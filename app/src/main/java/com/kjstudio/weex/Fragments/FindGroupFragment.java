package com.kjstudio.weex.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.kjstudio.weex.R;
import com.kjstudio.weex.adapter.GroupAdapter;
import com.kjstudio.weex.dataClass.GroupData;

import java.util.ArrayList;

/**
 * Created by KJ_Studio on 2015-12-05.
 */
public class FindGroupFragment extends BaseFragment {

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;

    ListView groupListView;
    ArrayList<GroupData> mGroupList = new ArrayList<>();

    FrameLayout lightningLayout;
    FrameLayout clubLayout;
    FrameLayout placeLayout;

    TextView lightningIndicator;
    TextView clubIndicator;
    TextView placeIndicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutResId = R.layout.fragment_find_group;
        View v = super.onCreateView(inflater, container, savedInstanceState);
        lightningLayout = (FrameLayout) v.findViewById(R.id.lightningLayout);
        clubLayout = (FrameLayout) v.findViewById(R.id.clubLayout);
        placeLayout = (FrameLayout) v.findViewById(R.id.placeLayout);
        groupListView = (ListView) v.findViewById(R.id.groupListView);

        lightningIndicator = (TextView) v.findViewById(R.id.lightningIndicator);
        clubIndicator = (TextView) v.findViewById(R.id.clubIndicator);
        placeIndicator = (TextView) v.findViewById(R.id.placeIndicator);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mGroupList.add(new GroupData());
        mGroupList.add(new GroupData());
        mGroupList.add(new GroupData());
        mGroupList.add(new GroupData());
        mGroupList.add(new GroupData());
        mGroupList.add(new GroupData());
        mGroupList.add(new GroupData());

        groupListView.setAdapter(new GroupAdapter(getActivity(), mGroupList));

        setupEvents();

    }

    public void showLocationActivity() {

        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(getActivity());
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to date. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that the result was from the autocomplete widget.
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == getActivity().RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.i("CHO", "Place Selected: " + place.getName() + " / " + place.getLatLng().toString());

                // Format the place's details and display them in the TextView.
//                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
//                        place.getId(), place.getAddress(), place.getPhoneNumber(),
//                        place.getWebsiteUri()));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.e("CHO", "Error: Status = " + status.toString());
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    private void setupEvents() {
        lightningLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIndicators(0);
            }
        });
        clubLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIndicators(1);
            }
        });
        placeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIndicators(2);
            }
        });
    }

    void setIndicators(int index) {

        if (index == 0) {

            lightningIndicator.setVisibility(View.VISIBLE);
            clubIndicator.setVisibility(View.GONE);
            placeIndicator.setVisibility(View.GONE);
        }
        else if (index == 1) {

            lightningIndicator.setVisibility(View.GONE);
            clubIndicator.setVisibility(View.VISIBLE);
            placeIndicator.setVisibility(View.GONE);
        }
        else if (index == 2) {

            lightningIndicator.setVisibility(View.GONE);
            clubIndicator.setVisibility(View.GONE);
            placeIndicator.setVisibility(View.VISIBLE);
        }
    }
}
