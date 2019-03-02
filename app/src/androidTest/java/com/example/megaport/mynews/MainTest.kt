package com.example.megaport.mynews

import androidx.test.runner.AndroidJUnit4

import com.example.megaport.mynews.models.Articles
import com.example.megaport.mynews.models.Result
import com.example.megaport.mynews.utils.MyNewsServiceTest
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class MainTest {
    private val mSection = "sports"
    private val mNewYorkTimeService = MyNewsServiceTest.retrofit.create(MyNewsServiceTest::class.java)


    @Test
    fun testGetMostPopularArticles() {
        baseForTestRequestNews(mNewYorkTimeService.mostPopular())
    }

    @Test
    fun testGetTopStoriesArticles() {
        baseForTestRequestNews(mNewYorkTimeService.getTopStoriesArticles(mSection))
    }

    @Test
    fun testSearchArticles() {
        val endDate = "20181017"
        val beginDate = "20181015"
        val search = "tesla"
        baseForTestRequestSearchResult(mNewYorkTimeService.getSearch(search, beginDate, endDate, mSection))
    }


    private fun baseForTestRequestNews(observable: Observable<Articles>) {
        //Create observer
        val testObserver = TestObserver<Articles>()
        //subscribe to the observable
        observable.subscribeWith(testObserver).assertNoErrors().assertNoTimeout().awaitTerminalEvent()
        //get response
        val news = testObserver.values()[0]
        //test response
        assertEquals("OK", news.status)
    }

    private fun baseForTestRequestSearchResult(observable: Observable<Result>) {
        //Create observer
        val testObserver = TestObserver<Result>()
        //subscribe to the observable
        observable.subscribeWith(testObserver).assertNoErrors().assertNoTimeout().awaitTerminalEvent()
        //get response
        val news = testObserver.values()[0]
        //test response
        assertEquals("OK", news.status)
    }
}
