package com.kjstudio.weex.dataClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by KJ_Studio on 2015-11-28.
 */
public class UserData implements Serializable {

    // 사용자 정보에 넣을 항목이 결정되면 여기에 형식 맞춰서 저장하면 된다.


    public int id=-1;
    public String name;
    public String uid;
    public String email;
    public String location = null;
    public String geoLocation = null;
    public boolean isTrainer = false;
    public String activityAndLevel;
    public String gender;
    public int age;
    public String speciality;
    public String concern;
    public String spokenLanguage;
    public String belong;
    public String profileLine;
    public String aboutMe;
    public String profilePhoto;
    public String imagePaths;
    public String os;
    public String deviceToken;
    public DateTime createAt;


    // JSON에서 UserData 뽑아낼때 최대한 편한 코드르 구상해봤다.
    // 원하는곳에서 UserData myData = UserData.getUserDataFromJson(json);
    // 이런 방식으로 사용하면 바로 유저데이터를 뽑아낼 수 있게 코딩함.



    public static  UserData getUserDataFromJson(JSONObject json) {
        UserData userData = null;

        try {
            userData = new UserData();
            userData.id = json.getInt("id");
            userData.name = json.getString("name");
            userData.uid = json.getString("uid");
            userData.email = json.getString("email");
            userData.location = json.getString("location");
            userData.geoLocation = json.getString("geoLocation");
            userData.isTrainer = json.getInt("isTrainer") == 1? true:false;
            userData.activityAndLevel = json.getString("activityAndLevel");
            userData.gender = json.getString("gender");
            userData.age = json.getInt("age");
            userData.speciality = json.getString("speciality");
            userData.concern = json.getString("concern");
            userData.spokenLanguage = json.getString("spokenLanguage");
            userData.belong = json.getString("belong");
            userData.profileLine = json.getString("profileLine");
            userData.aboutMe = json.getString("aboutMe");
            userData.profilePhoto = json.getString("profilePhoto");
            userData.imagePaths = json.getString("imagePaths");
            userData.os = json.getString("os");
            userData.deviceToken = json.getString("deviceToken");
            userData.createAt = DateTime.parse(json.getString("createAt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userData;
    }

    public static  UserData getUserDataFromJsonWhenRegister(JSONObject json) {
        UserData userData = null;

        try {
            userData = new UserData();
            userData.id = json.getInt("id");
            userData.uid = json.getString("uid");
            userData.email = json.getString("email");
            userData.name = json.getString("name");
            userData.profilePhoto = json.getString("profilePhoto");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userData;
    }



}
