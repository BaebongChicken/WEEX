package com.kjstudio.weex.dataClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by KJ_Studio on 2015-12-08.
 */
public class GroupData implements Serializable {

    public int id;
    public String category;
    public String title;
    public String activities;
    public String location = null;
    public String geoLocation = null;
    public String time;
    public int maxMemberCount;
    public String description;
    public String imagePath;
    public DateTime createAt;


    public static GroupData getGroupDataFromJson(JSONObject jsonObject) {
        GroupData groupData = new GroupData();

        try {
            groupData.id = jsonObject.getInt("id");
            groupData.category = jsonObject.getString("category");
            groupData.title = jsonObject.getString("title");
            groupData.activities = jsonObject.getString("activities");
            groupData.location = jsonObject.getString("location");
            groupData.geoLocation = jsonObject.getString("geoLocation");
            groupData.time = jsonObject.getString("time");
            groupData.maxMemberCount = jsonObject.getInt("maxMemberCount");
            groupData.description = jsonObject.getString("description");
            groupData.imagePath = jsonObject.getString("imagePath");
            groupData.createAt = DateTime.parse(jsonObject.getString("createAt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return groupData;
    }
}
