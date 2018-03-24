package com.example.ralph.githubapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ralph on 17/03/18.
 */

public interface GithubApi {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users/{username}/followers")
    Call<ArrayList<User>> getFollowers(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<ArrayList<Repo>> getRepos(@Path("username") String username);


}
