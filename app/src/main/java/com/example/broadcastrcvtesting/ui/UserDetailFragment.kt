package com.example.broadcastrcvtesting.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.broadcastrcvtesting.data.models.GithubProfiler
import com.example.broadcastrcvtesting.data.models.GithubProfilerRepo
import com.example.broadcastrcvtesting.data.models.RepositoryModel
import com.example.broadcastrcvtesting.data.models.RepositoryRepo
import com.example.broadcastrcvtesting.databinding.UserFragmentBinding
import com.example.broadcastrcvtesting.ext.image_helper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserDetailFragment(val userName: String): Fragment() {
    private val binding by lazy {
        UserFragmentBinding.inflate(layoutInflater)
    }
    private val viewModels by viewModels<UserDetailVM>()
    private var username = "zzmitzz"
    private var user: GithubProfiler? = null
    private var repos: List<RepositoryModel>? = null
    init {
        this.username = userName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            LoadingPage.visibility = View.VISIBLE
            detailPage.visibility = View.GONE
            webview.visibility = View.GONE
            webviewbar.visibility = View.GONE
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            user = GithubProfilerRepo.Network().getUserGithub(username)
            repos = RepositoryRepo.Network().getRepos(username)
            repos = repos!!.sortedWith(compareByDescending<RepositoryModel> { it.stargazersCount }
                .thenByDescending { it.forksCount })
            Log.d(DetailActivity.TAG, repos!!.size.toString())
            if(user?.login?.length == 0) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show()
                    activity?.onBackPressed()
                }
            }
            else{
                viewModels.setUserName(user)
            }

        }
        viewModels.ready.observe(viewLifecycleOwner) {
            if(viewModels.ready.value == true){
                CoroutineScope(Dispatchers.Default).launch {
//                    val a = GithubProfilerRepo.RoomDB(RoomDataBase.getInstance(requireContext()))
                    Log.d(DetailActivity.TAG,"success store in roomDB")
                }
                bindingUI()
                prepareRCV()
            }
        }
    }
    private fun prepareRCV(){
        binding.apply {
            val adapter = RepoListAdapter()
            adapter.onItemClick = {
                webview.visibility = View.VISIBLE
                webview.loadUrl(repos!![it].htmlUrl ?: "https://github.com/zzmitzz")
                webview.settings.setJavaScriptEnabled(true)
                webview.webViewClient = WebViewClient()
                webviewbar.visibility = View.VISIBLE
                openBrowser.setOnClickListener {view ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repos!![it].htmlUrl))
                    startActivity(intent)
                }
            }
            if(repos!!.size >= 5){
                var tmp = repos!!.subList(0,5)
                adapter.setListData(tmp)

            }else{
                adapter.setListData(repos!!)
            }
            rcviewList.adapter = adapter
            rcviewList.setHasFixedSize(true)
        }
    }
    private fun  bindingUI(){
        binding.apply {
            Log.d(DetailActivity.TAG,user?.login ?: "null")
            detailPage.visibility = View.VISIBLE
            LoadingPage.visibility = View.GONE
            userName.text = user?.login
            name.text = user?.name
            textView.text = "${user?.following} Following - ${user?.followers} Follower(s)"
            Bio.text  = user?.bio
            if(user?.avatar_url?.trim()?.length == 0 || user?.avatar_url == null){
                image_helper("https://avatars.githubusercontent.com/u/1446536?v=4",avatar )
            }else{
                image_helper(user?.avatar_url!!.trim(),avatar)
            }
            done.setOnClickListener {
                webview.visibility = View.GONE
                webviewbar.visibility = View.GONE
            }
            seeAll.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(user?.repos_url)))
            }
        }
    }

}