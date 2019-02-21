package com.example.megaport.mynews.models

import org.junit.Test
import java.util.ArrayList
import org.junit.Assert.assertEquals

class ArticlesTest {

    private val article = Article()
    private val articles = Articles()

    @Test
    fun setArticles() {
        val testStr = "test"
        article.title = testStr
        val articlesL = ArrayList<Article>()
        articlesL.add(article)
        articles.articles = articlesL
        val articles2 = articles.articles
        assertEquals("Articles getter or setter is invalid", testStr, articles.getArticle(0)!!.title)
        assertEquals("Articles getter or setter is invalid", testStr, articles2!![0].title)
    }
}