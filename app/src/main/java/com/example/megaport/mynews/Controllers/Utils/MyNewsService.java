package com.example.megaport.mynews.Controllers.Utils;

import android.support.annotation.Nullable;

import com.example.megaport.mynews.Models.Articles;
import com.example.megaport.mynews.Models.Result;
import com.example.megaport.mynews.Models.notifications.MyNewsResultAPI;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MyNewsService
{

    // TOP STORIES API

    @GET("svc/topstories/v2/{section}.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ")
    Observable<Articles> getTopStoriesArticles(@Path("section") String section);

    // MOST POPULAR API

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ")
    Observable<Articles> getMostPopular();


    // SEARCH API

    @GET("svc/search/v2/articlesearch.json?api-key=pvatnTXul5i0bamh9uisdP0q9HnG0lbQ&sort=newest")
    Observable<Result> getSearch(
            @Query("q") String query,
            @Query("begin_date") String b_date,
            @Query("end_date") String e_date,
            @Query("fq") String section);

    // RESULT API

    @GET("svc/search/v2/articlesearch.json?api-key=514f85e678024ec3a52dd5cb986fdc63&sort=newest" )
    Observable<MyNewsResultAPI> getResultForNotification(@Query("q") String toSearch, @Nullable @Query("fq") List<String> filterQuery, @Nullable @Query("begin_date") String beginDate, @Nullable @Query("end_date") String endDate);



    // ----------------------------------------------

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
