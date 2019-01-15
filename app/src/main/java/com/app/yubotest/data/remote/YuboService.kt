package com.app.yubotest.data.remote

import com.app.yubotest.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface YuboService{
    @GET("/list")
    fun list(): Observable<Response>

    @POST("/like")
    fun like(@Body user: User): Observable<Response>

    @POST("/dislike")
    fun dislike(@Body user: User): Observable<Response>
}