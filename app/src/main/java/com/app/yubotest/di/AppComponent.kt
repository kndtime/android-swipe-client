package com.app.yubotest.di

import com.app.yubotest.data.remote.RemoteService
import com.app.yubotest.ui.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RemoteService::class))
@Singleton
interface AppComponent {

    fun inject(baseViewModel: BaseViewModel)
}