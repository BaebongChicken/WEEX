package com.kjstudio.weex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kjstudio.weex.R;
import com.kjstudio.weex.activities.UserProfileActivity;
import com.kjstudio.weex.dataClass.UserData;

import java.util.ArrayList;

/**
 * Created by KJ_Studio on 2015-12-08.
 */
public class MemberAdapter extends BaseCustomAdapter<UserData>
{

    ArrayList<UserData> mList;
    LayoutInflater inf;

    public MemberAdapter(Context context, ArrayList<UserData> list) {
        super(context, R.layout.member_list_item_1, R.id.memberNameTxt, list);

        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (position % 2 == 1) {

            if (row == null) {
                row = inf.inflate(R.layout.member_list_item_1, null);
                setGlobalFont(row);

            }
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, UserProfileActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
        }
        else {

            if (row == null) {
                row = inf.inflate(R.layout.member_list_item_2, null);
                setGlobalFont(row);
            }

            FrameLayout rootLayoutLeft = (FrameLayout) row.findViewById(R.id.rootLayoutLeft);
            rootLayoutLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mIntent = new Intent(mContext, UserProfileActivity.class);
                    mContext.startActivity(mIntent);
                }
            });

            FrameLayout rootLayoutRight = (FrameLayout) row.findViewById(R.id.rootLayoutRight);
            rootLayoutRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mIntent = new Intent(mContext, UserProfileActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
            if (mList.size() % 3 == 1)
            {
                rootLayoutRight.setVisibility(View.INVISIBLE);
            }
        }

        return row;
    }

    @Override
    public int getCount() {
        if (mList.size() % 3 == 0) {

            return mList.size() / 3 * 2;
        }
        else {

            return mList.size() / 3 * 2 +1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0) {

            return 0;
        }
        else {

            return 1;
        }

    }
}