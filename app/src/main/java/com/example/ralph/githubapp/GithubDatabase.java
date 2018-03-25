package com.example.ralph.githubapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by ralph on 25/03/18.
 */

@Database(entities = {User.class},version = 1)
public abstract class GithubDatabase extends RoomDatabase {

    abstract UsersDAO getUserDao();

}
