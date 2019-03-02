package com.example.megaport.mynews.utils

import com.example.megaport.mynews.models.Articles
import com.example.megaport.mynews.models.Result

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyNewsServiceTest {

    // MOST POPULAR API

    @GET("svc/mostpopular/v2/viewed/7.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ")
    fun mostPopular(): Observable<Articles>

    // TOP STORIES API

    @GET("svc/topstories/v2/{section}.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ")
    fun getTopStoriesArticles(@Path("section") section: String): Observable<Articles>


    // SEARCH API

    @GET("svc/search/v2/articlesearch.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ&sort=newest")
    fun getSearch(
            @Query("q") query: String,
            @Query("begin_date") b_date: String,
            @Query("end_date") e_date: String,
            @Query("fq") section: String): Observable<Result>

    companion object {

        // ----------------------------------------------

        private const val BASE_URL = "https://api.nytimes.com/"

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
