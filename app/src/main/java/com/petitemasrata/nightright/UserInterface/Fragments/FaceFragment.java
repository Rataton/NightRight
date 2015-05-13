package com.petitemasrata.nightright.UserInterface.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.petitemasrata.nightright.R;
import com.petitemasrata.nightright.UserInterface.model.FacebookUser;

import org.json.JSONObject;

import java.util.Arrays;

public class FaceFragment extends Fragment {

    Context CONTEXT;
    CallbackManager callbackManager;
    LoginButton loginButton;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    FacebookCallback<LoginResult> mFacebookCallback;

    FacebookUser fbUser;


    public FaceFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CONTEXT = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(CONTEXT.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                Log.i("Token: ", String.valueOf(currentAccessToken));
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                if (currentProfile != null) {
                    Profile infoProfile = Profile.getCurrentProfile();
                    if (infoProfile != null) {
                        String firstName = infoProfile.getFirstName();
                        String lastName = infoProfile.getLastName();
                        String fbId = infoProfile.getId();
                        Uri fbImageProfile = infoProfile.getProfilePictureUri(64, 64);

                        fbUser = new FacebookUser(fbId, firstName, lastName, fbImageProfile);

                        Toast.makeText(CONTEXT, "Bienvenido " + fbUser.getFbFirstName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CONTEXT, "ERROR: No existe un perfil", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    @Override
//    http://stackoverflow.com/questions/29912083/get-profile-data-from-facebook-sdk-on-android-always-return-null-why
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_face, container, false);

        loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile, user_friends"));

        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Intent i = new Intent("com.petitemasrata.nightright.MAINACTIVITY");
                startActivity(i);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

}
