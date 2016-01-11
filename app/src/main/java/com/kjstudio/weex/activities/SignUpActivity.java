package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.UserData;
import com.kjstudio.weex.utils.ContextUtil;
import com.kjstudio.weex.utils.ServerUtil;

import org.json.JSONObject;

public class SignUpActivity extends BaseActivity {

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;

    private Button nextBtn;
    private Button yesBtn;
    private Button noBtn;
    private EditText nameEdt;
    private Button locationBtn;

    UserData mUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUserData = ContextUtil.getUserData(SignUpActivity.this);
        setCustomActionbar();
        bindViews();
        setValues(R.string.signup_title);
        setupEvents();

    }

    @Override
    public void setValues(int title) {
        super.setValues(title);

        nameEdt.setText(mUserData.name);
        locationBtn.setText(mUserData.location);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserData.isTrainer = true;

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserData.isTrainer = false;
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(SignUpActivity.this);
                    startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeUserData();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(SignUpActivity.this, data);
                Log.i("CHO", "Place Selected: " + place.getName() + " / " + place.getLatLng().latitude + "," + place.getLatLng().longitude);

                locationBtn.setText(place.getName());
                mUserData.location = place.getName().toString();
                mUserData.geoLocation = place.getLatLng().latitude + "," + place.getLatLng().longitude;
                // Format the place's details and display them in the TextView.
//                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
//                        place.getId(), place.getAddress(), place.getPhoneNumber(),
//                        place.getWebsiteUri()));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(SignUpActivity.this, data);
                Log.e("CHO", "Error: Status = " + status.toString());
            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    private void makeUserData() {
        if (nameEdt.getText().toString().equals("")) {
            Toast.makeText(SignUpActivity.this, R.string.이름을_입력해_주세요, Toast.LENGTH_SHORT).show();
            return;
        }
        mUserData.name = nameEdt.getText().toString();

        if (locationBtn.getText().toString().equals("")) {

            Toast.makeText(SignUpActivity.this, R.string.장소를_입력해주세요, Toast.LENGTH_SHORT).show();
            return;
        }

        ServerUtil.registerUser(SignUpActivity.this, mUserData, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                ContextUtil.setUserData(SignUpActivity.this, mUserData);
                Intent mIntent = new Intent(getApplicationContext(), SignUp2Activity.class);
                startActivity(mIntent);
                finish();
            }
        });

    }

    @Override
    public void bindViews() {
        super.bindViews();

        this.nextBtn = (Button) findViewById(R.id.nextBtn);
        this.noBtn = (Button) findViewById(R.id.noBtn);
        this.yesBtn = (Button) findViewById(R.id.yesBtn);
        this.locationBtn = (Button) findViewById(R.id.locationBtn);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);

    }

}
