package com.example.megaport.mynews

import com.example.megaport.mynews.models.Multimedium
import org.junit.Test
import org.junit.Assert.assertEquals

class MultimediumTest {

    private val testStr = "test"
    private val testInt = 1
    private val multimedium = Multimedium()

    @Test
    fun setUrl() {
        multimedium.url = testStr
        assertEquals("Url getter or setter is invalid", testStr, multimedium.url)
    }

    @Test
    fun setHeight() {
        multimedium.height = testInt
        assertEquals("Height getter or setter is invalid", testInt, multimedium.height)
    }

    @Test
    fun setWidth() {
        multimedium.width = testInt
        assertEquals("Width getter or setter is invalid", testInt, multimedium.width)
    }

    @Test
    fun setType() {
        multimedium.type = testStr
        assertEquals("Type getter or setter is invalid", testStr, multimedium.type)
    }
}