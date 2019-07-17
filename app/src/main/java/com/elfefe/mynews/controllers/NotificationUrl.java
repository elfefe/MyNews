package com.elfefe.mynews.controllers;

import java.util.List;

public class NotificationUrl {
    private static String sections;
    private static final String section_prefix = "section_name:( ";
    private static final String section_suffix = ")";

    public static NotificationUrl createUrl(List<String> list) {
        NotificationUrl notificationUrl = new NotificationUrl();

        StringBuilder sectionsBuilder = new StringBuilder();

        sectionsBuilder.append(NotificationUrl.section_prefix);

        for (String section : list) {
            sectionsBuilder.append("\"").append(section).append("\" ");
        }
        sectionsBuilder.append(NotificationUrl.section_suffix);

        sections = sectionsBuilder.toString();

        return notificationUrl;
    }

    public String getUrl(){
        return sections;
    }
}
