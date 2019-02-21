package com.example.megaport.mynews.models

import org.junit.Test
import java.util.ArrayList
import org.junit.Assert.assertEquals

class MediumTest {

    private val testStr = "test"
    private val medium = Medium()
    private val multimedium = Multimedium()

    @Test
    fun setType() {
        medium.type = testStr
        assertEquals("Type getter or setter invalid", testStr, medium.type)
    }

    @Test
    fun setCaption() {
        medium.caption = testStr
        assertEquals("Caption getter or setter invalid", testStr, medium.caption)
    }

    @Test
    fun setMultimedia() {
        multimedium.url = testStr
        val multimediumList = ArrayList<Multimedium>()
        multimediumList.add(multimedium)
        medium.multimedia = multimediumList
        assertEquals("Multimedia getter or setter invalid", testStr, medium.multimedia!![0].url)
    }
}