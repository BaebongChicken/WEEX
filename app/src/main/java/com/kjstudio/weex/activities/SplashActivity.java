package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kjstudio.weex.R;
import com.kjstudio.weex.utils.ContextUtil;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("Log", "3sec");
                startAppropriateActivity();
            }
        }, 1500);
    }

    void startAppropriateActivity() {
        if (ContextUtil.isUserLoggedin(SplashActivity.this)) {
//            Intent mIntent = new Intent(SplashActivity.this, SelectPayMethodActivity.class);
//            startActivity(mIntent);
//            finish();
        }
        else {
            Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(mIntent);
            finish();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
