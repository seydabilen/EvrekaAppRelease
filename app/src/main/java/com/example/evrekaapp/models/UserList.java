package com.example.evrekaapp.models;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.evrekaapp.R;

import java.util.List;

public class UserList extends ArrayAdapter<UserInformation> {
    private Activity context;
    private List<UserInformation> userList;
    public UserList(Activity context,List<UserInformation> userList){
        super(context, R.layout.list_item,userList);
        this.context=context;
        this.userList=userList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater infilater=context.getLayoutInflater();
        View listViewItem=infilater.inflate(R.layout.list_item,null,true);
        TextView container=listViewItem.findViewById(R.id.containerTxt);
        TextView sensor=listViewItem.findViewById(R.id.sensorTxt);
        TextView fullnessRate=listViewItem.findViewById(R.id.fullnessRateTxt);
        TextView temperature=listViewItem.findViewById(R.id.temperatureTxt);

        UserInformation userInformation=userList.get(position);
        container.setText(userInformation.getContainerID());
        sensor.setText(userInformation.getSensorID());
        fullnessRate.setText(userInformation.getFullnessRate());
        temperature.setText(userInformation.getTemperature());

        return listViewItem;
    }
}
