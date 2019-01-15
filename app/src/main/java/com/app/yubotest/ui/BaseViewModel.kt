package com.app.yubotest.ui

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.app.yubotest.data.repository.BaseRepository
import com.app.yubotest.data.repository.Repository
import com.app.yubotest.di.YuboTest
import javax.inject.Inject

open class BaseViewModel : ViewModel(), LifecycleObserver {
    @Inject
    lateinit var repository: Repository

    init {
        initializeDagger()
    }

    private fun initializeDagger() {
        YuboTest.appComponent.inject(this)
    }
}