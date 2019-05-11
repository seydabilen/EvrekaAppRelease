package com.example.evrekaapp.accounts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evrekaapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    Button exitButton;
    android.app.AlertDialog.Builder builder;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        exitButton=findViewById(R.id.exitBtn);
        builder = new android.app.AlertDialog.Builder(this);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert("Are you sure you want to exit the application?");
            }
        });
    }

    public void Alert(String title)
    {
        final Button btnConfirm,btnExit;
        TextView lblContent;
        ImageView image;
        LayoutInflater infilater =this.getLayoutInflater();
        View alertv = infilater.inflate(R.layout.dialog_layout,null);
        btnConfirm = alertv.findViewById(R.id.confirm);
        btnExit =  alertv.findViewById(R.id.exit);
        lblContent = alertv.findViewById(R.id.content);
        image = alertv.findViewById(R.id.alertImage);
        lblContent.setText(title);
        builder.setView(alertv);
        final android.app.AlertDialog alertDialog = builder.show();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent=new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });

    }
}
