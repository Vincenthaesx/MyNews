package com.example.megaport.mynews.utils

import com.example.megaport.mynews.models.Articles
import com.example.megaport.mynews.models.Result
import java.util.concurrent.TimeUnit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal object MyNewsStreamsTest {

    fun streamFetchTopStories(key: String): Observable<Articles> {
        val nyTimesServiceTest = MyNewsServiceTest.retrofit.create(MyNewsServiceTest::class.java)
        return nyTimesServiceTest.getTopStoriesArticles(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS)
    }

    fun streamFetchMostPopular(): Observable<Articles> {
        val nyTimesServiceTest = MyNewsServiceTest.retrofit.create(MyNewsServiceTest::class.java)
        return nyTimesServiceTest.mostPopular
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS)
    }

    fun streamSearch(query: String, start_date: String, end_date: String, section: String): Observable<Result> {
        val nyTimesServiceTest = MyNewsServiceTest.retrofit.create(MyNewsServiceTest::class.java)
        return nyTimesServiceTest.getSearch(query, start_date, end_date, section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10000, TimeUnit.SECONDS)
    }
}
