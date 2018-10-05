package com.example.locnt.app_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentTabHost mTabHost;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        LayoutInflater inflater = getLayoutInflater();
        View tab1 = inflater.inflate(R.layout.home_tab,null);
        View tab2 = inflater.inflate(R.layout.book_tab,null);
        View tab3 = inflater.inflate(R.layout.search_tab,null);

        mTabHost.addTab(mTabHost.newTabSpec("tab1")
                        .setIndicator(tab1),
                HomeFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab2")
                        .setIndicator(tab2),
                LocationFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("tab3")
                        .setIndicator(tab3),
                SearchFragment.class, null);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        setNavigationViewListener();
    }

    private TabHost.OnTabChangeListener onTab_Changed = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            switch (tabId){
                case "tab1":
                    getSupportActionBar().setTitle("Trang Chủ");
                    break;
                case "tab2":
                    getSupportActionBar().setTitle("Sân Bóng Gần Đây");
                    break;
                case "tab3":
                    getSupportActionBar().setTitle("Tìm Kiếm");
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_info: {
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_history: {
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            }
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
