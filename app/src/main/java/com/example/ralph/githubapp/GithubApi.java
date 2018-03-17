package com.example.ralph.githubapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ralph on 17/03/18.
 */

public interface GithubApi {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

}
