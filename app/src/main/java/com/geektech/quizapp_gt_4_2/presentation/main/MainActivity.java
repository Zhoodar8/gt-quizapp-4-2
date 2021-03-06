package com.geektech.quizapp_gt_4_2.presentation.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreActivity;
import com.geektech.quizapp_gt_4_2.presentation.history.HistoryFragment;
import com.geektech.quizapp_gt_4_2.presentation.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends CoreActivity {

    private MainViewModel mViewModel;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView mBottomNavigation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        iniViews();
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        setUpbottomNavigation();
    }

    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    private void setUpbottomNavigation() {
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.explore:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.map:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.active:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //   mBottomNavigation.getMenu().getItem(position).setChecked(true);
                switch (position) {
                    case 0:
                        mBottomNavigation.getMenu().findItem(R.id.explore).setChecked(true);
                        break;

                    case 1:
                        mBottomNavigation.getMenu().findItem(R.id.map).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigation.getMenu().findItem(R.id.active).setChecked(true);
                        break; }
            }


        });
    }

    private void iniViews() {
        mViewPager = findViewById(R.id.main_view_pager);
        mBottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = HistoryFragment.newInstance();
                    break;
                default:
                    fragment = SettingsFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() != 0){
            mViewPager.setCurrentItem(0);
        }else {
        super.onBackPressed();}
    }
}

