package com.example.megaport.mynews.Controllers.Utils

import com.example.megaport.mynews.Models.Articles
import com.example.megaport.mynews.Models.Result
import com.example.megaport.mynews.Models.notifications.MyNewsResultAPI
import com.example.megaport.mynews.okHttp
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

internal interface MyNewsService {

    // MOST POPULAR API

    @GET("svc/mostpopular/v2/viewed/7.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ")
    fun mostPopular(): Observable<Articles>

    // TOP STORIES API

    @GET("svc/topstories/v2/{section}.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ")
    fun getTopStoriesArticles(@Path("section") section: String): Observable<Articles>


    // SEARCH API

    @GET("svc/search/v2/articlesearch.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ&sort=newest")
    fun getSearch(
            @Query("q") query: String?,
            @Query("begin_date") b_date: String?,
            @Query("end_date") e_date: String?,
            @Query("fq") section: String): Observable<Result>

    // RESULT API

    @GET("svc/search/v2/articlesearch.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ&sort=newest")
    fun getResultForNotification(
            @Query("q") toSearch: String?,
            @Query("fq") filterQuery: List<String>?,
            @Query("begin_date") beginDate: String?,
            @Query("end_date") endDate: String?): Observable<MyNewsResultAPI>

    companion object {

        private const val BASE_URL = "https://api.nytimes.com/"
        private const val TOKEN = "pvatnTXul5i0bamh9uisdP0q9HnG0lbQ"

        private val okHttpS = okHttp.newBuilder()
                .callTimeout(20, TimeUnit.SECONDS)
//                .addInterceptor {
//                    val request = it.request().newBuilder()
//                            .addHeader("api-key", TOKEN)
//                            .build()
//                    it.proceed(request)
//                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpS)
                .build()
    }
}
