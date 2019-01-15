package com.app.yubotest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val yuboTest: YuboTest){
    @Provides
    @Singleton
    fun provideContext(): Context = yuboTest
}