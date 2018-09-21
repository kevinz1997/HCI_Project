package com.example.locnt.app_project;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends AppCompatActivity {
        private FragmentTabHost mTabHost;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().hide();
            mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
//            mTabHost.getTabWidget().setShowDividers(TabWidget.SHOW_DIVIDER_MIDDLE);
            mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//            mTabHost.setOnTabChangedListener(onTab_Changed);

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
}
