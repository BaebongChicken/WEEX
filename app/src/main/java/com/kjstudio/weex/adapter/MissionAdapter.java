package com.kjstudio.weex.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kjstudio.weex.R;
import com.kjstudio.weex.activities.MissionDetailActivity;
import com.kjstudio.weex.dataClass.MissionData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by KJ_Studio on 2015-12-07.
 */
public class MissionAdapter extends BaseCustomAdapter<MissionData> {


    ArrayList<MissionData> mList;
    LayoutInflater inf;
    MissionData mMissionData;

    public MissionAdapter(Context context, ArrayList<MissionData> list) {
        super(context, R.layout.mission_list_item_1, R.id.titleTxt, list);


        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (position % 2 == 1) {

            if (row == null) {
                row = inf.inflate(R.layout.mission_list_item_1, null);

                TextView mTitle = (TextView) row.findViewById(R.id.titleTxt);
                final ImageView mBackground = (ImageView) row.findViewById(R.id.missionBackgroundImgView);
                mTitle.setText(mList.get((position / 2) * 3 + 2).title);
                setBackgroundByImgPath(mList.get((position / 2) * 3 + 2).imagePath, mBackground);

                setGlobalFont(row);
            }
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, MissionDetailActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
        } else {
            FrameLayout missionLayoutLeft = null;
            FrameLayout missionLayoutRight = null;
            if (row == null) {
                row = inf.inflate(R.layout.mission_list_item_2, null);
                missionLayoutLeft = (FrameLayout) row.findViewById(R.id.missionLayoutLeft);
                missionLayoutRight = (FrameLayout) row.findViewById(R.id.missionLayoutRight);

                TextView mTitleLeft = (TextView) row.findViewById(R.id.missionTitleTxtLeft);
                mTitleLeft.setText(mList.get((position / 2) * 3).title);
                final ImageView mBackgroundLeft = (ImageView) row.findViewById(R.id.missionBackgroundImgViewLeft);
                setBackgroundByImgPath(mList.get((position / 2) * 3).imagePath, mBackgroundLeft);

                try {
                    TextView mTitleRight = (TextView) row.findViewById(R.id.missionTitleTxtRight);
                    mTitleRight.setText(mList.get((position / 2) * 3 + 1).title);
                    final ImageView mBackgroundRight = (ImageView) row.findViewById(R.id.missionBackgroundImgViewRight);
                    setBackgroundByImgPath(mList.get((position / 2) * 3 + 1).imagePath, mBackgroundRight);
                } catch (IndexOutOfBoundsException e) {
                    missionLayoutRight.setVisibility(View.INVISIBLE);
                }

                setGlobalFont(row);

            } else {
                missionLayoutLeft = (FrameLayout) row.findViewById(R.id.missionLayoutLeft);
                missionLayoutRight = (FrameLayout) row.findViewById(R.id.missionLayoutRight);
            }



            missionLayoutLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mIntent = new Intent(mContext, MissionDetailActivity.class);
                    mContext.startActivity(mIntent);
                }
            });

            missionLayoutRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mIntent = new Intent(mContext, MissionDetailActivity.class);
                    mContext.startActivity(mIntent);
                }
            });

        }

        return row;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        return position % 2;
    }

    @Override
    public int getCount() {
        if (mList.size() % 3 == 0) {

            return mList.size() / 3 * 2;
        } else {

            return mList.size() / 3 * 2 + 1;
        }
    }

    private void setBackgroundByImgPath(String imgPath, final ImageView mBackground) {
        ImageLoader.getInstance().loadImage("http://yohun92.cafe24.com/ci/images/"+imgPath, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                mBackground.setImageBitmap(bitmap);
                mBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

    }
}
