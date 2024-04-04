package com.example.broadcastrcvtesting.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.broadcastrcvtesting.data.models.GithubProfiler

class UserDetailVM : ViewModel() {
    private var _user: GithubProfiler? = GithubProfiler.default
    public val ready: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    public val user: GithubProfiler?
            get() = _user
    fun setUserName(username: GithubProfiler?){
        _user = username
        ready.postValue(true)

    }
    init {

    }
}