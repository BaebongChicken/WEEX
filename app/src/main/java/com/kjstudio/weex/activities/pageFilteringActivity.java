package com.kjstudio.weex.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kjstudio.weex.R;


public class pageFilteringActivity extends BaseActivity {


    private Button applyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_filtering);

        setCustomActionbar();
        bindViews();
        setValues(R.string.pagefiltering_title);

        setupEvents();
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.applyBtn = (Button) findViewById(R.id.applyBtn);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
