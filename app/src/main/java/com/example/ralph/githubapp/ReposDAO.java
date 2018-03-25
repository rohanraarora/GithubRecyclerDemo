package com.example.ralph.githubapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralph on 25/03/18.
 */

@Dao
public interface ReposDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepos(ArrayList<Repo> repos);

    @Query("SELECT name from repos WHERE owner_username = :username")
    List<String> getRepoNames(String username);

}
