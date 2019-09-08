package com.example.smarthome.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.Fragment.ActionFragment;
import com.example.smarthome.Fragment.ActionThreeFragment;
import com.example.smarthome.Fragment.ActionTwoFragment;
import com.example.smarthome.Fragment.CameraFragment;
import com.example.smarthome.Fragment.DataFragment;
import com.example.smarthome.Fragment.DataTwoFragment;
import com.example.smarthome.Fragment.FlowerFragment;
import com.example.smarthome.Fragment.ModeFragment;
import com.example.smarthome.Fragment.SettingFragment;
import com.example.smarthome.R;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainFourActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FlowingDrawer flowingDrawer;
    private NavigationView navigationView;

    //private ActionFragment actionFragment;
    private DataFragment dataFragment;
   // private SettingFragment settingFragment;//设置
    //private ActionTwoFragment actionTwoFragment;
    private ActionThreeFragment actionThreeFragment;
    private ModeFragment modeFragment;//情景模式
    private DataTwoFragment dataTwoFragment;
    private FlowerFragment flowerFragment;
    private CameraFragment cameraFragment;
    private Fragment[] fragments = new Fragment[]{};
    private int lastfragment;//用于记录上个选择的Fragment

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    //初始化fragment和fragment数组
    private void initFragment(){
     //   actionFragment = new ActionFragment();
        actionThreeFragment = new ActionThreeFragment();
        dataFragment = new DataFragment();
        //settingFragment = new SettingFragment();
        modeFragment = new ModeFragment();
        dataTwoFragment = new DataTwoFragment();
        flowerFragment = new FlowerFragment();
        cameraFragment = new CameraFragment();
        fragments = new Fragment[]{actionThreeFragment,modeFragment,dataFragment,flowerFragment,cameraFragment};

        lastfragment=0;

        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,actionThreeFragment).show(actionThreeFragment).commit();
    }


    //切换Fragment
    private void switchFragment(int lastfragment,int index){

        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false){
            transaction.add(R.id.home_container,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_three);

        flowingDrawer = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.vNavigation);

        navigationView.setNavigationItemSelectedListener(this);
        flowingDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        connectionApplication = (ConnectionApplication) getApplication();
        client = connectionApplication.getSocket();
        printWriter = connectionApplication.getPrintWriter();

        initFragment();
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
        //toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_action) {
            flowingDrawer.closeMenu(true);
            // Handle the camera action
            if (lastfragment != 0) {
                switchFragment(lastfragment, 0);
                lastfragment = 0;
            }
            return true;
        } else if (id == R.id.menu_mode) {
            flowingDrawer.closeMenu(true);
            if(lastfragment!=1) {
                switchFragment(lastfragment,1);
                lastfragment=1;
            }
            return true;
        } else if (id == R.id.menu_data) {
            flowingDrawer.closeMenu(true);
            if(lastfragment!=2)
            {
                switchFragment(lastfragment,2);
                lastfragment=2;
            }
            return true;
        } else if (id == R.id.menu_flower) {
            flowingDrawer.closeMenu(true);
            if(lastfragment!=3)
            {
                switchFragment(lastfragment,3);
                lastfragment=3;
            }
            return true;
        } else if (id == R.id.menu_safe) {
            flowingDrawer.closeMenu(true);
            if(lastfragment!=4)
            {
                switchFragment(lastfragment,4);
                lastfragment=4;
            }
            return true;
        }
       // DrawerLayout drawer = findViewById(R.id.drawer_layout);
      //  flowingDrawer.closeMenu(true);
      //  flowingDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (flowingDrawer.isMenuVisible()) {
            flowingDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
