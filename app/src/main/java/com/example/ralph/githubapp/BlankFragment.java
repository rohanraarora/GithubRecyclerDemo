




package com.example.ralph.githubapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    RecyclerView recyclerView;
    ProgressBar progressBar;
    UsersListAdapter adapter;
    UsersRecyclerAdapter recyclerAdapter;
    ArrayList<User> followers = new ArrayList<>();

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_blank, container, false);

        recyclerView = view.findViewById(R.id.listview);
        progressBar = view.findViewById(R.id.progressBar);

        //String username = getIntent().getStringExtra("username");
        String username = "rohanraarora";
        if(username!= null){
            fetchFollowers(username);
            recyclerAdapter = new UsersRecyclerAdapter(getContext(), followers, new UsersRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    followers.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                }
            });
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    int from = viewHolder.getAdapterPosition();
                    int to = target.getAdapterPosition();
                    Collections.swap(followers,from,to);
                    recyclerAdapter.notifyItemMoved(from,to);
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                    int position = viewHolder.getAdapterPosition();
                    followers.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);

                }
            });
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }

        return view;
    }

    private void fetchFollowers(String username) {

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);


        Call<ArrayList<User>> call = ApiClient.getInstance().getGithubApi().getFollowers(username);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> users = response.body();

                if(users != null){
                    followers.clear();
                    followers.addAll(users);
                    recyclerAdapter.notifyDataSetChanged();
                }
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });


    }

}
