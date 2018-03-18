package com.example.ralph.githubapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ImageView avatar;
    TextView name;
    TextView usernameTextView;
    String username;
    Button followers;
    ConstraintLayout content;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        usernameTextView = findViewById(R.id.username);
        followers = findViewById(R.id.followers);
        content = findViewById(R.id.content);
        progressBar = findViewById(R.id.progressBar);

        username = getIntent().getStringExtra("username");
        if(username != null){
            fetchUser();
        }
    }

    private void fetchUser() {
        content.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Call<User> call = ApiClient.getInstance().getGithubApi().getUser(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                name.setText(user.getName());
                usernameTextView.setText("@"+user.getUsername());
                Picasso.get().load(user.getAvatarURL()).into(avatar);
                followers.setText(user.getFollowers() + " followers");

                content.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                content.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void showFollowers(View view){
        Intent intent = new Intent(this,FollowersActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

}
