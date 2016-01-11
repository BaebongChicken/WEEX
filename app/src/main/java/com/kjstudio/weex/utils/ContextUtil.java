package com.kjstudio.weex.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.UserData;

import java.util.ArrayList;

/**
 * Created by KJ_Studio on 2015-11-16.
 */
public class ContextUtil {
    private static final String PREFERENCE_NAME = "RepotPreferences";
    private static final String LOGGED_IN = "isLogin";
    private static final String id = "id";
    private static final String USER_ID = "User_ID";
    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String USER_NAME = "USER_NAME";
    private static final String location = "location";
    private static final String geoLocation = "geoLocation";
    private static final String isTrainer = "isTrainer";
    private static final String activityAndLevel = "activityAndLevel";
    private static final String gender = "gender";
    private static final String age = "age";
    private static final String speciality = "speciality";
    private static final String concern = "concern";
    private static final String spokenLanguage = "spokenLanguage";
    private static final String belong = "belong";
    private static final String profileLine = "profileLine";
    private static final String aboutMe = "aboutMe";
    private static final String profilePhoto = "profilePhoto";
    private static final String imagePaths = "imagePaths";

    public static ArrayList<String> getGroupCategoryNameArray() {

        ArrayList<String> cardList = new ArrayList<String>();
        cardList.add("VS");
        cardList.add("번개");
        cardList.add("레슨");
        cardList.add("학교");
        cardList.add("회사");
        cardList.add("일반동호회");
        cardList.add("공공장소");
        cardList.add("개인장소");
        cardList.add("운동용품");
        return cardList;
    }
    public static int[] getGroupCategoryStringArray() {

        int[] categoryStrings = {
                R.string.vs_mode,
                R.string.lightning,
                R.string.lessonOrEvent,
                R.string.school,
                R.string.company,
                R.string.normal_club,
                R.string.공공장소,
                R.string.개인장소,
                R.string.운동용품
        };

        return categoryStrings;
    }

    public static void setUserData(Context context, UserData userData) {

        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        //DateTime now = new DateTime(new Date());
        prefs.edit().putInt(id, userData.id).commit();
        prefs.edit().putString(USER_EMAIL, userData.email).commit();
        prefs.edit().putString(USER_NAME, userData.name).commit();
        prefs.edit().putString(USER_ID, userData.uid).commit();
        prefs.edit().putString(location, userData.location).commit();
        prefs.edit().putString(geoLocation, userData.geoLocation).commit();
        prefs.edit().putBoolean(isTrainer, userData.isTrainer).commit();
        prefs.edit().putString(activityAndLevel, userData.activityAndLevel).commit();
        prefs.edit().putString(gender, userData.gender).commit();
        prefs.edit().putInt(age, userData.age).commit();
        prefs.edit().putString(speciality, userData.speciality).commit();
        prefs.edit().putString(concern, userData.concern).commit();
        prefs.edit().putString(spokenLanguage, userData.spokenLanguage).commit();
        prefs.edit().putString(belong, userData.belong).commit();
        prefs.edit().putString(profileLine, userData.profileLine).commit();
        prefs.edit().putString(aboutMe, userData.aboutMe).commit();
        prefs.edit().putString(profilePhoto, userData.profilePhoto).commit();
        prefs.edit().putString(imagePaths, userData.imagePaths).commit();

    }
    public static UserData getUserData(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        //DateTime now = new DateTime(new Date());

        UserData userData = new UserData();
        userData.id = prefs.getInt(id, -1);
        userData.email = prefs.getString(USER_EMAIL, "");
        userData.name = prefs.getString(USER_NAME, "");
        userData.uid = prefs.getString(USER_ID, "");
        userData.location = prefs.getString(location, "");
        userData.geoLocation = prefs.getString(geoLocation, "");
        userData.isTrainer = prefs.getBoolean(isTrainer, false);
        userData.activityAndLevel = prefs.getString(activityAndLevel, "");
        userData.gender = prefs.getString(gender, "");
        userData.age = prefs.getInt(age, -1);
        userData.speciality = prefs.getString(speciality, "");
        userData.concern = prefs.getString(concern, "");
        userData.spokenLanguage = prefs.getString(spokenLanguage, "");
        userData.belong = prefs.getString(belong, "");
        userData.profileLine = prefs.getString(profileLine, "");
        userData.aboutMe = prefs.getString(aboutMe, "");
        userData.profilePhoto = prefs.getString(profilePhoto, "");
        userData.imagePaths = prefs.getString(imagePaths, "");

        return userData;
    }


    public static boolean isUserLoggedin(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        return prefs.getBoolean(LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean value)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putBoolean(LOGGED_IN, value).commit();

    }




    public static void setUserDataWhenRegister(Context context, UserData userData) {

        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        //DateTime now = new DateTime(new Date());
        prefs.edit().putString(USER_EMAIL, userData.email).commit();
        prefs.edit().putString(USER_NAME, userData.name).commit();
        prefs.edit().putString(USER_ID, userData.uid).commit();
        prefs.edit().putString(profilePhoto, userData.profilePhoto).commit();

    }


}
