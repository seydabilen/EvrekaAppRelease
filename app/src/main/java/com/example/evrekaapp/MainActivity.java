package com.example.evrekaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evrekaapp.models.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    private Button btnSave;
    private Button btnProceed,btnUpdate;
    private EditText editTextContainerID,editTextSensorID, editTextLatitude,editTextLongitude,editTextTemperature,editTextFullnessRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        init();
        btnSave.setOnClickListener(this);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,OperationActivity.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ContainerListActivity.class);
                startActivity(intent);
            }
        });


    }
    private void saveUserInformation(){
        String containerId =editTextContainerID.getText().toString().trim();
        String sensorId =editTextSensorID.getText().toString().trim();
        String temperature =editTextTemperature.getText().toString().trim();
        String fullnessRate =editTextFullnessRate.getText().toString().trim();
        double latitude= Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude= Double.parseDouble(editTextLongitude.getText().toString().trim());
        UserInformation userInformation=new UserInformation(containerId,sensorId,temperature,fullnessRate,latitude,longitude);

        final String containerID = FirebaseDatabase.getInstance().getReference().push().getKey();
        final String userID= FirebaseAuth.getInstance().getUid();

        mDatabase.child("User ID " + userID)
                .child("Container ID " + containerID)
                .setValue(userInformation);

    }


    private void init(){
        btnProceed=findViewById(R.id.buttonProceed);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        editTextContainerID=findViewById(R.id.editTextContainerID);
        editTextSensorID=findViewById(R.id.editTextSensorID);
        editTextLatitude=findViewById(R.id.editTextLatitude);
        editTextLongitude=findViewById(R.id.editTextLongitude);
        editTextTemperature=findViewById(R.id.editTextTemperature);
        editTextFullnessRate=findViewById(R.id.editTextFullnessRate);
        btnSave=findViewById(R.id.buttonSave);
        btnUpdate=findViewById(R.id.buttonUpdate);
    }

    @Override
    public void onClick(View view) {
        if(view==btnProceed){
            finish();
        }
        if (view==btnSave){
            saveUserInformation();
            editTextContainerID.setText("");
            editTextLatitude.setText("");
            editTextLongitude.setText("");
            editTextSensorID.setText("");
            editTextTemperature.setText("");
            editTextFullnessRate.setText("");
        }
    }
    }

