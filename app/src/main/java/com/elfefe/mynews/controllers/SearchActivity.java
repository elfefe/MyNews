package com.elfefe.mynews.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.SpinnerAdapter;
import com.elfefe.mynews.models.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static String KEY_SEARCH = "SEARCH_BUNDLE";

    EditText text;
    AppCompatSpinner dateEnd,dateBegin;
    AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    Button search;

    List<String> periods = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_query);

        Toolbar toolbar = (Toolbar) findViewById(R.id.query_toolbar);
        setSupportActionBar(toolbar);

        text = (EditText) findViewById(R.id.query_query);
        arts = (AppCompatCheckBox) findViewById(R.id.query_cb_arts);
        buisness = (AppCompatCheckBox) findViewById(R.id.query_cb_buisness);
        entrepreneurs = (AppCompatCheckBox) findViewById(R.id.query_cb_entrepreneurs);
        politics = (AppCompatCheckBox) findViewById(R.id.query_cb_politics);
        sports = (AppCompatCheckBox) findViewById(R.id.querye_cb_sports);
        travel = (AppCompatCheckBox) findViewById(R.id.query_cb_travel);
        search = (Button) findViewById(R.id.query_button_query);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout layoutBegin = (LinearLayout)  findViewById(R.id.query_layout_begin);
        LinearLayout layoutEnd = (LinearLayout)  findViewById(R.id.query_layout_end);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View spinnerViewBegin = inflater.inflate(R.layout.spinner_items,layoutBegin,false);
        View spinnerViewEnd = inflater.inflate(R.layout.spinner_items,layoutEnd,false);

        TextView titleBegin = spinnerViewBegin.findViewById(R.id.spinner_tv);
        TextView titleEnd = spinnerViewEnd.findViewById(R.id.spinner_tv);

        dateBegin = spinnerViewBegin.findViewById(R.id.spinner_elv);
        dateEnd = spinnerViewEnd.findViewById(R.id.spinner_elv);


        titleBegin.setText("Begin date");
        titleEnd.setText("End date");

        periods.add("01/01/2014");
        periods.add("01/01/2015");
        periods.add("01/01/2016");
        periods.add("01/01/2017");
        periods.add("01/01/2018");

        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this,R.layout.spinner_adapter_item,periods);
        ArrayAdapter<String> adapterE = new ArrayAdapter<String>(this,R.layout.spinner_adapter_item,periods);

        SpinnerAdapter adapterBegin = new SpinnerAdapter(this,R.layout.spinner_adapter_item,periods);
        SpinnerAdapter adapterEnd = new SpinnerAdapter(this,R.layout.spinner_adapter_item,periods);

        dateBegin.setAdapter(adapterBegin);
        dateEnd.setAdapter(adapterEnd);

        layoutBegin.addView(spinnerViewBegin, 0);
        layoutEnd.addView(spinnerViewEnd, 0);

    }

    @Override
    protected void onResume() {
        super.onResume();

        dateEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SELECTED", String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("SELECTED", "null");
            }
        });

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
                    dateBegin.getSelectedItem().toString(),
                    dateEnd.getSelectedItem().toString(),
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
