package com.mengo.android.backend.beans;


public class User {
    private int id;
    private String user_name;
    private String password;
    private String salt_word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt_word() {
        return salt_word;
    }

    public void setSalt_word(String salt_word) {
        this.salt_word = salt_word;
    }
}
