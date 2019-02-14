package com.example.megaport.mynews;

import com.example.megaport.mynews.Models.Multimedium;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MultimediumTest {

    private final String testStr = "test";
    private final Integer testInt = 1;
    private final Multimedium multimedium = new Multimedium();

    @Test
    public void setUrl() {
        multimedium.setUrl(testStr);
        assertEquals("Url getter or setter is invalid", testStr, multimedium.getUrl());
    }

    @Test
    public void setHeight() {
        multimedium.setHeight(testInt);
        assertEquals("Height getter or setter is invalid", testInt, multimedium.getHeight());
    }

    @Test
    public void setWidth() {
        multimedium.setWidth(testInt);
        assertEquals("Width getter or setter is invalid", testInt, multimedium.getWidth());
    }

    @Test
    public void setType() {
        multimedium.setType(testStr);
        assertEquals("Type getter or setter is invalid", testStr, multimedium.getType());
    }
}