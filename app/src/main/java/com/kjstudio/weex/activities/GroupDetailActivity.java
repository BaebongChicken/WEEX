package com.kjstudio.weex.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kjstudio.weex.Fragments.WorkaroundMapFragment;
import com.kjstudio.weex.R;

public class GroupDetailActivity extends BaseActivity {

    private android.widget.TextView groupNameTxt;
    private android.widget.TextView numOfMemberTxt;
    private android.widget.TextView hostMessageTxt;
    private android.widget.LinearLayout pageControllerLayout;
    private android.widget.TextView dateAndTimeTxt;
    private android.widget.TextView locationTxt;
    private android.widget.ImageView checkAllMemberImageView;
    private android.widget.FrameLayout googleMapLayout;
    private android.widget.LinearLayout replyLayout;

    private MapFragment mapFragment;
    private GoogleMap mMap;
    private android.widget.ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        // 살려놨땅!!

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void bindViews() {
        super.bindViews();

        this.mScrollView = (ScrollView) findViewById(R.id.mScrollView);
        this.replyLayout = (LinearLayout) findViewById(R.id.replyLayout);
        this.googleMapLayout = (FrameLayout) findViewById(R.id.googleMapLayout);
        this.checkAllMemberImageView = (ImageView) findViewById(R.id.checkAllMemberImageView);
        this.locationTxt = (TextView) findViewById(R.id.locationTxt);
        this.dateAndTimeTxt = (TextView) findViewById(R.id.dateAndTimeTxt);
        this.pageControllerLayout = (LinearLayout) findViewById(R.id.pageControllerLayout);
        this.hostMessageTxt = (TextView) findViewById(R.id.hostMessageTxt);
        this.numOfMemberTxt = (TextView) findViewById(R.id.numOfMemberTxt);
        this.groupNameTxt = (TextView) findViewById(R.id.groupNameTxt);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;

                LatLng sydney = new LatLng(-33.867, 151.206);
                if (ActivityCompat.checkSelfPermission(GroupDetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GroupDetailActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

                googleMap.addMarker(new MarkerOptions()
                        .title("Sydney")
                        .snippet("The most populous city in Australia.")
                        .position(sydney));

                googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {
                        Log.d("CHO", "카메라가 움직여유!");
                    }
                });

                ((WorkaroundMapFragment) getFragmentManager().findFragmentById(R.id.map)).setListener(new WorkaroundMapFragment.OnTouchListener() {
                    @Override
                    public void onTouch() {
                        mScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                });
            }
        });



    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        checkAllMemberImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        searchDetailBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mIntent = new Intent(GroupDetailActivity.this, pageFilteringActivity.class);
//                startActivity(mIntent);
//            }
//        });

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(GroupDetailActivity.this, moreBtn);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popupmenu_for_group, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals(getResources().getString(R.string.report))) {
                            Toast.makeText(GroupDetailActivity.this, "신고 접수 기능을 구현합니다.", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        moreBtn.setVisibility(View.VISIBLE);
//        searchDetailBtn.setVisibility(View.VISIBLE);
    }
}
