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

public class InterestedActsFitnessFragment extends BaseFragment {

    public String[] selectionFitnessArray = {"Aerobic", "Marathon", "Walking", "Running", "Yga"};
    public ArrayList<Button> selectFitnessBtnArray = new ArrayList<Button>();
    public ArrayList<LinearLayout> childViewFitnessArray = new ArrayList<LinearLayout>();
    public LinearLayout selectionFitnessLyt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFitnessBtn();
        makeFitnessLayout();
        Log.d("check arrays", "size of Fitness btn Array : " + selectFitnessBtnArray.size());
        Log.d("check arrays", "size of child view Fitness Array : " + childViewFitnessArray.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("check arrays", "View Creating...<Ftn>");
        layoutResId = R.layout.fragment_interested_acts2;
        View v = super.onCreateView(inflater, container, savedInstanceState);
        selectionFitnessLyt = (LinearLayout) v.findViewById(R.id.selectionFitnessLyt);
        selectionFitnessLyt = getCreatedLayout(selectionFitnessLyt);

        return v;
    }

    void makeFitnessBtn() {
        LinearLayout.LayoutParams mBtnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        mBtnParams.setMargins(5, 5, 5, 5);
        for (int j = 0; j < selectionFitnessArray.length; j++) {
            final int mCounter = j;
            Button mBtn = new Button(getActivity());
            mBtn.setLayoutParams(mBtnParams);
            mBtn.setId(mCounter);
            mBtn.setText((mCounter + 1) + " : " + selectionFitnessArray[mCounter]);
            mBtn.setBackgroundResource(R.color.color_primary);
            mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InterestedActsActivity.interestedActsName = selectionFitnessArray[mCounter];
                    Log.d("IA Contents", "IA CONTENTS : <EVENT NAME> " + InterestedActsActivity.interestedActsName);

                    ((InterestedActsActivity) getActivity()).startLevelActivity();
                }
            });
            selectFitnessBtnArray.add(mBtn);

        }
    }

    void makeFitnessLayout() {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int mPortion = (selectionFitnessArray.length / 3 + 1);

        for (int i = 0; i < mPortion; i++) {
            LinearLayout mLayout = new LinearLayout(getActivity());
            mLayout.setLayoutParams(mParams);
            mLayout.setOrientation(LinearLayout.HORIZONTAL);
            childViewFitnessArray.add(mLayout);
        }
    }

    private LinearLayout getCreatedLayout(LinearLayout mLayout) {
        Log.d("check arrays", "setButtonsInLayout processing...START");
        LinearLayout.LayoutParams mViewParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);

        for (int l = 0; l < childViewFitnessArray.size(); l++) {
            Log.d("check arrays", "setButtonsInLayout processing...terms : " + l);
            LinearLayout mView = childViewFitnessArray.get(l);
            for (int k = 0; k < 3; k++) {
                if (selectFitnessBtnArray.size() < (l * 3 + k) + 1) {
                    TextView emptyView = new TextView(getActivity());
                    emptyView.setLayoutParams(mViewParams);
                    mView.addView(emptyView);
                    Log.d("check arrays", "setButtonsInLayout processing...terms : <EMPTY VIEW>" + l + " * " + k);
                } else {
                    mView.addView(selectFitnessBtnArray.get(l * 3 + k));
                    Log.d("check arrays", "setButtonsInLayout processing...terms : " + l + " * " + k);
                }

            }
            mLayout.addView(mView);
        }
        return mLayout;
    }

}
