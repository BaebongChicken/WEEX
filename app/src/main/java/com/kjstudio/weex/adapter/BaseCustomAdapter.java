package com.kjstudio.weex.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

import com.kjstudio.weex.utils.FontChanger;

/**
 * Created by KJ_Studio on 2015-12-08.
 */
public abstract class BaseCustomAdapter<T> extends ArrayAdapter<T> {
    static Context mContext;

    public BaseCustomAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;

    }

    public void setGlobalFont(View mConvertView){
        Typeface mFont = FontChanger.setTypeFace(mContext);
        FontChanger.setGlobalFont(mFont, mContext, mConvertView);
    }
}
