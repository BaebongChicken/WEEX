package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.GroupData;
import com.kjstudio.weex.utils.ContextUtil;

import java.util.ArrayList;

public class SelectGroupTypeActivity extends BaseActivity {

    private android.widget.TextView vsModeTxt;
    private android.widget.TextView schoolTxt;
    private android.widget.TextView publicPlaceTxt;
    private android.widget.TextView lightningTxt;
    private android.widget.TextView companyTxt;
    private android.widget.TextView privatePlaceTxt;
    private android.widget.TextView lessonOrEventTxt;
    private android.widget.TextView normalClubTxt;
    private android.widget.TextView excersiseToolTxt;

    GroupData mGroupData = new GroupData();
    ArrayList<String> categories = ContextUtil.getGroupCategoryNameArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group_type);
        setCustomActionbar();
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.excersiseToolTxt = (TextView) findViewById(R.id.excersiseToolTxt);
        this.normalClubTxt = (TextView) findViewById(R.id.normalClubTxt);
        this.lessonOrEventTxt = (TextView) findViewById(R.id.lessonOrEventTxt);
        this.privatePlaceTxt = (TextView) findViewById(R.id.privatePlaceTxt);
        this.companyTxt = (TextView) findViewById(R.id.companyTxt);
        this.lightningTxt = (TextView) findViewById(R.id.lightningTxt);
        this.publicPlaceTxt = (TextView) findViewById(R.id.publicPlaceTxt);
        this.schoolTxt = (TextView) findViewById(R.id.schoolTxt);
        this.vsModeTxt = (TextView) findViewById(R.id.vsModeTxt);
    }

    @Override
    public void setValues() {
        super.setValues();
        mTitleTextView.setText(R.string.페이지_만들기);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        View.OnClickListener selector = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectGroupTypeActivity.this, EditGroupActivity.class);
                mGroupData.category = categories.get(Integer.parseInt(v.getTag().toString()));
                intent.putExtra("groupData", mGroupData);
                startActivity(intent);
            }
        };

        vsModeTxt.setOnClickListener(selector);
        lightningTxt.setOnClickListener(selector);
        lessonOrEventTxt.setOnClickListener(selector);
        schoolTxt.setOnClickListener(selector);
        companyTxt.setOnClickListener(selector);
        normalClubTxt.setOnClickListener(selector);
        publicPlaceTxt.setOnClickListener(selector);
        privatePlaceTxt.setOnClickListener(selector);
        excersiseToolTxt.setOnClickListener(selector);


    }
}
