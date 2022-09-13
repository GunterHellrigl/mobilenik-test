package com.gunter.mobilenik.model.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRequest<T: Any>(
    private var baseUrl: String
) {
    fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T: Any> getService(): T =
        buildRetrofit().run {
            create(T::class.java)
        }
}

class RecipeRequest(baseUrl: String): BaseRequest<RecipeService>(baseUrl)