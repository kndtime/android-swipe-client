package com.app.yubotest.data.repository

import android.arch.lifecycle.LiveData
import com.app.yubotest.data.remote.Response
import com.app.yubotest.model.User

interface BaseRepository{

    fun list() : LiveData<List<User>>
    fun like(user: User) : LiveData<Response>
    fun dislike(user: User) : LiveData<Response>

}