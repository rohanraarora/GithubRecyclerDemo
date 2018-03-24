package com.example.ralph.githubapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class FollowersActivity extends AppCompatActivity implements BlankFragment.UserSelectedCallback{


    FrameLayout container;
    boolean isLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        container = findViewById(R.id.container);

        if(container!= null){
            isLandscapeMode = true;
        }

    }


    @Override
    public void onUserSelected(User user) {
//        Toast.makeText(this,"User slected" + user.getUsername(),Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("username",user.getUsername());
        bundle.putString("avatar",user.getAvatarURL());

        if(isLandscapeMode){



            UserDetailFragment fragment = new UserDetailFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container,fragment).commit();

        }else {

            Intent intent = new Intent(this,UserDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);


        }
    }
}
