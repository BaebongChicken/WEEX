package com.kjstudio.weex.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.kjstudio.weex.R;


public class FindLocationActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private AutoCompleteTextView locationEdt;
    private ListView locationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomActionbar();
        setContentView(R.layout.activity_find_location);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        bindViews();
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        locationEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                PendingResult result =
//                        Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, s.toString(), LatLngBounds.builder().build());
//
//                Log.d("result", result.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void bindViews() {
        super.bindViews();

        this.locationListView = (ListView) findViewById(R.id.locationListView);
        this.locationEdt = (AutoCompleteTextView) findViewById(R.id.locationEdt);
    }

    @Override
    public void setValues() {
        super.setValues();
        mTitleTextView.setText(R.string.FindLocationActivity_title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("onConnected", "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("onConnectionSuspended", "onConnectionSuspended = " + i);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("onConnectionFailed", connectionResult.toString());

    }
}
