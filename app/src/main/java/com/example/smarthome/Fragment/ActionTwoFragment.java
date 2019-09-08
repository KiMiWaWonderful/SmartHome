package com.example.smarthome.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ActionTwoFragment extends Fragment {

    private View view;
    private Context context;
    private FloatingActionButton btnRestart;
    private FloatingActionButton btnAeration;
    private FloatingActionButton btnLight;
    private FloatingActionButton btnSmartSocket1;
    private FloatingActionButton btnSmartSocket2;
    private FloatingActionButton btnHumidification;
    private FloatingActionButton btnCurtain;
    private FloatingActionButton btnTurnUpTemperature;
    private FloatingActionButton btnTurnDownTemperature;
    private FloatingActionButton btnComfortTemperature;

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    public void initView(){
        btnRestart = view.findViewById(R.id.action_two_fab_switch);
        btnAeration = view.findViewById(R.id.action_two_fab_paiqishan);
        btnLight = view.findViewById(R.id.action_two_fab_light);
        btnSmartSocket1 = view.findViewById(R.id.action_two_fab_socket1);
        btnSmartSocket2 = view.findViewById(R.id.action_two_fab_socket2);
        btnHumidification = view.findViewById(R.id.action_two_fab_jiashiqi);
        btnCurtain = view.findViewById(R.id.action_two_fab_chuanglian);
        btnTurnUpTemperature = view.findViewById(R.id.action_two_fab_tigaowendu);
        btnTurnDownTemperature = view.findViewById(R.id.action_two_fab_jiangdiwendu);
        btnComfortTemperature = view.findViewById(R.id.action_two_fab_shushiwendu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_action_two,container,false);
        initView();
        this.context = getActivity();
        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

        btnRestart.setOnClickListener(new OnClick());
        btnAeration.setOnClickListener(new OnClick());
        btnLight.setOnClickListener(new OnClick());
        btnSmartSocket1.setOnClickListener(new OnClick());
        btnSmartSocket2.setOnClickListener(new OnClick());
        btnHumidification.setOnClickListener(new OnClick());
        btnTurnUpTemperature.setOnClickListener(new OnClick());
        btnTurnDownTemperature.setOnClickListener(new OnClick());
        btnComfortTemperature.setOnClickListener(new OnClick());
        return view;
    }



    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.action_two_fab_switch:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Restart");
                                String  s = "Restart";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"重启",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_paiqishan:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Aeration");
                                String s = "Aeration";
                                printWriter.println(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"换气",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_light:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Light");
                                String s = "Light";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"灯",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_socket1:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Socket1");
                                String s = "Socket1";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"插座1",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_socket2:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Socket2");
                                String s = "Socket2";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"插座2",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_jiashiqi:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Humidification");
                                String s = "Humidification";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"加湿",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_chuanglian:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Curtain");
                                String  s = "Curtain";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"窗帘",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_tigaowendu:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","RaiseTM");
                                String  s = "RaiseTM";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"提高温度",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_jiangdiwendu:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","ReduceTM");
                                String  s = "ReduceTM";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"降低温度",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_two_fab_shushiwendu:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","ComfortTM");
                                String  s = "ComfortTM";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"舒适温度",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
