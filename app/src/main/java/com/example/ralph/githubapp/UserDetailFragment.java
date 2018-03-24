package com.example.ralph.githubapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {

    TextView usernameTextView;
    ImageView avatarImageView;
    Button reposButton;


    public UserDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View view =  inflater.inflate(R.layout.fragment_user_detail, container, false);

        avatarImageView = view.findViewById(R.id.avatar);
        usernameTextView = view.findViewById(R.id.username);
        reposButton = view.findViewById(R.id.reposButton);

        Bundle bundle = getArguments();
        if(bundle != null){
            final String username = bundle.getString("username");
            String avatar = bundle.getString("avatar");

            usernameTextView.setText(username);
            Picasso.get().load(avatar).into(avatarImageView);
            reposButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ReposActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            });
        }

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(getContext(),"Delete",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
