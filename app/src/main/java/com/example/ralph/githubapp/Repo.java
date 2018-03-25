package com.example.ralph.githubapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ralph on 24/03/18.
 */


@Entity(tableName = "repos",foreignKeys = @ForeignKey(entity = User.class,parentColumns = "username",childColumns = "owner_username"))
public class Repo {

    @PrimaryKey
    long id;
    String name;
    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    String fullName;

    @Ignore
    User owner;
    @SerializedName("stars")
    int stars;

    @ColumnInfo(name = "owner_username")
    String ownerUsername;


}
