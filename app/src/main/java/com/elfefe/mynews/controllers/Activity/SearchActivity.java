package com.elfefe.mynews.controllers.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.SearchSpinner;
import com.elfefe.mynews.controllers.adapters.SpinnerAdapter;
import com.elfefe.mynews.models.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static String KEY_SEARCH = "SEARCH_BUNDLE";

    EditText text;
    SearchSpinner dateEnd, dateBegin;
    AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    Button search;

    List<String> periods = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_query);

        Toolbar toolbar = findViewById(R.id.query_toolbar);
        setSupportActionBar(toolbar);

        text = findViewById(R.id.query_query);
        arts = findViewById(R.id.query_cb_arts);
        buisness = findViewById(R.id.query_cb_buisness);
        entrepreneurs = findViewById(R.id.query_cb_entrepreneurs);
        politics = findViewById(R.id.query_cb_politics);
        sports = findViewById(R.id.querye_cb_sports);
        travel = findViewById(R.id.query_cb_travel);
        search = findViewById(R.id.query_button_query);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout layoutBegin = findViewById(R.id.query_layout_begin);
        LinearLayout layoutEnd = findViewById(R.id.query_layout_end);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        periods.add("01/01/2014");
        periods.add("01/01/2015");
        periods.add("01/01/2016");
        periods.add("01/01/2017");
        periods.add("01/01/2018");

        SearchSpinner spinnerBegin = new SearchSpinner(
                R.id.spinner_acs,
                layoutBegin,
                R.layout.spinner_items,
                inflater);

        spinnerBegin.setTextView(R.id.spinner_tv, getResources().getString(R.string.spinner_begin_title));
        spinnerBegin.setAdapter(new SpinnerAdapter(this,R.layout.spinner_adapter_item,periods));

        SearchSpinner spinnerEnd = new SearchSpinner(
                R.id.spinner_acs,
                layoutEnd,
                R.layout.spinner_items,
                inflater);

        spinnerEnd.setTextView(R.id.spinner_tv, getResources().getString(R.string.spinner_end_title));
        spinnerEnd.setAdapter(new SpinnerAdapter(this,R.layout.spinner_adapter_item,periods));

        dateBegin = spinnerBegin;
        dateEnd = spinnerEnd;


        layoutBegin.addView(spinnerBegin.getView(), 0);
        layoutEnd.addView(spinnerEnd.getView(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        search.setOnClickListener(v -> {

            Bundle bundle = new Bundle();

            ArrayList<String> sectionsList = new ArrayList<String>(){{
                add("arts");
                add("business");
                add("entrepreneurs");
                add("politics");
                add("sports");
                add("travel");
            }};

            boolean[] checkList = {
                    arts.isChecked(),
                    buisness.isChecked(),
                    entrepreneurs.isChecked(),
                    politics.isChecked(),
                    sports.isChecked(),
                    travel.isChecked()
            };

            Search searchData = new Search(
                    text.getText().toString(),
                    dateBegin.getSelectedItem(),
                    dateEnd.getSelectedItem(),
                    sectionsList,
                    checkList
            );

            bundle.putParcelable(KEY_SEARCH, searchData);

            Intent intent = new Intent(this, FilteredSearchActivity.class);

            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_back) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
