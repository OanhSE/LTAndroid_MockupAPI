package com.example.api_crud_mock;

import android.app.Activity;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Activity context;
    ArrayList<User> list = new ArrayList<>() ;
    private static LayoutInflater  layoutInflater;


    public UserAdapter(Activity context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemview = view;
        itemview = (itemview == null) ? layoutInflater.inflate(R.layout.mylist,null) : itemview;
        TextView id = (TextView)itemview.findViewById(R.id.txtid);
        TextView name = (TextView)itemview.findViewById(R.id.txtName);
        TextView avatar = (TextView)itemview.findViewById(R.id.txtavatar);
        TextView createAt = (TextView)itemview.findViewById(R.id.txtcreateAt);
        User u = list.get(i);
        id.setText("Đối tượng");
        name.setText(u.getName());
        avatar.setText(u.getAvatar());
        createAt.setText(u.getCreatedAt());
        return itemview;
    }
}
