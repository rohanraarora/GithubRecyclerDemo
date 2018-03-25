package com.example.ralph.githubapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> repoNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        listView = findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,repoNames);
        listView.setAdapter(adapter);



        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if(username!= null){

            List<String> names = GithubDatabase.getInstance(this).getReposDao().getRepoNames(username);
            repoNames.clear();
            repoNames.addAll(names);
            adapter.notifyDataSetChanged();

            Call<ArrayList<Repo>> call = ApiClient.getInstance().getGithubApi().getRepos(username);

            call.enqueue(new Callback<ArrayList<Repo>>() {
                @Override
                public void onResponse(Call<ArrayList<Repo>> call, Response<ArrayList<Repo>> response) {
                    ArrayList<Repo> repos = response.body();
                    if(repos != null){
                        repoNames.clear();
                        for(Repo repo:repos){
                            repoNames.add(repo.name);
                            repo.ownerUsername = repo.owner.getUsername();
                        }
                        GithubDatabase.getInstance(ReposActivity.this).getReposDao().insertRepos(repos);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Repo>> call, Throwable t) {

                }
            });
        }
    }
}
