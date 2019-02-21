package com.example.megaport.mynews

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
        .build()


