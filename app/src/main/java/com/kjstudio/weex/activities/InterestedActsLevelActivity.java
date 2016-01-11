package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.InterestedActsData;

import java.util.ArrayList;

public class InterestedActsLevelActivity extends BaseActivity {

    private int currentBtnNum = 100;
    InterestedActsActivity mContext;
    private Button levelProBtn;
    private Button levelAmateurBtn;
    private Button levelOkayBtn;
    private Button levelBeginnerBtn;
    private Button levelNoneBtn;
    private ArrayList<Button> levelBtnArray = new ArrayList<Button>();

    public static String interestedActsLevel = "";

    InterestedActsData interestedActsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_act_level);
        currentBtnNum = getIntent().getIntExtra("mBtnNum", 0);
        Log.d("REQUEST_CODE", "(REQUEST_CODE)RECEIVED_2 : " + currentBtnNum);

        setCustomActionbar();
        bindViews();
        interestedActsData = (InterestedActsData) getIntent().getSerializableExtra("interestedActsData");
        setValues(R.string.interestedactslevel_title);
        setupEvents(R.string.complete);

    }

    @Override
    public void setupEvents(int stateBtnText) {
        super.setupEvents(stateBtnText);
        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mIntent = new Intent(getApplicationContext(),SignUp2Activity.class);
//                mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(mIntent);
//Flag로 하면 깔끔하지만, requestCode가 안넘어감.  finish를 직접날려줘야 리퀘스트코드가 반응하는듯
                setDataInputEvents();

//                mContext.finish();
                setResult(RESULT_OK);
                finish();

            }
        });
        setLevelBtnToArray();
        setLevelBtnEvents();

    }

    void setDataInputEvents() {
        InterestedActsData mInterestedActsData = new InterestedActsData(InterestedActsActivity.interestedActsName, interestedActsLevel);

        SignUp2Activity.interestedActsDataArray.add(mInterestedActsData);
    }

    void setLevelBtnToArray() {
        levelBtnArray.add(levelAmateurBtn);
        levelBtnArray.add(levelBeginnerBtn);
        levelBtnArray.add(levelNoneBtn);
        levelBtnArray.add(levelOkayBtn);
        levelBtnArray.add(levelProBtn);
    }

    void setLevelBtnEvents() {
        for (int i = 0; i < levelBtnArray.size(); i++) {
            final Button mBtn = levelBtnArray.get(i);
            mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interestedActsLevel = mBtn.getText().toString();
                    Log.d("IA Contents", "IA CONTENTS : <EVENT LEVEL> " + interestedActsLevel);

                    interestedActsData.actsLevel = mBtn.getText().toString();

                    Intent mIntent = new Intent();
                    mIntent.putExtra("interestedActsData", interestedActsData);
                    setResult(RESULT_OK, mIntent);
                    finish();
                }
            });
        }
    }


    @Override
    public void bindViews() {
        super.bindViews();

        this.mContext = InterestedActsActivity.mIAActivity;

        this.levelNoneBtn = (Button) findViewById(R.id.levelNoneBtn);
        this.levelBeginnerBtn = (Button) findViewById(R.id.levelBeginnerBtn);
        this.levelOkayBtn = (Button) findViewById(R.id.levelOkayBtn);
        this.levelAmateurBtn = (Button) findViewById(R.id.levelAmateurBtn);
        this.levelProBtn = (Button) findViewById(R.id.levelProBtn);
        setLevelBtnToArray();

    }
}
