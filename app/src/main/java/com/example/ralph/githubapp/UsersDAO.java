package com.example.ralph.githubapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralph on 25/03/18.
 */

@Dao
public interface UsersDAO {

    @Insert
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(ArrayList<User> users);

    @Insert
    void addTwoUsers(User user, User user1);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);


    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT username FROM users")
    List<String> getAllUsernames();



}
