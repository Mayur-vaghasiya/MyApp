package com.example.myapplication.DHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.Model.User;

import java.util.ArrayList;

/**
 * Created by peacock on 3/11/16.
 */
public class Databasehelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User";
    public static final String TABLEUSER = "Usermaster";
    public static final String KEY_ID = "UId";
    public static final String KEY_USER = "UserName";
    public static final String KEY_EMAIL = "UserEmail";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_GENDER = "Gender";
    public static final String KEY_HOBBIES = "Hobbies";

    SQLiteDatabase db;

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        db = this.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CRETATE_TABLE_USER = "CREATE TABLE " + TABLEUSER + "("
                + "UId INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "UserName TEXT ,"
                + "UserEmail TEXT ,"
                + "Phone TEXT ,"
                + "Gender INTEGER ,"
                + "Hobbies TEXT " + ")";

        db.execSQL(CRETATE_TABLE_USER);
    }

    public void adduser(User user) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE, user.getMobile());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_HOBBIES,user.getHoboies());
        db.insert(TABLEUSER, null, values);
        close();
    }

    public void Deleteuser(User user) {
        open();
        db.delete(TABLEUSER, KEY_ID + "=?", new String[]{String.valueOf(user.getId())});
        close();
    }

    public int Updateuser(User user) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE, user.getMobile());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_HOBBIES, user.getHoboies());
        int i = db.update(TABLEUSER, values, KEY_ID + " = " + user.getId(), null);
        if (i > 0) {
            Log.e("UPATE", "update data" + String.valueOf(i));
        }
        close();
        return i;
    }

    public ArrayList<User> getalluserinfo() {
        ArrayList<User> userinfo = new ArrayList<User>();
         /*db = this.getWritableDatabase();*/
        open();
        Cursor c1 = db.rawQuery("SELECT * FROM  " + TABLEUSER, null);
        if (c1.moveToFirst()) {
            do {
                User u = new User();
                u.setId(c1.getInt(c1.getColumnIndex(KEY_ID)));
                u.setName(c1.getString(c1.getColumnIndex(KEY_USER)));
                u.setEmail(c1.getString(c1.getColumnIndex(KEY_EMAIL)));
                u.setMobile(c1.getString(c1.getColumnIndex(KEY_PHONE)));
                u.setGender(c1.getInt(c1.getColumnIndex(KEY_GENDER)));
                u.setHoboies(c1.getString(c1.getColumnIndex(KEY_HOBBIES)));
                userinfo.add(u);
            } while (c1.moveToNext());
        }
        c1.close();
        close();
        return userinfo;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLEUSER);
        onCreate(db);
    }
}
