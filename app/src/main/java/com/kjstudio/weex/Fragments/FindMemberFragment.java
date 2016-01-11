package com.kjstudio.weex.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kjstudio.weex.R;
import com.kjstudio.weex.adapter.MemberAdapter;
import com.kjstudio.weex.dataClass.UserData;

import java.util.ArrayList;

/**
 * Created by KJ_Studio on 2015-12-05.
 */
public class FindMemberFragment extends BaseFragment {

    ListView memberListView;
    ArrayList<UserData> mUserData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutResId = R.layout.fragment_find_member;
        View v = super.onCreateView(inflater, container, savedInstanceState);
        memberListView = (ListView) v.findViewById(R.id.memberListView);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());
        mUserData.add(new UserData());

        memberListView.setAdapter(new MemberAdapter(getActivity(), mUserData));


    }
}
