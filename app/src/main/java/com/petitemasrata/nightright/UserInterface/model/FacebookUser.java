package com.petitemasrata.nightright.UserInterface.model;


import android.net.Uri;

public class FacebookUser {

    String fbId;
    String fbFirstName;
    String fbLastName;
    Uri fbUserImage;

    public FacebookUser(String fbId, String fbFirstName, String fbLastName, Uri fbUserImage) {
        this.fbId = fbId;
        this.fbFirstName = fbFirstName;
        this.fbLastName = fbLastName;
        this.fbUserImage = fbUserImage;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbFirstName() {
        return fbFirstName;
    }

    public void setFbFirstName(String fbFirstName) {
        this.fbFirstName = fbFirstName;
    }

    public String getFbLastName() {
        return fbLastName;
    }

    public void setFbLastName(String fbLastName) {
        this.fbLastName = fbLastName;
    }

    public Uri getFbUserImage() {
        return fbUserImage;
    }

    public void setFbUserImage(Uri fbUserImage) {
        this.fbUserImage = fbUserImage;
    }
}
