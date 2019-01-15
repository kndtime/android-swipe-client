package com.app.yubotest.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.app.yubotest.model.User
import com.app.yubotest.ui.BaseViewModel
import okhttp3.Response

class MainViewModel : BaseViewModel(){
    private var liveResponseData : MutableLiveData<List<User>>? = null

    fun list() : MutableLiveData<List<User>>? {
        liveResponseData = MutableLiveData()
        liveResponseData!!.value = repository.list().value?.data
        Log.d("data", "${repository.list().value}")
        return  liveResponseData
    }

    fun like(user: User) {
        repository.like(user)
    }

    fun dislike(user: User) {
        repository.dislike(user)
    }
}