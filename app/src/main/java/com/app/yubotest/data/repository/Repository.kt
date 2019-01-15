package com.app.yubotest.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.app.yubotest.data.remote.RemoteService
import com.app.yubotest.data.remote.Response
import com.app.yubotest.data.remote.YuboService
import com.app.yubotest.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteYuboService: RemoteService
) : BaseRepository {
    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun list(): LiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        val disposable = remoteYuboService.provideYuboService(remoteYuboService.provideRetrofitInterface())
            .list()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {res ->
                if (true || res.result.equals("ok")){
                    mutableLiveData.value = res
                    Log.d("Repo", "$res")
                } else {
                    Log.d("Repo", "$res")
                    throw Throwable("SymplRepository -> on Error occurred - ${object{}.javaClass.enclosingMethod?.name}")
                }
            }, {t: Throwable? -> t?.printStackTrace()})
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    override fun like(user: User): LiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        val disposable = remoteYuboService.provideYuboService(remoteYuboService.provideRetrofitInterface())
            .like(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {res ->
                if (res.result.equals("ok")){
                    mutableLiveData.value = res
                } else {
                    throw Throwable("SymplRepository -> on Error occurred - ${object{}.javaClass.enclosingMethod?.name}")
                }
            }, {t: Throwable? -> t?.printStackTrace()})
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    override fun dislike(user: User): LiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        val disposable = remoteYuboService.provideYuboService(remoteYuboService.provideRetrofitInterface())
            .dislike(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {res ->
                if (res.result.equals("ok")){
                    mutableLiveData.value = res
                } else {
                    throw Throwable("SymplRepository -> on Error occurred - ${object{}.javaClass.enclosingMethod?.name}")
                }
            }, {t: Throwable? -> t?.printStackTrace()})
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }
}