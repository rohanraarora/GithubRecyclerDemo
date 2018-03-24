package com.example.ralph.githubapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {

    TextView usernameTextView;
    ImageView avatarImageView;


    public UserDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_detail, container, false);

        avatarImageView = view.findViewById(R.id.avatar);
        usernameTextView = view.findViewById(R.id.username);

        Bundle bundle = getArguments();
        if(bundle != null){
            String username = bundle.getString("username");
            String avatar = bundle.getString("avatar");

            usernameTextView.setText(username);
            Picasso.get().load(avatar).into(avatarImageView);
        }

        return view;
    }

}
