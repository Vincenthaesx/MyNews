package com.example.megaport.mynews.Utils;

import com.example.megaport.mynews.models.Articles;
import com.example.megaport.mynews.models.Result;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyNewsServiceTest {

    // TOP STORIES API

    @Headers({
            "api_key: 2d4ac87ce0c64ce5a415c89c4678c1a5"
    })

    @GET("svc/topstories/v2/{section}.json")
    Observable<Articles> getTopStoriesArticles(@Path("section") String section);

    // MOST POPULAR API

    @Headers({
            "api_key: 2d4ac87ce0c64ce5a415c89c4678c1a5"
    })
    @GET("svc/mostpopular/v2/mostviewed/all-sections/1.json")
    Observable<Articles> getMostPopular();


    // SEARCH API

    @GET("svc/search/v2/articlesearch.json?api-key=514f85e678024ec3a52dd5cb986fdc63&sort=newest")
    Observable<Result> getSearch(
            @Query("q") String query,
            @Query("begin_date") String b_date,
            @Query("end_date") String e_date,
            @Query("fq") String section);

    // ----------------------------------------------

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
