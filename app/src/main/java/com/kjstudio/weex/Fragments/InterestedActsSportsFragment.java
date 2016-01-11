package com.kjstudio.weex.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kjstudio.weex.R;
import com.kjstudio.weex.activities.InterestedActsActivity;

import java.util.ArrayList;

public class InterestedActsSportsFragment extends BaseFragment {

    public String[] selectionSportsArray = {"soccer", "baseball", "boarding", "skydiving"};
    public ArrayList<Button> selectSportsBtnArray = new ArrayList<Button>();
    public ArrayList<LinearLayout> childViewSportsArray = new ArrayList<LinearLayout>();
    public LinearLayout selectionSportsLyt;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        makeSportsBtn();
        makeSportsLayout();
        Log.d("check arrays", "size of sports btn Array : " + selectSportsBtnArray.size());
        Log.d("check arrays", "size of child view Sports Array : " + childViewSportsArray.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("check arrays", "View Creating...<Spt>");
        layoutResId = R.layout.fragment_interested_acts1;
        View v = super.onCreateView(inflater, container, savedInstanceState);
        selectionSportsLyt = (LinearLayout) v.findViewById(R.id.selectionSportsLyt);
        selectionSportsLyt = getCreatedLayout(selectionSportsLyt);
        return v;
    }


    void makeSportsBtn() {
        LinearLayout.LayoutParams mBtnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        mBtnParams.setMargins(5, 5, 5, 5);
        for (int j = 0; j < selectionSportsArray.length; j++) {
            final int mCounter = j;
            Button mBtn = new Button(getActivity());
            mBtn.setLayoutParams(mBtnParams);
            mBtn.setId(mCounter);
            mBtn.setText((mCounter + 1) + " : " + selectionSportsArray[mCounter]);
            mBtn.setBackgroundResource(R.color.color_primary);
            mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InterestedActsActivity.interestedActsName = selectionSportsArray[mCounter];
                    Log.d("IA Contents", "IA CONTENTS : <EVENT NAME> " + InterestedActsActivity.interestedActsName);

                    ((InterestedActsActivity) getActivity()).startLevelActivity();


                }
            });
            selectSportsBtnArray.add(mBtn);

        }
    }

    void makeSportsLayout() {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int mPortion = (selectionSportsArray.length / 3 + 1);

        for (int i = 0; i < mPortion; i++) {
            LinearLayout mLayout = new LinearLayout(getActivity());
            mLayout.setLayoutParams(mParams);
            mLayout.setOrientation(LinearLayout.HORIZONTAL);
            childViewSportsArray.add(mLayout);
        }
    }

    private LinearLayout getCreatedLayout(LinearLayout mLayout) {
        Log.d("check arrays", "setButtonsInLayout processing...START");
        LinearLayout.LayoutParams mViewParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);

        for (int l = 0; l < childViewSportsArray.size(); l++) {
            Log.d("check arrays", "setButtonsInLayout processing...terms : " + l);
            LinearLayout mView = childViewSportsArray.get(l);
            for (int k = 0; k < 3; k++) {
                if (selectSportsBtnArray.size() < (l * 3 + k) + 1) {
                    TextView emptyView = new TextView(getActivity());
                    emptyView.setLayoutParams(mViewParams);
                    mView.addView(emptyView);
                    Log.d("check arrays", "setButtonsInLayout processing...terms : <EMPTY VIEW>" + l + " * " + k);
                } else {
                    mView.addView(selectSportsBtnArray.get(l * 3 + k));
                    Log.d("check arrays", "setButtonsInLayout processing...terms : " + l + " * " + k);
                }

            }
            mLayout.addView(mView);
        }
        return mLayout;

//        selectionSportsLyt.addView(mLayout);
    }
}
