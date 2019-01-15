package com.app.yubotest.di

import android.app.Application
import com.app.yubotest.data.remote.RemoteService
import com.facebook.stetho.Stetho

class YuboTest : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        Stetho.initializeWithDefaults(this)
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .remoteService(RemoteService()).build()
    }
}