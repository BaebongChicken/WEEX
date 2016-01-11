package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kjstudio.weex.Fragments.InterestedActsFitnessFragment;
import com.kjstudio.weex.Fragments.InterestedActsSportsFragment;
import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.InterestedActsData;

import java.util.ArrayList;

public class InterestedActsActivity extends BaseActivity {

    public ViewPager mViewPager;
    public PagerAdapter mPagerAdapter;
    public static InterestedActsActivity mIAActivity;

    ArrayList<Fragment> fragList = new ArrayList<Fragment>();

    public InterestedActsSportsFragment InterestedActsSportsFragment;
    public InterestedActsFitnessFragment interestedActsFitnessFragment;
    private Button sportsBtn;
    private Button fitnessBtn;
    private int currentBtnNum = 100;

    public static String interestedActsName = "";

    public static String activityName = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_acts);

        currentBtnNum = getIntent().getIntExtra("mBtnNum", 0);
        Log.d("REQUEST_CODE", "(REQUEST_CODE)RECEIVED_1 : " + currentBtnNum);

        setCustomActionbar();
        bindViews();
        setValues(R.string.interestedacts_title, View.VISIBLE);
        setupEvents(R.string.next);
        setViewPager();

        activityName = getIntent().getStringExtra("activityName");

        Log.d("activityName", activityName);
    }

    public void startLevelActivity() {

        Intent mIntent = new Intent(getApplicationContext(), InterestedActsLevelActivity.class);
        InterestedActsData interestedActsData = new InterestedActsData();
        interestedActsData.actsName = interestedActsName;
        mIntent.putExtra("interestedActsData", interestedActsData);
        mIntent.putExtra("mBtnNum", currentBtnNum);
        startActivityForResult(mIntent, currentBtnNum);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Intent mIntent = new Intent();

            mIntent.putExtra("interestedActsData", data.getSerializableExtra("interestedActsData"));
            setResult(RESULT_OK, mIntent);
            finish();
        }
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.InterestedActsSportsFragment = new InterestedActsSportsFragment();
        this.interestedActsFitnessFragment = new InterestedActsFitnessFragment();
        addFragments();
        mIAActivity = InterestedActsActivity.this;
        this.mViewPager = (ViewPager) findViewById(R.id.viewpager);
        this.mPagerAdapter = new PageAdapter(getSupportFragmentManager());
        this.mViewPager.setAdapter(mPagerAdapter);
        this.fitnessBtn = (Button) findViewById(R.id.fitnessBtn);
        this.sportsBtn = (Button) findViewById(R.id.sportsBtn);
    }

    @Override
    public void setupEvents(int stateBtnText) {
        super.setupEvents(stateBtnText);
        sportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        fitnessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });

        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), InterestedActsLevelActivity.class);
                mIntent.putExtra("mBtnNum",currentBtnNum);
                startActivityForResult(mIntent, currentBtnNum);
            }
        });
    }


    public void setViewPager() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        sportsBtn.setBackgroundColor(getResources().getColor(R.color.color_background_white));
                        sportsBtn.setTextColor(getResources().getColor(R.color.color_text_primary));
                        fitnessBtn.setBackgroundColor(getResources().getColor(R.color.color_primary));
                        fitnessBtn.setTextColor(getResources().getColor(R.color.color_text_primary_inverse));
                        break;
                    case 1:
                        sportsBtn.setBackgroundColor(getResources().getColor(R.color.color_primary));
                        sportsBtn.setTextColor(getResources().getColor(R.color.color_text_primary_inverse));
                        fitnessBtn.setBackgroundColor(getResources().getColor(R.color.color_background_white));
                        fitnessBtn.setTextColor(getResources().getColor(R.color.color_text_primary));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addFragments() {
        fragList.add(InterestedActsSportsFragment);
        fragList.add(interestedActsFitnessFragment);
    }

    private class PageAdapter extends FragmentStatePagerAdapter {
        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            Fragment finalFrag = null;
            finalFrag = fragList.get(position);
            return finalFrag;
        }

        @Override
        public int getCount() {
            return fragList.size();  // 총 .size()개의 page를 보여줍니다.
        }

    }
}




