package com.example.rxandroidandretrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {
    companion object {

        private val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okBuilder = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true).addInterceptor(
                loggingInterceptor
            )
        val apiService: ApiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okBuilder.build())
            .build()
            .create(ApiService::class.java)
    }
    @GET("posts")
    fun callApi() : Observable<List<ObjectData>>
}
