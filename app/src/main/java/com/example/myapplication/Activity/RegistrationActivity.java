package com.example.myapplication.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DHelper.Databasehelper;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    public EditText Uname, Email, Phonenum;
    public Button cancel, save, showdata;
    public RadioGroup rbtngender;
    public RadioButton rbtnmale, rbtnfemale, radioSexButton;
    public String Name, email, phoneno, Gender, selection;
    public TextView Title;
    int GenderId;
    public Toolbar toolbar;
    private boolean isupdate = false;
    public Databasehelper dbhelper;
    int Id;
    String chkcrc, chkhocki, chkcarrom, chkreading, chkcook, chkmusic;
    public CheckBox chkCricket, chkHockey, chkCarrom, chkReading, chkCooking, chkMusic;
    ArrayList<String> checkedHobies = new ArrayList<String>();
    String hobies = "";
    String Hobbie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Uname = (EditText) findViewById(R.id.editusername);
        Email = (EditText) findViewById(R.id.editemail);
        Phonenum = (EditText) findViewById(R.id.editphone);

        rbtngender = (RadioGroup) findViewById(R.id.radiogender);

        rbtnmale = (RadioButton) findViewById(R.id.radiomale);
        rbtnfemale = (RadioButton) findViewById(R.id.radiofemale);

        chkCricket = (CheckBox) findViewById(R.id.chkcricket);
        chkHockey = (CheckBox) findViewById(R.id.chkhockei);
        chkCarrom = (CheckBox) findViewById(R.id.chkcarrom);
        chkReading = (CheckBox) findViewById(R.id.chkreading);
        chkCooking = (CheckBox) findViewById(R.id.chkcook);
        chkMusic = (CheckBox) findViewById(R.id.chkmusic);

        save = (Button) findViewById(R.id.btninsert);
        cancel = (Button) findViewById(R.id.btncancel);

        rbtngender.check(R.id.radiomale);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //toolbar.setTitle("User Registration");
        //toolbar.setTitleTextColor(Color.parseColor("#F5F5F5"));*/
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.tool_bar);
        Title = (TextView) toolbarTop.findViewById(R.id.tooltitle);
        Title.setText("User Registration");
        Title.setTextColor(Color.parseColor("#F5F5F5"));
        setSupportActionBar(toolbar);

        dbhelper = new Databasehelper(this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uname.getText().clear();
                Email.getText().clear();
                Phonenum.getText().clear();
                rbtngender.check(R.id.radiomale);
                chkCricket.setChecked(false);
                chkHockey.setChecked(false);
                chkMusic.setChecked(false);
                chkCooking.setChecked(false);
                chkReading.setChecked(false);
                chkCarrom.setChecked(false);
                //     if (chkCricket.isChecked()) { chkCricket.toggle(); }
                //    if (chkHockey.isChecked()) {chkHockey.toggle();    }
                //    if (chkMusic.isChecked()) {  chkMusic.toggle(); }
                //    if (chkCooking.isChecked()) {chkCooking.toggle(); }
                //    if (chkReading.isChecked()) {chkReading.toggle();}
                //   if (chkCarrom.isChecked()) {chkCarrom.toggle();}
                //rbtngender.clearCheck();
                // finish();
            }
        });
        Bundle intent = getIntent().getExtras();
        isupdate = intent.getBoolean("update");
        if (isupdate) {
            Id = intent.getInt("Id");
            String name = intent.getString("Uname");
            String email = intent.getString("UEmail");
            String phone = getIntent().getStringExtra("Umobile");
            int gender = getIntent().getIntExtra("Ugender", -1);
            Hobbie = getIntent().getStringExtra("UHobbies");

            Uname.setText(name);
            Email.setText(email);
            Phonenum.setText(phone);
            if (gender == 0) {
                rbtngender.check(R.id.radiomale);
            } else {
                rbtngender.check(R.id.radiofemale);
            }



            String[] h = Hobbie.split(",");

            for (String Hobi : h) {

                if (Hobi.equals("Cricket")) {
                    chkCricket.setChecked(true);
                } else if (Hobi.equals("Hockey")) {
                    chkHockey.setChecked(true);
                } else if (Hobi.equals("Carrom")) {
                    chkCarrom.setChecked(true);
                } else if (Hobi.equals("Reading")) {
                    chkReading.setChecked(true);
                } else if (Hobi.equals("Coocking")) {
                    chkCooking.setChecked(true);
                } else if (Hobi.equals("Music")) {
                    chkMusic.setChecked(true);
                }
            }

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = Uname.getText().toString().trim();
                email = Email.getText().toString().trim();
                phoneno = Phonenum.getText().toString();
                int radioButtonID = rbtngender.getCheckedRadioButtonId();
                View radioButton = rbtngender.findViewById(radioButtonID);
                GenderId = rbtngender.indexOfChild(radioButton);

                if (chkCricket.isChecked()) {
                    chkcrc = chkCricket.getText().toString();
                    if (chkcrc != null) {
                        hobies = hobies + chkcrc + ",";
                    } else {
                        hobies = "";
                    }
                }
                if (chkHockey.isChecked()) {
                    chkhocki = chkHockey.getText().toString();
                    if (chkhocki != null) {
                        hobies = hobies + chkhocki + ",";
                    } else {
                        hobies = "";
                    }
                }
                if (chkCarrom.isChecked()) {
                    chkcarrom = chkCarrom.getText().toString();
                    if (chkcarrom != null) {
                        hobies = hobies + chkcarrom + ",";
                    } else {
                        hobies = "";
                    }
                }
                if (chkReading.isChecked()) {
                    chkreading = chkReading.getText().toString();
                    if (chkreading != null) {
                        hobies = hobies + chkreading + ",";
                    } else {
                        hobies = "";
                    }
                }
                if (chkMusic.isChecked()) {
                    chkmusic = chkMusic.getText().toString();
                    if (chkmusic != null) {
                        hobies = hobies + chkmusic + ",";
                    } else {
                        hobies = "";
                    }
                }
                if (chkCooking.isChecked()) {
                    chkcook = chkCooking.getText().toString();
                    if (chkcook != null) {
                        hobies = hobies + chkcook + ",";
                    } else {
                        hobies = "";
                    }
                }
                if (!hobies.equals("")) {
                    hobies = hobies.substring(0, hobies.length() - 1);
                }

                if (isupdate) {
                    User User = new User(Id, Name, email, phoneno, GenderId, hobies);
                    dbhelper.Updateuser(User);
                    if (User != null) {
                        Toast.makeText(getApplicationContext(), "Data has been Updated !..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, Main2Activity.class);
                        startActivity(intent);
                        Intent i = new Intent(RegistrationActivity.this, Main2Activity.class);
                        // set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                } else {
                    User user = new User(Name, email, phoneno, GenderId, hobies);
                    dbhelper.adduser(user);
                    if (user != null) {
                        Toast.makeText(getApplicationContext(), "Data has been inserted !..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                }
                Log.d("phone", String.valueOf(phoneno));
                Log.d("gender", String.valueOf(GenderId));
                Log.d("Hobies", hobies);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        /*menu.findItem(R.id.Register).setTitle(Html.fromHtml("<font color='#F5F5F5'>Registration</font>"));
        menu.findItem(R.id.showdata).setTitle(Html.fromHtml("<font color='#F5F5F5'>Show Info</font>"));*/
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.parseColor("#F5F5F5")), 0, spanString.length(), 0); //fix the color to white
            int end = spanString.length();
            spanString.setSpan(new RelativeSizeSpan(1.1f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.showdata) {
            Intent intent = new Intent(RegistrationActivity.this, Main2Activity.class);
           /* intent.putExtra("update", false);*/
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
