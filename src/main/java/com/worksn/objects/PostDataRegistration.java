package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDataRegistration {
    @SerializedName("login")
    @Expose
    String login = null;

    @SerializedName("password")
    @Expose
    String password = null;

    @SerializedName("new_pass")
    @Expose
    String newPassword = null;


    @SerializedName("name")
    @Expose
    String name = null;

    @SerializedName("s_name")
    @Expose
    String sName = null;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("about_user")
    @Expose
    String aboutUser = null;


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }
}
