package com.ipz_project.Webdes.models;

public class AdminData {

    private String admin_password, admin_username;

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public AdminData() {
    }

    public AdminData(String admin_password, String admin_username) {
        this.admin_password = admin_password;
        this.admin_username = admin_username;
    }

}
