package com.kjstudio.weex.activities;

import android.os.Bundle;

import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.UserData;

public class UserProfileActivity extends BaseActivity {

    UserData mUserData; // 보여줄 사용자의 정보를 들고있는 Data 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setCustomActionbar();
        bindViews();
        setValues();
    }

    @Override
    public void setValues() {
        super.setValues();

        mTitleTextView.setText("Denis"); // 여기에 사용자 이름을 가져와야한다.
    }
}
