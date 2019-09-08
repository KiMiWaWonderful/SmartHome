package com.example.smarthome.Activity;

import android.app.Activity;
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

import com.example.smarthome.Fragment.ActionFragment;
//import com.example.smarthome.Pojo.Code;
import com.example.smarthome.R;
import com.example.smarthome.Utils.DBOpenUtils;

//注册界面
public class RegisterActivity extends Activity {

    private EditText editTextRegisterUsername;
    private EditText editTextRegisterPasswordOne;
    private EditText editTextRegisterPasswordTwo;
    private Button buttonRegister;
    private DBOpenUtils dbOpenUtils;

    //初始化界面
    private void initView(){
        editTextRegisterUsername = findViewById(R.id.et_registeractivity_username);
        editTextRegisterPasswordOne = findViewById(R.id.et_registeractivity_password1);
        editTextRegisterPasswordTwo = findViewById(R.id.et_registeractivity_password2);
        buttonRegister = findViewById(R.id.bt_registeractivity_register);
    }

    //注册点击事件
    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_registeractivity_register:
                    String username = editTextRegisterUsername.getText().toString().trim();
                    String passwordOne = editTextRegisterPasswordOne.getText().toString().trim();
                    String passwordTwo = editTextRegisterPasswordTwo.getText().toString().trim();

                    if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(passwordOne) && !TextUtils.isEmpty(passwordTwo) ){
                        if(passwordOne.equals(passwordTwo)) {
                            //将用户名和密码加入到数据库中
                            dbOpenUtils.add(username, passwordOne);
                            Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent2);
                            Toast.makeText(RegisterActivity.this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                        }else {
                                Toast.makeText(RegisterActivity.this, "两次输入的密码不一致,注册失败", Toast.LENGTH_SHORT).show();
                            }
                    }else {
                        Toast.makeText(RegisterActivity.this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_register);
        initView();
        dbOpenUtils = new DBOpenUtils(this);
        buttonRegister.setOnClickListener(new OnClick());
    }
}
