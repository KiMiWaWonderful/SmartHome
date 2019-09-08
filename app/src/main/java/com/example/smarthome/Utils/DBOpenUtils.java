package com.example.smarthome.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.smarthome.Pojo.User;

import java.util.ArrayList;

//操作数据库
public class DBOpenUtils extends SQLiteOpenHelper {


    private SQLiteDatabase sqLiteDatabase;
    public DBOpenUtils(Context context){
        super(context,"db_test",null,1);
        sqLiteDatabase = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");

        Log.e("message","创建表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
        Log.e("message","更新表");
    }

    //添加数据
    public void add(String username, String password){
        sqLiteDatabase.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{username,password});
        Log.e("message","添加表数据");
    }

    //删除数据
    public void delete(String username, String password){
        sqLiteDatabase.execSQL("DELETE FROM user WHERE name = AND password ="+username+password);
    }

    //改变数据
    public void update(String password){
        sqLiteDatabase.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    //查询所有数据
    public ArrayList<User> getAllUsers(){
        Log.e("message","查询表数据");
        ArrayList<User> users = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("user",null,null,null,null,null,"name");
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            users.add(new User(username,password));
        }
        return users;
    }
}
