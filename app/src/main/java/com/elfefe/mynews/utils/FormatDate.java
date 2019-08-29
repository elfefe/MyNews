package com.elfefe.mynews.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

    private final String date;

    public FormatDate(String date) {
        this.date = date;
    }

    public String date(){
        Date fromDate;
        String toDate = null;

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat fromSimpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat toSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            fromDate = fromSimpleDateFormat.parse(date);

            assert fromDate != null;
            toDate = toSimpleDateFormat.format(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return toDate;
    }
}
