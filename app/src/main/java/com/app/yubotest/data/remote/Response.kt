package com.app.yubotest.data.remote

import android.arch.lifecycle.LiveData
import com.app.yubotest.model.User
import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "data")var data: List<User>?,
    @field:Json(name = "result")var result: String?
)