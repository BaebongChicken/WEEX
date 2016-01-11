package com.kjstudio.weex.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.GroupData;
import com.kjstudio.weex.dataClass.InterestedActsData;
import com.kjstudio.weex.utils.ContextUtil;
import com.kjstudio.weex.utils.ServerUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditGroupActivity extends BaseActivity {

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;

    private android.widget.TextView categoryTxt;
    private android.widget.EditText titleEdt;
    private android.widget.LinearLayout subjectLayout;
    private android.widget.ImageView addSubjectBtn;
    private android.widget.EditText locationEdt;
    private android.widget.ToggleButton notConfirmedBtn;
    private android.widget.EditText descriptionEdt;
    private android.widget.Button nextBtn;

    GroupData mGroupData;
    InterestedActsData interestedActsData;
    ArrayList<String> subjectList = new ArrayList<>();

    static final int REQ_FOR_SUBJECT = 100;
    private EditText countEdt;
    private TextView actNameTxt;
    private TextView actLevelTxt;
    private TextView largeCategoryTxt;
    private TextView locationTxt;
    private TextView startDateTxt;
    private TextView startTimeTxt;

    private Calendar mStartDate = Calendar.getInstance();
    private final static SimpleDateFormat sDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat sDateTimeSecFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd (E)");
    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("a h시 mm분");
    private ImageView largeCategoryImg;

    ArrayList<String> categories = ContextUtil.getGroupCategoryNameArray();
    int[] categoryStrings = ContextUtil.getGroupCategoryStringArray();
    private LinearLayout dateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        setCustomActionbar();
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setValues() {
        super.setValues();
        mGroupData = (GroupData) getIntent().getSerializableExtra("groupData");
        int categoryIndex = categories.indexOf(mGroupData.category);

        if (categoryIndex < 3) {
            largeCategoryTxt.setText(R.string.comehere);
        } else if (categoryIndex < 6) {
            largeCategoryTxt.setText(R.string.club);
        } else if (categoryIndex < 9) {
            largeCategoryTxt.setText(R.string.place);
        }

        categoryTxt.setText(categoryStrings[categoryIndex]);
        setDateText();
        mTitleTextView.setText(R.string.페이지_만들기);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        notConfirmedBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    dateLayout.setVisibility(View.GONE);
                } else {
                    dateLayout.setVisibility(View.VISIBLE);

                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeGroupData();
                ServerUtil.registerGroup(EditGroupActivity.this, mGroupData, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Toast.makeText(EditGroupActivity.this, "서버에는 추가됐습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });

        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(EditGroupActivity.this, InterestedActsActivity.class);
                mIntent.putExtra("activityName", "EditGroupActivity");
                startActivityForResult(mIntent, REQ_FOR_SUBJECT);
            }
        });

        locationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(EditGroupActivity.this);
                    startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        startDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditGroupActivity.this, mDateSetListener, mStartDate.get(Calendar.YEAR), mStartDate.get(Calendar.MONTH), mStartDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(EditGroupActivity.this, mTimeSetListener, mStartDate.get(Calendar.HOUR_OF_DAY), mStartDate.get(Calendar.MINUTE), false).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_FOR_SUBJECT) {
            if (resultCode == RESULT_OK) {

                subjectLayout.setVisibility(View.VISIBLE);

                interestedActsData = (InterestedActsData) data.getSerializableExtra("interestedActsData");
                actNameTxt.setText(interestedActsData.actsName);
                actLevelTxt.setText(interestedActsData.actsLevel);

            }
        }
        else if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(EditGroupActivity.this, data);
                Log.i("CHO", "Place Selected: " + place.getName() + " / " + place.getLatLng().latitude + "," + place.getLatLng().longitude);

                locationTxt.setText(place.getName());
                mGroupData.location = place.getName().toString();
                mGroupData.geoLocation = place.getLatLng().latitude + "," + place.getLatLng().longitude;
                // Format the place's details and display them in the TextView.
//                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
//                        place.getId(), place.getAddress(), place.getPhoneNumber(),
//                        place.getWebsiteUri()));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(EditGroupActivity.this, data);
                Log.e("CHO", "Error: Status = " + status.toString());
            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    private void makeGroupData() {


        mGroupData.title = titleEdt.getText().toString();

        if (mGroupData.title.equals("")) {
            Toast.makeText(EditGroupActivity.this, R.string.모임명을_입력해주세요, Toast.LENGTH_SHORT).show();
            return;
        }

        if (interestedActsData == null) {
            Toast.makeText(EditGroupActivity.this, R.string.운동종목을_입력해주세요, Toast.LENGTH_SHORT).show();
            return;

        }
        mGroupData.activities = interestedActsData.actsName + "/" + interestedActsData.actsLevel;

        if (mGroupData.geoLocation == null) {

            Toast.makeText(EditGroupActivity.this, R.string.장소를_입력해주세요, Toast.LENGTH_SHORT).show();
            return;
        }

        if (notConfirmedBtn.isChecked()) {
            mGroupData.time = "nono";
        }
        else {
            mGroupData.time = sDateTimeSecFormat.format(mStartDate.getTime());
        }

        mGroupData.maxMemberCount = Integer.parseInt(countEdt.getText().toString());
        mGroupData.description = descriptionEdt.getText().toString();

//        mGroupData.
    }

    @Override
    public void bindViews() {
        super.bindViews();

        this.nextBtn = (Button) findViewById(R.id.nextBtn);
        this.descriptionEdt = (EditText) findViewById(R.id.descriptionEdt);
        this.countEdt = (EditText) findViewById(R.id.countEdt);
        this.dateLayout = (LinearLayout) findViewById(R.id.dateLayout);
        this.startTimeTxt = (TextView) findViewById(R.id.startTimeTxt);
        this.startDateTxt = (TextView) findViewById(R.id.startDateTxt);
        this.notConfirmedBtn = (ToggleButton) findViewById(R.id.notConfirmedBtn);
        this.locationTxt = (TextView) findViewById(R.id.locationTxt);
        this.locationEdt = (EditText) findViewById(R.id.locationEdt);
        this.addSubjectBtn = (ImageView) findViewById(R.id.addSubjectBtn);
        this.subjectLayout = (LinearLayout) findViewById(R.id.subjectLayout);
        this.actLevelTxt = (TextView) findViewById(R.id.actLevelTxt);
        this.actNameTxt = (TextView) findViewById(R.id.actNameTxt);
        this.titleEdt = (EditText) findViewById(R.id.titleEdt);
        this.categoryTxt = (TextView) findViewById(R.id.categoryTxt);
        this.largeCategoryImg = (ImageView) findViewById(R.id.largeCategoryImg);
        this.largeCategoryTxt = (TextView) findViewById(R.id.largeCategoryTxt);
    }


    public void setDateText() {
        startDateTxt.setText(mDateFormat.format(mStartDate.getTime()));
        startTimeTxt.setText(mTimeFormat.format(mStartDate.getTime()));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mStartDate.set(year, monthOfYear, dayOfMonth);
                    setDateText();
                }
            };


    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mStartDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    mStartDate.set(Calendar.MINUTE, minute);
                    setDateText();
                }
            };

}
