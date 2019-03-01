package com.example.megaport.mynews.controllers.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val okHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
        .build()
