package com.example.mechanicsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LoginInfoAdapter extends BaseAdapter {
    private Context context;
    private List<LoginInfo> loginInfoList;
    private LayoutInflater inflater;

    public LoginInfoAdapter(Context context, List<LoginInfo> loginInfoList) {
        this.context = context;
        this.loginInfoList = loginInfoList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return loginInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return loginInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.login_info_item, parent, false);
        }

        TextView txtOIB = convertView.findViewById(R.id.txtOIB);
        TextView txtUsername = convertView.findViewById(R.id.txtUsername);
        TextView txtPassword = convertView.findViewById(R.id.txtPassword);

        LoginInfo loginInfo = loginInfoList.get(position);

        txtOIB.setText(String.valueOf(loginInfo.oib));
        txtUsername.setText(loginInfo.username);
        txtPassword.setText(loginInfo.password);

        return convertView;
    }
}
