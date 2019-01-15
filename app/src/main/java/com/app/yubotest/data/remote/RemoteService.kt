package com.app.yubotest.data.remote

import com.app.yubotest.model.Photos
import com.app.yubotest.model.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
object RemoteService{

    var userlist = Types.newParameterizedType(List::class.java, User::class.java)
    var photolist = Types.newParameterizedType(List::class.java, Photos::class.java)


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideYuboService(retrofit: Retrofit) : YuboService{
        return retrofit.create(YuboService::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
        httpClient.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        var moshi = Moshi.Builder()
            .build()
        var userAdapter : JsonAdapter<List<User>> = moshi.adapter(userlist)
        var photosAdapter : JsonAdapter<List<Photos>> = moshi.adapter(photolist)
        return Retrofit.Builder()
            .client(provideHttpClient())
            .baseUrl("http://test2.yellw.co")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    operator fun invoke(): RemoteService = this
}