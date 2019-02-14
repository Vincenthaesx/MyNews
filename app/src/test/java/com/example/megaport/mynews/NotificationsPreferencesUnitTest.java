package com.example.megaport.mynews;


import com.example.megaport.mynews.Models.notifications.NotificationsPreferences;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class NotificationsPreferencesUnitTest {

    @Test
    public void createNotificationsPreferences() {
        List<String> categoryList = new ArrayList<>();
        String queryTerm = "test";

        NotificationsPreferences notificationsPref = new NotificationsPreferences(queryTerm, categoryList, false);

        assertEquals("test", notificationsPref.getQueryTerm());
        assertTrue( notificationsPref.getCategoryList().isEmpty() );
        assertFalse( notificationsPref.isEnabled() );
    }

    @Test
    public void setQueryTerm() {
        NotificationsPreferences notificationsPref = new NotificationsPreferences("",null, false);

        notificationsPref.setQueryTerm("test");

        assertEquals("test", notificationsPref.getQueryTerm());
    }

    @Test
    public void setCategoryList() {
        NotificationsPreferences notificationsPref = new NotificationsPreferences("",null, false);

        List<String> categoryList = new ArrayList<>();
        String entry1 = "Arts";
        String entry2 = "Politics";
        categoryList.add(entry1);
        categoryList.add(entry2);

        notificationsPref.setCategoryList(categoryList);

        assertEquals("Politics", notificationsPref.getCategoryList().get(1));
    }

    @Test
    public void setEnabled() {
        NotificationsPreferences notificationsPref = new NotificationsPreferences("",null, false);

        notificationsPref.setEnabled(true);

        assertTrue( notificationsPref.isEnabled() );
    }
}