package com.example.ralph.githubapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ralph on 17/03/18.
 */

public class ApiClient {

    private static ApiClient INSTANCE;

    private GithubApi githubApi;

    private ApiClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubApi = retrofit.create(GithubApi.class);
    }

    public static ApiClient getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public GithubApi getGithubApi() {
        return githubApi;
    }
}
