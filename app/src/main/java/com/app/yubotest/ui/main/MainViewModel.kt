package com.app.yubotest.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.app.yubotest.model.User
import com.app.yubotest.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Response

class MainViewModel : BaseViewModel(){
    var liveResponseData : LiveData<List<User>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun list() : LiveData<List<User>>? {
        liveResponseData = repository.list()
        return  liveResponseData
    }

    fun like(user: User) {
        repository.like(user)
    }

    fun dislike(user: User) {
        repository.dislike(user)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in repository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }
}