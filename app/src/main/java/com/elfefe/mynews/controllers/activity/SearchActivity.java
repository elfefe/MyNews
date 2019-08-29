package com.elfefe.mynews.controllers.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.elfefe.mynews.R;
import com.elfefe.mynews.utils.FormatDate;
import com.elfefe.mynews.utils.SearchSpinner;
import com.elfefe.mynews.utils.SectionLuceneFormator;
import com.elfefe.mynews.controllers.adapters.SpinnerAdapter;
import com.elfefe.mynews.models.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static final String KEY_SEARCH = "SEARCH_BUNDLE";

    private EditText text;
    private SearchSpinner dateEnd;
    private SearchSpinner dateBegin;
    private AppCompatCheckBox arts;
    private AppCompatCheckBox buisness;
    private AppCompatCheckBox technology;
    private AppCompatCheckBox health;
    private AppCompatCheckBox sports;
    private AppCompatCheckBox science;
    private Button search;

    private final List<String> periods = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_query);

        Toolbar toolbar = findViewById(R.id.query_toolbar);
        setSupportActionBar(toolbar);

        text = findViewById(R.id.query_query);
        arts = findViewById(R.id.query_cb_arts);
        buisness = findViewById(R.id.query_cb_buisness);
        technology = findViewById(R.id.query_cb_technology);
        health = findViewById(R.id.query_cb_health);
        sports = findViewById(R.id.querye_cb_sports);
        science = findViewById(R.id.query_cb_science);
        search = findViewById(R.id.query_button_search);

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
                if(arts.isChecked()){add("arts");}
                if (buisness.isChecked()){add("business");}
                if (technology.isChecked()){add("technology");}
                if (health.isChecked()){add("health");}
                if (sports.isChecked()){add("sports");}
                if (science.isChecked()){add("science");}
            }};

            FormatDate formatDateBegin = new FormatDate(dateBegin.getSelectedItem());
            FormatDate formatDateEnd = new FormatDate(dateEnd.getSelectedItem());

            SectionLuceneFormator sections = new SectionLuceneFormator(sectionsList);

            Search searchData = new Search(
                    text.getText().toString(),
                    formatDateBegin.date(),
                    formatDateEnd.date(),
                    sections.createLucene()
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
