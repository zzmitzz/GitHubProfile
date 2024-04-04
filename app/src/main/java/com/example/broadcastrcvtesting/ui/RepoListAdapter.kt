package com.example.broadcastrcvtesting.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.broadcastrcvtesting.data.models.RepositoryModel
import com.example.broadcastrcvtesting.databinding.ProfileItemBinding

class RepoListAdapter: RecyclerView.Adapter<RepoListAdapter.VH>() {

    private val repoList = mutableListOf<RepositoryModel>()
    var onItemClick: ((Int) -> Unit)? = null
    @SuppressLint("NotifyDataSetChanged")
    fun setListData(repoList: List<RepositoryModel>) {
        this.repoList.clear()
        this.repoList.addAll(repoList)
        notifyDataSetChanged()
    }
    inner class VH(
        private val binding: ProfileItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(repo: RepositoryModel) {
            binding.run {
                if(repo.fork == false){
                    fork.visibility = View.GONE
                }
                tview1.text = repo.name
                tview2.text = repo.fullName
                star.text = repo.stargazersCount.toString()
                count.text = repo.forksCount.toString()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ProfileItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        val repo = repoList[position]
        holder.itemView.apply {
            holder.bind(repo)
            setOnClickListener {
                onItemClick?.invoke(position)
            }
        }

    }

    override fun getItemCount(): Int = repoList.size
}