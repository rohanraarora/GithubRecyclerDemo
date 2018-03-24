package com.example.ralph.githubapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ralph on 24/03/18.
 */

public class Repo {

    long id;
    String name;
    @SerializedName("full_name")
    String fullName;
    User owner;
    @SerializedName("stars")
    int stars;


}
