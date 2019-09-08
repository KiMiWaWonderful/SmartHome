package com.example.smarthome.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.Fragment.ActionFragment;
import com.example.smarthome.Pojo.User;
import com.example.smarthome.R;
import com.example.smarthome.Utils.DBOpenUtils;

import java.util.ArrayList;

//登录界面
public class LoginActivity extends Activity {

    private EditText editTextLoginUsername;
    private EditText editTextLoginPassword;
    private Button buttonLogin;
    private DBOpenUtils dbOpenUtils;
   ConnectionApplication connectionApplication;

    //初始化界面
    private void initView(){
        editTextLoginUsername = findViewById(R.id.et_loginactivity_username);
        editTextLoginPassword = findViewById(R.id.et_loginactivity_password);
        buttonLogin = findViewById(R.id.bt_loginactivity_login);
    }

    //注册点击事件
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_loginactivity_login:

                    String username = editTextLoginUsername.getText().toString().trim();
                    String password = editTextLoginPassword.getText().toString().trim();
                    if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                        ArrayList<User> users = dbOpenUtils.getAllUsers();
                        boolean match = false;
                        if(users.size() == 0){
                            match = false;
                            Toast.makeText(LoginActivity.this, "未注册，请先注册", Toast.LENGTH_SHORT).show();
                        }else {
                            for (int i = 0; i < users.size() ; i++) {
                                User user = users.get(i);
                                if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                                    match = true;
                                    break;
                                }else {
                                    match = false;
                                }
                            }
                            if(match){
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                connectionApplication.connect();
                              //  Intent intent2 = new Intent(LoginActivity.this, ChooseTwoActivity.class);
                                Intent intent2 = new Intent(LoginActivity.this, MainFourActivity.class);
                                startActivity(intent2);
                            }else {
                                Toast.makeText(LoginActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        initView();
        dbOpenUtils= new DBOpenUtils(this);
        connectionApplication = (ConnectionApplication) getApplication();
        buttonLogin.setOnClickListener(new OnClick());
    }
}
