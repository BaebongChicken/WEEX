package com.kjstudio.weex.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kjstudio.weex.utils.FontChanger;

/**
 * Created by JinHee on 2015-11-30.
 */
public class BaseFragment extends Fragment {

    public static int layoutResId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(layoutResId, container, false);

        FontChanger.setGlobalFont(FontChanger.setTypeFace(getActivity()), getActivity(), v);


        return v;
    }

}
