package com.example.megaport.mynews;

import androidx.test.runner.AndroidJUnit4;

import com.example.megaport.mynews.Models.Articles;
import com.example.megaport.mynews.Models.Result;
import com.example.megaport.mynews.Utils.MyNewsServiceTest;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainTest {
    private final String mSection = "sports";
    private final MyNewsServiceTest mNewYorkTimeService = MyNewsServiceTest.retrofit.create(MyNewsServiceTest.class);


    @Test
    public void testGetMostPopularArticles(){
        baseForTestRequestNews(mNewYorkTimeService.getMostPopular());
    }
    @Test
    public void testGetTopStoriesArticles(){
        baseForTestRequestNews(mNewYorkTimeService.getTopStoriesArticles(mSection));
    }
    @Test
    public void testSearchArticles(){
        String endDate = "20181017";
        String beginDate = "20181015";
        String search = "tesla";
        baseForTestRequestSearchResult(mNewYorkTimeService.getSearch( search, beginDate, endDate, mSection));
    }


    private void baseForTestRequestNews(Observable<Articles> observable){
        //Create observer
        TestObserver<Articles> testObserver = new TestObserver<>();
        //subscribe to the observable
        observable.subscribeWith(testObserver).assertNoErrors().assertNoTimeout().awaitTerminalEvent();
        //get response
        Articles news = testObserver.values().get(0);
        //test response
        assertEquals("OK",news.getStatus());
    }

    private void baseForTestRequestSearchResult(Observable<Result>observable){
        //Create observer
        TestObserver<Result> testObserver = new TestObserver<>();
        //subscribe to the observable
        observable.subscribeWith(testObserver).assertNoErrors().assertNoTimeout().awaitTerminalEvent();
        //get response
        Result news = testObserver.values().get(0);
        //test response
        assertEquals("OK",news.getStatus());
    }
}
