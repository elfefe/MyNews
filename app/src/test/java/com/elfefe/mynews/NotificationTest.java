package com.elfefe.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.elfefe.mynews.controllers.MainActivity;
import com.elfefe.mynews.controllers.NotificationActivity;
import com.elfefe.mynews.controllers.NotificationWorker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static net.bytebuddy.matcher.ElementMatchers.is;

@RunWith(RobolectricTestRunner.class)
public class NotificationTest {

    public static final String PREF_TEST = "KEY_PREF_TEST";
    public static final String PREF_SEARCH = "KEY_PREF_SEARCH";
    public static final String PREF_SECTION = "KEY_PREF_SECTION";

    private NotificationActivity notificationActivity;

    private SharedPreferences preferences;

    private EditText text;
    private AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    private Button search;

    private String searched;
    private List<String> sectionsList;
    private Set<String> sectionsSet;

    @Before
    public void setup(){
        notificationActivity = Robolectric.buildActivity(NotificationActivity.class)
                .create()
                .resume()
                .get();

        preferences = notificationActivity.getSharedPreferences(PREF_TEST, Context.MODE_PRIVATE);

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
    public void CheckUpThatAllTheViewsAreInitiateFalseAreEmpty() throws Exception {

        Assert.assertEquals(text.getText().toString(),"");

        Assert.assertFalse(arts.isChecked());
        Assert.assertFalse(buisness.isChecked());
        Assert.assertFalse(entrepreneurs.isChecked());
        Assert.assertFalse(politics.isChecked());
        Assert.assertFalse(sports.isChecked());
        Assert.assertFalse(travel.isChecked());
    }

    @Test
    public void InitiateTheListAndConvertingItAsASetAndPutSharedPreferences() throws Exception {

        arts.setChecked(true);
        buisness.setChecked(true);
        entrepreneurs.setChecked(true);
        politics.setChecked(true);
        sports.setChecked(true);
        travel.setChecked(true);

        sectionsList = new ArrayList<String>() {{
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
        preferences.edit().putString(PREF_SEARCH,searched).putStringSet(PREF_SECTION,sectionsSet).apply();

        String testSearch = preferences.getString(PREF_SEARCH, "ERROR");
        Set<String> testSection = preferences.getStringSet(PREF_SECTION, null);

        Assert.assertEquals(testSearch, searched);
        Assert.assertEquals(testSection, sectionsSet);
    }
    @Test
    public void SetupAndStartWorker() throws Exception {



        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest notificationWorker = new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.DAYS)
                .setConstraints(constraints)
                // .setInputData(data)
                .build();

        WorkManager.getInstance()
                .enqueue(notificationWorker);


        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .build();

        WorkManager workManager = WorkManager.getInstance();

        workManager.enqueue(request).getResult().get();

        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();

        Assert.assertEquals(workInfo.getState(), WorkInfo.State.SUCCEEDED);
    }
}
