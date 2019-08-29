package com.elfefe.mynews.utils;

import java.util.List;

public class SectionLuceneFormator {
    private final List<String> sectionList;

    public SectionLuceneFormator(List<String> list) {
        this.sectionList = list;
    }

    public  String createLucene() {
        StringBuilder sectionsBuilder = new StringBuilder();

        String section_prefix = "section_name:( ";
        sectionsBuilder.append(section_prefix);

        for (String section : sectionList){
            sectionsBuilder.append("\"").append(section).append("\" ");
        }

        String section_suffix = ")";
        sectionsBuilder.append(section_suffix);

        return sectionsBuilder.toString();
    }
}
