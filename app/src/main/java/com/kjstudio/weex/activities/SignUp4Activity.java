package com.kjstudio.weex.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kjstudio.weex.R;
import com.kjstudio.weex.utils.RoundedAvatarDrawable;

import java.util.ArrayList;

public class SignUp4Activity extends BaseActivity {

    private ImageView signupPhotoBtn1;
    private ImageView signupPhotoBtn2;
    private ImageView signupPhotoBtn3;
    private ImageView signupPhotoBtn4;
    private Button startBtn;
    private ArrayList<ImageView> galleryBtnArray = new ArrayList<ImageView>();
    private TextView modifyPhotoBtn1;
    private TextView removePhotoBtn1;
    private TextView modifyPhotoBtn2;
    private TextView removePhotoBtn2;
    private TextView modifyPhotoBtn3;
    private TextView removePhotoBtn3;
    private TextView modifyPhotoBtn4;
    private TextView removePhotoBtn4;

    private ArrayList<TextView> modifyBtnArray = new ArrayList<TextView>();
    private ArrayList<TextView> removeBtnArray = new ArrayList<TextView>();
    private Bitmap mBitmapArray[] = {null, null, null, null};
    private Drawable plusImg=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4);


        setCustomActionbar();
        bindViews();
        setValues(R.string.signup4_title);
        setupEvents();
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.startBtn = (Button) findViewById(R.id.startBtn);
        this.signupPhotoBtn4 = (ImageView) findViewById(R.id.signupPhotoBtn4);
        this.signupPhotoBtn3 = (ImageView) findViewById(R.id.signupPhotoBtn3);
        this.signupPhotoBtn2 = (ImageView) findViewById(R.id.signupPhotoBtn2);
        this.signupPhotoBtn1 = (ImageView) findViewById(R.id.signupPhotoBtn1);
        this.removePhotoBtn4 = (TextView) findViewById(R.id.removePhotoBtn4);
        this.modifyPhotoBtn4 = (TextView) findViewById(R.id.modifyPhotoBtn4);
        this.removePhotoBtn3 = (TextView) findViewById(R.id.removePhotoBtn3);
        this.modifyPhotoBtn3 = (TextView) findViewById(R.id.modifyPhotoBtn3);
        this.removePhotoBtn2 = (TextView) findViewById(R.id.removePhotoBtn2);
        this.modifyPhotoBtn2 = (TextView) findViewById(R.id.modifyPhotoBtn2);
        this.removePhotoBtn1 = (TextView) findViewById(R.id.removePhotoBtn1);
        this.modifyPhotoBtn1 = (TextView) findViewById(R.id.modifyPhotoBtn1);
        this.plusImg = getResources().getDrawable(R.drawable.check3);
        setButtonsToArray();

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                finishAllActivities();
                startActivity(mIntent);
//                finish();
            }
        });

        setGalleryCallEvent();
    }

    private void setButtonsToArray() {
        galleryBtnArray.add(signupPhotoBtn1);
        galleryBtnArray.add(signupPhotoBtn2);
        galleryBtnArray.add(signupPhotoBtn3);
        galleryBtnArray.add(signupPhotoBtn4);

        modifyBtnArray.add(modifyPhotoBtn1);
        modifyBtnArray.add(modifyPhotoBtn2);
        modifyBtnArray.add(modifyPhotoBtn3);
        modifyBtnArray.add(modifyPhotoBtn4);

        removeBtnArray.add(removePhotoBtn1);
        removeBtnArray.add(removePhotoBtn2);
        removeBtnArray.add(removePhotoBtn3);
        removeBtnArray.add(removePhotoBtn4);
    }


    private void setGalleryCallEvent() {

        for (int i = 0; i < galleryBtnArray.size(); i++) {
            final int mBtnNum = i;

            galleryBtnArray.get(mBtnNum).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBitmapArray[mBtnNum] == null) {
                        Intent mIntent = new Intent();
                        mIntent.setType("image/*");
                        mIntent.setAction(Intent.ACTION_GET_CONTENT);
                        mIntent.putExtra("crop", "true");
                        mIntent.putExtra("aspectX", 1);
                        mIntent.putExtra("aspectY", 1);
                        mIntent.putExtra("outputX", 500);
                        mIntent.putExtra("outputY", 500);
                        try {
                            mIntent.putExtra("return-data", true);
                            startActivityForResult(Intent.createChooser(mIntent,
                                    "Complete action using"), mBtnNum);
                        } catch (ActivityNotFoundException e) {
                            // Do nothing for now
                        }
                    } else {
                        setModifyBtnEvent(mBtnNum);
                        setRemoveBtnEvent(mBtnNum);
                    }
                }
            });
        }
    }

    private void setRemoveBtnEvent(final int mBtnNum) {
        TextView mModifyView = modifyBtnArray.get(mBtnNum);
//        mModifyView.setBackground(new RoundedAvatarDrawable(mModifyView)); 에러발생 백그라운드이미지가 아니라 컬러값 입력해서오류나는듯
        mModifyView.setVisibility(View.VISIBLE);
        mModifyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setType("image/*");
                mIntent.setAction(Intent.ACTION_GET_CONTENT);
                mIntent.putExtra("crop", "true");
                mIntent.putExtra("aspectX", 1);
                mIntent.putExtra("aspectY", 1);
                mIntent.putExtra("outputX", 500);
                mIntent.putExtra("outputY", 500);
                try {
                    mIntent.putExtra("return-data", true);
                    startActivityForResult(Intent.createChooser(mIntent,
                            "Complete action using"), mBtnNum);
                } catch (ActivityNotFoundException e) {
                    // Do nothing for now
                }
            }
        });
    }

    private void setModifyBtnEvent(final int mBtnNum) {
        TextView mRemoveView = removeBtnArray.get(mBtnNum);
//        mRemoveView.setBackground(new RoundedAvatarDrawable(mRemoveView)); 에러발생 백그라운드이미지가 아니라 컬러값 입력해서오류나는듯
        mRemoveView.setVisibility(View.VISIBLE);
        mRemoveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryBtnArray.get(mBtnNum).setImageDrawable(plusImg);
                mBitmapArray[mBtnNum] = null;
                modifyBtnArray.get(mBtnNum).setVisibility(View.GONE);
                removeBtnArray.get(mBtnNum).setVisibility(View.GONE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle extras = data.getExtras();
            Bitmap image = extras.getParcelable("data");
            Drawable roundedImage = new RoundedAvatarDrawable(image);

            galleryBtnArray.get(requestCode).setImageDrawable(roundedImage);
            mBitmapArray[requestCode] = image;
            modifyBtnArray.get(requestCode).setVisibility(View.GONE);
            removeBtnArray.get(requestCode).setVisibility(View.GONE);
        }
    }
}


