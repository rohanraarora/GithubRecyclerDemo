package com.example.ralph.githubapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ralph on 17/03/18.
 */

@Entity(tableName = "users")
public class User {

    private String name;

    @SerializedName("avatar_url")
    private String avatarURL;

    private int followers;

    @PrimaryKey
    @SerializedName("login")
    @NonNull
    private String username;

    public User(String name, String avatarURL, int followers, String username) {
        this.name = name;
        this.avatarURL = avatarURL;
        this.followers = followers;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
