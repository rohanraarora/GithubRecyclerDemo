package com.example.ralph.githubapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    UsersListAdapter adapter;
    ArrayList<User> followers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview);
        progressBar = findViewById(R.id.progressBar);

        String username = getIntent().getStringExtra("username");
        if(username!= null){
            fetchFollowers(username);
            adapter = new UsersListAdapter(this,followers);
            listView.setAdapter(adapter);
        }
    }

    private void fetchFollowers(String username) {

        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);


        Call<ArrayList<User>> call = ApiClient.getInstance().getGithubApi().getFollowers(username);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> users = response.body();
                if(users != null){
                    followers.clear();
                    followers.addAll(users);
                    adapter.notifyDataSetChanged();
                }
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(FollowersActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });


    }

}
