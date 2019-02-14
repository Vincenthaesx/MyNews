package com.example.megaport.mynews.Controllers.Utils;

import com.example.megaport.mynews.Models.Articles;
import com.example.megaport.mynews.Models.Result;
import com.example.megaport.mynews.Models.notifications.MyNewsResultAPI;

import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

public class MyNewsStreams {

    // TopStories
    public static Observable<Articles> streamFetchTopStories(String key){
        MyNewsService newsService = MyNewsService.retrofit.create( MyNewsService.class );

        return newsService.getTopStoriesArticles( key )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread() )
                .timeout(10, TimeUnit.SECONDS);
    }

    // MostPopular
    public static Observable<Articles> streamFetchMostPopular(){
        MyNewsService nyTimesService = MyNewsService.retrofit.create(MyNewsService.class);
        return nyTimesService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // Search
    public static Observable<Result> streamFetchSearch(String query, String start_date, String end_date, String section){
        MyNewsService nyTimesService = MyNewsService.retrofit.create(MyNewsService.class);
        if(query.isEmpty())
            query = null;
        if(start_date.isEmpty())
            start_date = null;
        if(end_date.isEmpty())
            end_date = null;
        return nyTimesService.getSearch(query, start_date, end_date, section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // Notification
    public static Observable<MyNewsResultAPI> streamFetchNotifications(String toSearch, @Nullable List<String> filterQuery, @Nullable String beginDate, @Nullable String endDate){
        MyNewsService nyTimesService = MyNewsService.retrofit.create(MyNewsService.class);
        return nyTimesService.getResultForNotification(toSearch, filterQuery, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
