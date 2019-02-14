package com.example.megaport.mynews.Utils;

import com.example.megaport.mynews.Models.Articles;
import com.example.megaport.mynews.Models.Result;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class MyNewsStreamsTest {

    public static Observable<Articles> streamFetchTopStories(String key){
        MyNewsServiceTest nyTimesServiceTest = MyNewsServiceTest.retrofit.create(MyNewsServiceTest.class);
        return nyTimesServiceTest.getTopStoriesArticles(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS);
    }

    public static Observable<Articles> streamFetchMostPopular(){
        MyNewsServiceTest nyTimesServiceTest = MyNewsServiceTest.retrofit.create(MyNewsServiceTest.class);
        return nyTimesServiceTest.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS);
    }

    public static Observable<Result> streamSearch(String query, String start_date, String end_date, String section){
        MyNewsServiceTest nyTimesServiceTest = MyNewsServiceTest.retrofit.create(MyNewsServiceTest.class);
        return nyTimesServiceTest.getSearch(query, start_date, end_date, section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS);
    }
}
