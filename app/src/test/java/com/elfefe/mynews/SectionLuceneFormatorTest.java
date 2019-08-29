package com.elfefe.mynews;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class SectionLuceneFormatorTest {

    @Test
    public void string_is_correctly_formated(){

        StringBuilder sectionsBuilder = new StringBuilder();

        String section_prefix = "section_name:( ";
        sectionsBuilder.append(section_prefix);

        boolean[] checkList = new boolean[]{
                true,
                true,
                false,
        };
        List<String> sectionList = new ArrayList<String>(){{
            add("section1");
            add("section2");
            add("section3");
        }};


        if(checkList.length == sectionList.size()) {
            for (int x = 0; x < checkList.length; x++) {
                if (checkList[x]) {
                    sectionsBuilder.append("\"").append(sectionList.get(x)).append("\" ");
                }
            }
        }

        String section_suffix = ")";
        sectionsBuilder.append(section_suffix);
    }
}
