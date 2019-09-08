package com.example.smarthome.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smarthome.Application.ConnectionApplication;
//import com.example.smarthome.DataFragment;
import com.example.smarthome.Fragment.ActionFragment;
//import com.example.smarthome.Fragment.AutoFragment;
import com.example.smarthome.Fragment.DataFragment;
import com.example.smarthome.Fragment.SettingFragment;
//import com.example.smarthome.Fragment.SleepFragment;
import com.example.smarthome.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

//登录成功后跳转到的页面，包含了三个Fragment
public class ChooseTwoActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ActionFragment actionFragment;//回家
    private DataFragment dataFragment;//在家
    private SettingFragment settingFragment;//设置

    private Fragment[] fragments = new Fragment[]{};

    private int lastfragment;//用于记录上个选择的Fragment

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_two);

        connectionApplication = (ConnectionApplication) getApplication();
        client = connectionApplication.getSocket();
        printWriter = connectionApplication.getPrintWriter();

        initFragment();
    }

    //初始化fragment和fragment数组
    private void initFragment(){
        actionFragment = new ActionFragment();
        dataFragment = new DataFragment();
        settingFragment = new SettingFragment();
        fragments = new Fragment[]{actionFragment,dataFragment,settingFragment};

        lastfragment=0;

        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,actionFragment).show(actionFragment).commit();
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    //判断选择的菜单

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.tab_menu_action: {

                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;
                    }
                    return true;
                }
                case R.id.tab_menu_data:{
                    if(lastfragment!=1) {
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    return true;
                }
                case R.id.tab_menu_settings:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    //切换Fragment
    private void switchFragment(int lastfragment,int index){

        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false){
            transaction.add(R.id.home_container,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}

