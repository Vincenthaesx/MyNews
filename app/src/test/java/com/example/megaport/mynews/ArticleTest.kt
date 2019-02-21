package com.example.megaport.mynews

import com.example.megaport.mynews.models.Article
import com.example.megaport.mynews.models.Medium
import com.example.megaport.mynews.models.Multimedium
import org.junit.Test
import java.util.ArrayList
import org.junit.Assert.assertEquals

class ArticleTest {

    private val article = Article()
    private val testStr = "test"

    @Test
    fun setTitle() {
        article.title = testStr
        assertEquals("Title setter or getter is invalid", testStr, article.title)
    }

    @Test
    fun setSection() {
        article.section = testStr
        assertEquals("Section setter or getter is invalid", testStr, article.section)
    }

    @Test
    fun setSubsection() {
        article.subsection = testStr
        assertEquals("Subsection setter or getter is invalid", testStr, article.subsection)
    }

    @Test
    fun setUrl() {
        article.url = testStr
        assertEquals("Url setter or getter is invalid", testStr, article.url)
    }

    @Test
    fun setPublishedDate() {
        article.publishedDate = testStr
        assertEquals("PublishedDate setter or getter is invalid", testStr, article.publishedDate)
    }

    @Test
    fun setMultimedia() {
        val multimedia = Multimedium()
        multimedia.url = testStr
        val multimedium = ArrayList<Multimedium>()
        multimedium.add(multimedia)
        article.multimedia = multimedium
        assertEquals("Multimedia setter or getter is invalid", testStr, article.multimedia!![0].url)
    }

    @Test
    fun setMedia() {
        val media = Medium()
        media.caption = testStr
        val medium = ArrayList<Medium>()
        medium.add(media)
        article.media = medium
        assertEquals("Media setter or getter is invalid", testStr, article.media!![0].caption)
    }
}