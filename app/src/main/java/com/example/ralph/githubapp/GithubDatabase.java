package com.example.ralph.githubapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ralph on 25/03/18.
 */

@Database(entities = {User.class,Repo.class},version = 1)
public abstract class GithubDatabase extends RoomDatabase {

    private static GithubDatabase INSTANCE;

    public static GithubDatabase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),GithubDatabase.class,"github_databases")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    abstract UsersDAO getUserDao();

    abstract ReposDAO getReposDao();

}
