package com.kjstudio.weex.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.UserData;
import com.kjstudio.weex.utils.ContextUtil;
import com.kjstudio.weex.utils.ServerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;

public class LoginActivity extends BaseActivity {


    Button facebookLoginBtn;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(LoginActivity.this);

        bindViews();
        setupEvents();

        getAppKeyHash();


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d("CHO", "fbLogin Success");

                Profile loginProfile = Profile.getCurrentProfile();

                Log.d("loginProfile", loginProfile.getName() + " / " + loginProfile.getId());

//                String pictureURI = "https://graph.facebook.com/" + loginProfile.getId() + "/picture?type=large";
//                Log.d("CHO", "loginProfile = " + loginProfile.getId() + "," + loginProfile.getName() + "," + pictureURI);

//                Log.d("login get token", loginResult.getAccessToken().getToken());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {

                                    Log.d("GraphResponse", object.toString());

                                    ServerUtil.facebookLogin(LoginActivity.this, object.getString("name"), object.getString("id"), object.getString("email"), new ServerUtil.JsonResponseHandler() {
                                        @Override
                                        public void onResponse(JSONObject json) {

                                            try {
                                                JSONObject userProfile = json.getJSONObject("userProfile");
                                                UserData userData = UserData.getUserDataFromJson(userProfile);

                                                ContextUtil.setLoggedIn(LoginActivity.this, true);
                                                ContextUtil.setUserData(LoginActivity.this, userData);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            Intent mIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                                            startActivity(mIntent);
                                            finish();
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
//

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }


    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

    public void setupEvents() {
        facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {"public_profile", "user_friends", "email"};
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList(permissions));
            }
        });
    }

    @Override
    public void bindViews() {
        facebookLoginBtn = (Button) findViewById(R.id.facebookLoginBtn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
