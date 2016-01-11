package com.kjstudio.weex.utils;


import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.kjstudio.weex.dataClass.GroupData;
import com.kjstudio.weex.dataClass.UserData;

public class ServerUtil {
	private static final String TAG = ServerUtil.class.getSimpleName();

	public final static String BASE_URL = "http://yohun92.cafe24.com/ci/gb_dongari/";



    public interface JsonResponseHandler {
		void onResponse(JSONObject json);
	}

	public static void getMissions(final Context context, final JsonResponseHandler handler){
        String url = BASE_URL + "getMissions";
        Map<String, Object> data = new HashMap<String, Object>();

        AsyncHttpRequest.post(context, url,  data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                Log.v("CHO", response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });

	}
	public static void facebookLogin(final Context context, final String name, final String uid, final String email, final JsonResponseHandler handler) {
		String url = BASE_URL + "facebookLogin";
		//		String registrationId = ContextUtil.getRegistrationId(context);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", name);
		data.put("uid", uid);
		data.put("email", email);
		final String profilePicturePath = "https://graph.facebook.com/" + uid + "/picture?type=large";
		data.put("profilePhoto", profilePicturePath);
		data.put("os", "Android");
		data.put("deviceToken", "tempDeviceToken");
//		data.put("userPhoneNum", objectData.userPhoneNum);

		AsyncHttpRequest.post(context, url,  data, false, new AsyncHttpRequest.HttpResponseHandler() {

			@Override
			public boolean onPrepare() {
				return true;
			}

			@Override
			public void onResponse(String response) {
				Log.v("CHO", response);
				try {
					JSONObject json = new JSONObject(response);

					if (handler != null)
						handler.onResponse(json);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFinish() {

			}

			@Override
			public void onCancelled() {

			}

		});
	}


	public static void registerUser(final Context context, final UserData userData, final JsonResponseHandler handler) {
		String url = BASE_URL + "registerUser";
//		UserData userData = ContextUtil.getUserData(context);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", userData.id+"");
		data.put("name", userData.name);
		data.put("uid", userData.uid);
		data.put("email", userData.email);
		data.put("location", userData.location);
		data.put("geoLocation", userData.geoLocation);
		data.put("isTrainer", userData.isTrainer? 1+"" : 0+"");
		data.put("activityAndLevel", userData.activityAndLevel);
		data.put("gender", userData.gender);
		data.put("age", userData.age);
		data.put("speciality", userData.speciality);
		data.put("concern", userData.concern);
		data.put("spokenLanguage", userData.spokenLanguage);
		data.put("belong", userData.belong);
		data.put("profileLine", userData.profileLine);
		data.put("aboutMe", userData.aboutMe);
		data.put("profilePhoto", userData.profilePhoto);
		data.put("imagePaths", userData.imagePaths);
		data.put("os", "Android");
		data.put("deviceToken", "tempDeviceToken");

		AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

			@Override
			public boolean onPrepare() {
				return true;
			}

			@Override
			public void onResponse(String response) {
				Log.v("CHO", response);
				try {
					JSONObject json = new JSONObject(response);

					if (handler != null)
						handler.onResponse(json);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFinish() {

			}

			@Override
			public void onCancelled() {

			}

		});
	}


	// 그룹 관리


	public static void registerGroup(final Context context, final GroupData groupData, final JsonResponseHandler handler) {
		String url = BASE_URL + "registerGroup";
//		UserData userData = ContextUtil.getUserData(context);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", groupData.id+"");
		data.put("category", groupData.category);
		data.put("title", groupData.title);
		data.put("activities", groupData.activities);
		data.put("location", groupData.location);
		data.put("geoLocation", groupData.geoLocation);
		data.put("time", groupData.time);
		data.put("maxMemberCount", groupData.maxMemberCount);
		data.put("description", groupData.description);

		AsyncHttpRequest.post(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

			@Override
			public boolean onPrepare() {
				return true;
			}

			@Override
			public void onResponse(String response) {
				System.out.println(response);
//				Log.v("CHO", response);
				try {
					JSONObject json = new JSONObject(response);

					if (handler != null)
						handler.onResponse(json);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFinish() {

			}

			@Override
			public void onCancelled() {

			}

		});
	}
}
