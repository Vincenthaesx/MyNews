package com.example.megaport.mynews.Controllers.Utils

import com.example.megaport.mynews.Models.Articles
import com.example.megaport.mynews.Models.Result
import com.example.megaport.mynews.Models.notifications.MyNewsResultAPI
import java.util.concurrent.TimeUnit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.Nullable
import io.reactivex.schedulers.Schedulers

object MyNewsStreams {

    // TopStories
    fun streamFetchTopStories(section: String): Observable<Articles> {
        val newsService = MyNewsService.retrofit.create(MyNewsService::class.java)

        return newsService.getTopStoriesArticles(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(100, TimeUnit.SECONDS)
    }

    // MostPopular
    fun streamFetchMostPopular(): Observable<Articles> {
        val nyTimesService = MyNewsService.retrofit.create<MyNewsService>(MyNewsService::class.java)
        return nyTimesService.mostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
    }

    // Search
    fun streamFetchSearch(query: String?, start_date: String?, end_date: String?, section: String): Observable<Result> {
        var query = query
        var startDate = start_date
        var endDate = end_date
        val nyTimesService = MyNewsService.retrofit.create<MyNewsService>(MyNewsService::class.java)
        if (query!!.isEmpty())
            query = null
        if (startDate!!.isEmpty())
            startDate = null
        if (endDate!!.isEmpty())
            endDate = null
        return nyTimesService.getSearch(query, startDate, endDate, section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
    }

    // Notification
    fun streamFetchNotifications(toSearch: String?, @Nullable filterQuery: List<String>?, @Nullable beginDate: String?, @Nullable endDate: String?): Observable<MyNewsResultAPI> {
        val nyTimesService = MyNewsService.retrofit.create<MyNewsService>(MyNewsService::class.java)
        return nyTimesService.getResultForNotification(toSearch, filterQuery, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
    }
}
