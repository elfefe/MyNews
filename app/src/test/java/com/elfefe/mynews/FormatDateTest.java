package com.elfefe.mynews;

import com.elfefe.mynews.utils.FormatDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FormatDateTest {

    @Test
    public void date_is_formated_as_string_in_good_order(){

        String date = "01/01/01";

        FormatDate formatDate = new FormatDate(date);

        Assert.assertEquals("2001/01/01",formatDate.date());
    }
}
