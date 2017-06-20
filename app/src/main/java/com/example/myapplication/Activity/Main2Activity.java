package com.example.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Listadapter;
import com.example.myapplication.DHelper.Databasehelper;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    public ListView listuser;
    public Databasehelper dhelper;
    ArrayList<User> userinfo = new ArrayList<User>();
    private AlertDialog.Builder build, build1, build2;
    private Toolbar toolbar;
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        //toolbar.setTitle("User Infromation");
           //toolbar.setTitleTextColor(Color.parseColor("#F5F5F5"));*/
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.tool_bar);
        TextView Title = (TextView) toolbarTop.findViewById(R.id.tooltitle);
        Title.setText("User Information");
        Title.setTextColor(Color.parseColor("#F5F5F5"));

        setSupportActionBar(toolbar);
        listuser = (ListView) findViewById(R.id.lstviewuser);
        dhelper = new Databasehelper(this);
        Displaynfo();
        final CharSequence[] items = {"Update Data", "Delete Record from List"};
        listuser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final User user = userinfo.get(position);
                Id = userinfo.get(position).getId();
                build = new AlertDialog.Builder(Main2Activity.this);

                build.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            build2 = new AlertDialog.Builder(Main2Activity.this);
                            build2.setTitle("Update");
                            // build2.setIcon(R.drawable.add_to_shopping_cart48);
                            build2.setMessage("Are you sure to Update record?");
                            build2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Main2Activity.this, RegistrationActivity.class);
                                    intent.putExtra("Id", Id);
                                    intent.putExtra("Uname", user.getName());
                                    intent.putExtra("UEmail", user.getEmail());
                                    intent.putExtra("Umobile", user.getMobile());
                                    intent.putExtra("Ugender", user.getGender());
                                    intent.putExtra("UHobbies",user.getHoboies());
                                    intent.putExtra("update", true);
                                    startActivity(intent);

                                    finish();
                                    Displaynfo();
                                    dialog.cancel();
                                }
                            });
                            build2.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            build2.show();
                        } else {

                            build1 = new AlertDialog.Builder(Main2Activity.this);
                            build1.setTitle("REMOVE Record !..");

                            // build1.setIcon(R.drawable.remfev);
                            build1.setMessage("Are you sure to Remove information?");
                            build1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dhelper.Deleteuser(user);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Data Remove  ", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.BOTTOM, 30, 120);
                                    toast.getView().setPadding(10, 10, 10, 10);
                                    toast.getView().setBackgroundColor(Color.parseColor("#08457E"));
                                    toast.show();
                                    Displaynfo();
                                    dialog.cancel();
                                }
                            });
                            build1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            build1.show();
                        }
                    }
                });
                build.create().show();
                return true;
            }
        });

    }
    public void Displaynfo(){
       // listuser.setDivider(null);
       listuser.setDividerHeight(3);

        userinfo = dhelper.getalluserinfo();
        Listadapter adapter = new Listadapter(getApplicationContext(), userinfo);
        listuser.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        /*menu.findItem(R.id.Register).setTitle(Html.fromHtml("<font color='#F5F5F5'>Registration</font>"));
        menu.findItem(R.id.showdata).setTitle(Html.fromHtml("<font color='#F5F5F5'>Show Info</font>"));*/
        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.parseColor("#ECF0F1")), 0, spanString.length(), 0); //fix the color to white
            int end = spanString.length();
            spanString.setSpan(new RelativeSizeSpan(1.1f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);
        }
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Register) {
            Intent intent = new Intent(Main2Activity.this, RegistrationActivity.class);
            intent.putExtra("update", false);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
