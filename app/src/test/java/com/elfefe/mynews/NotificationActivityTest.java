package com.elfefe.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

import com.elfefe.mynews.controllers.Activity.NotificationActivity;
import com.elfefe.mynews.controllers.NotificationWorker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class NotificationActivityTest {

    private static final String KEY_SEARCH = "key_search";
    private static final String KEY_SECTION = "key_section";
    private static final String PREF_NAME = "pref_name";
    private NotificationActivity notificationActivity;

    private SharedPreferences preferences;

    private EditText text;
    private AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    private Button search;

    private String searched;
    private Set<String> sectionsSet;

    @Before
    public void setup(){
        notificationActivity = Robolectric.buildActivity(NotificationActivity.class)
                .create().get();

        preferences = notificationActivity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        text = notificationActivity.findViewById(R.id.query_query);
        arts = notificationActivity.findViewById(R.id.query_cb_arts);
        buisness = notificationActivity.findViewById(R.id.query_cb_buisness);
        entrepreneurs = notificationActivity.findViewById(R.id.query_cb_entrepreneurs);
        politics = notificationActivity.findViewById(R.id.query_cb_politics);
        sports = notificationActivity.findViewById(R.id.querye_cb_sports);
        travel = notificationActivity.findViewById(R.id.query_cb_travel);
        search = notificationActivity.findViewById(R.id.query_button_query);
    }

    @After
    public void tearDown(){
    }

    @Test
    public void CheckUpThatAllTheViewsAreInitiateFalseAreEmpty(){

        Assert.assertEquals(text.getText().toString(),"");

        Assert.assertFalse(arts.isChecked());
        Assert.assertFalse(buisness.isChecked());
        Assert.assertFalse(entrepreneurs.isChecked());
        Assert.assertFalse(politics.isChecked());
        Assert.assertFalse(sports.isChecked());
        Assert.assertFalse(travel.isChecked());
    }

    @Test
    public void InitiateTheListAndConvertingItAsASetAndPutSharedPreferences() {

        arts.setChecked(true);
        buisness.setChecked(true);
        entrepreneurs.setChecked(true);
        politics.setChecked(true);
        sports.setChecked(true);
        travel.setChecked(true);

        List<String> sectionsList = new ArrayList<String>() {{
            if (arts.isChecked()) {
                add("arts");
            }
            if (buisness.isChecked()) {
                add("business");
            }
            if (entrepreneurs.isChecked()) {
                add("entrepreneurs");
            }
            if (politics.isChecked()) {
                add("politics");
            }
            if (sports.isChecked()) {
                add("sports");
            }
            if (travel.isChecked()) {
                add("search");
            }
        }};

        sectionsSet = new HashSet<>(sectionsList);

        searched = (!text.getText().toString().isEmpty())?text.getText().toString():"";

        GetSharedPreference();

        Assert.assertNotNull(sectionsList);
        Assert.assertNotNull(sectionsSet);
        if(text.getText().toString().isEmpty()){
            Assert.assertNotNull(searched);
        } else {
            Assert.assertEquals(searched, "");
        }
    }

    public void GetSharedPreference(){
        preferences.edit().putString(KEY_SEARCH,searched).putStringSet(KEY_SECTION,sectionsSet).apply();

        String testSearch = preferences.getString(KEY_SEARCH, "ERROR");
        Set<String> testSection = preferences.getStringSet(KEY_SECTION, null);

        Assert.assertEquals(testSearch, searched);
        Assert.assertEquals(testSection, sectionsSet);
        Assert.assertEquals(testSection, sectionsSet);
    }
}
