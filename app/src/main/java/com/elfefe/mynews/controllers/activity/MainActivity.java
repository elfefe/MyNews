package com.elfefe.mynews.controllers.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MainFragment mainFragment;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        NavigationView navigationView = findViewById(R.id.main_navigationview);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);

        if(mainFragment == null){
            mainFragment = MainFragment.newInstance(0);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.framelayout_main, mainFragment)
                    .commit();
        }

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_notif:
                startActivity(new Intent(this,NotificationActivity.class));
                return true;
            case R.id.main_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.topstory:
                updateFragment(0);
                return true;
            case R.id.mostpopular:
                updateFragment(1);
                return true;
            case R.id.favorite:
                updateFragment(2);
                return true;
            case R.id.notification:
                startActivity(new Intent(this,NotificationActivity.class));
                return true;
            default:
                return false;
        }
    }

    private void updateFragment(int page){
        mainFragment = MainFragment.newInstance(page);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, mainFragment).commit();
    }
}
