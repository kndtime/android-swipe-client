package com.app.yubotest.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "age")var age: String?,
    @field:Json(name = "birth")var birth: String?,
    @field:Json(name = "emojis")var emojis: List<String>?,
    @field:Json(name = "gender")var gender: String?,
    @field:Json(name = "instagram")var instagram: Instagram?,
    @field:Json(name = "location")var location: String?,
    @field:Json(name = "name")var name : String?,
    @field:Json(name = "photos")var photos: List<Photos>?,
    @field:Json(name = "town")var town: String?,
    @field:Json(name = "uid")var uid: String?
){
    val info : String get() {
        return "$name, ${age}"
    }
}


data class Data(
    @field:Json(name = "age")var age: String?,
    @field:Json(name = "birth")var birth: String?,
    @field:Json(name = "emojis")var emojis: List<String>?
)

data class Instagram(
    @field:Json(name = "count")var count: Int?,
    @field:Json(name = "profile_picture")var profile_picture: String?,
    @field:Json(name = "username")var username: String?
)

data class Photos(
    @field:Json(name = "type")var type: String?,
    @field:Json(name = "url")var url: String?
)