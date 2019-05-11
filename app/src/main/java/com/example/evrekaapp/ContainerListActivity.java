package com.example.evrekaapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evrekaapp.models.UserInformation;
import com.example.evrekaapp.models.UserList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContainerListActivity extends AppCompatActivity {
    ListView listView;
    DatabaseReference database;
    List<UserInformation> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_list);
        listView=findViewById(R.id.listView);
        userList=new ArrayList<>();
        database=FirebaseDatabase.getInstance().getReference("Users");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserInformation user=userList.get(position);
                showUpdateDialog(user.getLatitude(),user.getLongitude());

            }
        });
    }

    private void showUpdateDialog(Double lat,Double lon){
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.update_dialog,null);
        dialogBuilder.setView(dialogView);

        final TextView textView=dialogView.findViewById(R.id.dialogTxt);
        final EditText editTextContainer=dialogView.findViewById(R.id.editText2);
        final EditText editTextSensor=dialogView.findViewById(R.id.editText3);
        final EditText editTextLat=dialogView.findViewById(R.id.editText4);
        final EditText editTextLon=dialogView.findViewById(R.id.editText5);
        final EditText editTextTemp=dialogView.findViewById(R.id.editText6);
        final EditText editTextFullness=dialogView.findViewById(R.id.editText7);
        final Button updateBtn= dialogView.findViewById(R.id.buttonUpdateDialog);
        final AlertDialog alertDialog=dialogBuilder.create();
        alertDialog.show();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String container = editTextContainer.getText().toString().trim();
                final String sensor = editTextSensor.getText().toString().trim();
                final String temp = editTextTemp.getText().toString().trim();
                final String fullnes = editTextFullness.getText().toString().trim();
                final Double lat= Double.valueOf(editTextLat.getText().toString().trim());
                final Double lon= Double.valueOf(editTextLon.getText().toString().trim());
                updateList(container,sensor,lat,lon,temp,fullnes);
                alertDialog.dismiss();

            }
        });

    }
    private boolean updateList(String containerID,String sensorID,double latitude,double longitude,String temperature,String fullnessRate){
         DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Users").child(containerID);
         String containerIDM=databaseReference.push().getKey();
         UserInformation userInformation=new UserInformation(containerID,sensorID,temperature,fullnessRate,latitude,longitude);
         databaseReference.child(containerIDM).setValue(userInformation);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    for(DataSnapshot s : dataSnapshot1.getChildren()){
                        UserInformation info=s.getValue(UserInformation.class);
                        userList.add(info);

                    }
                    UserList adapter=new UserList(ContainerListActivity.this,userList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
