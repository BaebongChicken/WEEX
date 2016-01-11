package com.kjstudio.weex.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kjstudio.weex.R;
import com.kjstudio.weex.utils.FontChanger;

import java.util.ArrayList;

/**
 * Created by JinHee on 2015-11-26.
 */
public class BaseActivity extends AppCompatActivity {

    public static ArrayList<Activity> activities = new ArrayList<Activity>();

    public static TextView mTitleTextView;
    public static ImageButton rightBtn;
    public static ImageButton leftBtn;
    public static Button stateBtn;
    public static ImageButton searchBtn;
    public static ImageButton searchDetailBtn;
    public static ImageButton moreBtn;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        FontChanger.setGlobalFont(FontChanger.setTypeFace(BaseActivity.this), BaseActivity.this, getWindow().getDecorView());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        Log.d("BaseActivity", "ActvityLists : " + activities);
        if (getSupportActionBar() != null)
            setCustomActionbar();
    }

    public void finishAllActivities(){
        for (int i = 0 ; i < activities.size(); i ++){
            activities.get(i).finish();
        }
    }

    public void setupEvents() {

    }

    public void setupEvents(int stateBtnText) {
        stateBtn.setVisibility(View.VISIBLE);
        stateBtn.setText(stateBtnText);
    }

    public void setupEvents(final Class<?> targetActivity) {
        final Class<?> mTargetActivity = targetActivity;
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), mTargetActivity);
                startActivity(mIntent);
                finish();
            }
        });
    }


    public void bindViews() {
        mTitleTextView = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.titleTxt);
        rightBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.okBtn);
        leftBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.toggleBtn);
        stateBtn = (Button) getSupportActionBar().getCustomView().findViewById(R.id.stateBtn);
        moreBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.moreBtn);
        searchBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.searchBtn);
        searchDetailBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.searchDetailBtn);


    }

    public void setValues(int title) {
        mTitleTextView.setText(title);
        Log.d("Base", "setValue Processing... title : " + mTitleTextView.getText());


    }
    public void setValues() {

    }

    public void setValues(int title, int rightView) {
        mTitleTextView.setText(title);
        rightBtn.setVisibility(rightView);
    }

    public void setValues(int title, int rightView, int leftView) {
        mTitleTextView.setText(title);
        rightBtn.setVisibility(rightView);
        leftBtn.setVisibility(leftView);
    }


    public void setCustomActionbar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

}
