package com.example.myapplication.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    EditText uname, pass;
    Button login, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (EditText) findViewById(R.id.etUserName);
        pass = (EditText) findViewById(R.id.etPass);
        login = (Button) findViewById(R.id.btnlogin);
        cancel = (Button) findViewById(R.id.btncancel);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((uname.getText().toString().equals("Admin")) && pass.getText().toString().equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                    intent.putExtra("update",false);
                    startActivity(intent);
                    //finish();
                } else if ((!uname.getText().toString().equals("Admin")) || (!pass.getText().toString().equals("admin"))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Insert Correct User & Password", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 50, 50);
                    toast.getView().setPadding(10, 10, 10, 10);
                    toast.getView().setBackgroundColor(Color.parseColor("#08457E"));
                    toast.show();
                }

                if (uname.getText().toString().length() == 0) {
                    uname.setError("Field cannot be left blank.");
                }
                if (pass.getText().toString().length() == 0) {
                    pass.setError("Field cannot be left blank.");
                }

            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.quit);
                builder.setTitle("Exit From Application !...");
                builder.setMessage("Would you like to Exit ?..");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

    }
}
