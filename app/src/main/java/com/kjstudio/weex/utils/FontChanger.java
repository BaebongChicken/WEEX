package com.kjstudio.weex.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JinHee on 2015-11-30.
 */
public class FontChanger {

    public static Typeface setTypeFace(Context context){
        Typeface mFont;
        mFont = Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic.ttf"); // 외부폰트 사용
        return mFont;
    }

    public static void setGlobalFont(Typeface mTypeface, Context context, View view) {

        if (view != null) {
            if(view instanceof ViewGroup){
                ViewGroup vg = (ViewGroup)view;
                int vgCnt = vg.getChildCount();
                for(int i=0; i < vgCnt; i++){
                    View v = vg.getChildAt(i);
                    if(v instanceof TextView){
                        ((TextView) v).setTypeface(mTypeface);
                    }
                    setGlobalFont(mTypeface, context,v);
                }
            }
        }
    }
}
