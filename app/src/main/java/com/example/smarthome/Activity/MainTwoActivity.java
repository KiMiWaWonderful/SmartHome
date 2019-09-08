package com.example.smarthome.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;

import java.util.ArrayList;

//选择登录注册的主界面
public class MainTwoActivity extends Activity {

    private Button btnToLogin;
    private Button btnToRegister;

    //初始化界面
    private void initView(){
        btnToLogin = findViewById(R.id.btn_to_login);
        btnToRegister = findViewById(R.id.btn_to_register);
    }

    //android 6.0 以上需要动态申请权限
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm :permissions){
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }

    //注册点击事件
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_to_login:
                    Intent intent1 = new Intent(MainTwoActivity.this,LoginActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_to_register:
                    Intent intent2 = new Intent(MainTwoActivity.this,RegisterActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        initPermission();
        initView();
        btnToRegister.setOnClickListener(new OnClick());
        btnToLogin.setOnClickListener(new OnClick());
    }
}
