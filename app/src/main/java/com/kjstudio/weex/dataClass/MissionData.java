package com.kjstudio.weex.dataClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by KJ_Studio on 2015-12-07.
 */
public class MissionData implements Serializable {

    public String id;
    public String title;
    public String imagePath;
    public String createAt;


    public static  MissionData getMissionDataFromJson(JSONObject json) {
        MissionData missionData = null;

        try {
            missionData = new MissionData();
            missionData.id = json.getString("id");
            missionData.title = json.getString("title");
            missionData.imagePath = json.getString("imagePath");
            missionData.createAt = json.getString("createAt");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return missionData;
    }

}
