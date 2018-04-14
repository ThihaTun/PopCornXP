package com.example.gl62m7rdx.sqlite_multi_database_test.data.vo;

import io.realm.RealmObject;

/**
 * Created by GL62M 7RDX on 06-Feb-18.
 */

public class UserVO extends RealmObject {

    public static final String JK_FB_ID = "facebookId";
    public static final String JK_NAME = "name";
    public static final String JK_GENDER = "gender";
    public static final String JK_EMAIL = "email";
    public static final String JK_DOB = "dateOfBirth";

    private String facebookId;
    private String name;
    private String gender;
    private String email;
    private String dateOfBirth;

    private String profileUrl;
    private String coverUrl;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
