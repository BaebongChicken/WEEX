package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.kjstudio.weex.R;
import com.kjstudio.weex.utils.FontChanger;

public class SelectModeActivity extends AppCompatActivity {

    FrameLayout missionLayout;
    FrameLayout findGroupLayout;
    FrameLayout findMemberLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);

        FontChanger.setGlobalFont(FontChanger.setTypeFace(SelectModeActivity.this), SelectModeActivity.this, getWindow().getDecorView());
        bindViews();
        setupEvents();
    }

    private void setupEvents() {
        missionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(SelectModeActivity.this, MainActivity.class);
                mIntent.putExtra("fragementIndex", 0);
                startActivity(mIntent);
            }
        });


        findGroupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(SelectModeActivity.this, MainActivity.class);
                mIntent.putExtra("fragementIndex", 0);
                startActivity(mIntent);
            }
        });


        findMemberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(SelectModeActivity.this, MainActivity.class);
                mIntent.putExtra("fragementIndex", 0);
                startActivity(mIntent);
            }
        });
    }

    private void bindViews() {
        missionLayout = (FrameLayout) findViewById(R.id.missionLayout);
        findGroupLayout = (FrameLayout) findViewById(R.id.findGroupLayout);
        findMemberLayout = (FrameLayout) findViewById(R.id.findMemberLayout);
    }
}
