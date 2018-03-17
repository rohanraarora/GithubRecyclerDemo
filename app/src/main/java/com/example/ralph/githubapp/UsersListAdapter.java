package com.example.ralph.githubapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ralph on 18/03/18.
 */

public class UsersListAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<User> mUsers;

    public UsersListAdapter(Context context, ArrayList<User> users){
        mContext = context;
        mUsers = users;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public User getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View output = convertView;
        if(output == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            output = inflater.inflate(R.layout.row_user,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.avatar = output.findViewById(R.id.avatar);
            holder.username = output.findViewById(R.id.username);
            output.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) output.getTag();
        User user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        Picasso.get().load(user.getAvatarURL()).into(holder.avatar);
        return output;
    }

    class ViewHolder {

        ImageView avatar;
        TextView username;

    }
}
