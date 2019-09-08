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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ModeFragment extends Fragment {

    private static final String TAG = "ModeFragment";

    private View view;
    private Context context;

    private ImageButton btnBackMode;//回家模式
    private ImageButton btnLeaveMode;//离家模式
    private ImageButton btnAutoMode;//自动模式
    private ImageButton btnSleepMode;//睡眠模式

    private ImageButton btnGetUpMode;//起床模式
    private ImageButton btnMediaMode;//影音
    private ImageButton btnRomanMode;//浪漫
    private ImageButton btnFunMode;//娱乐
    //private Button btnAutoMode;//自动
    private ImageButton btnHuiKeMode;//会客模式

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_try,container,false);
        initView();

        this.context = getActivity();
        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

        btnBackMode.setOnClickListener(new OnClick());
        btnLeaveMode.setOnClickListener(new OnClick());
        btnAutoMode.setOnClickListener(new OnClick());
        btnSleepMode.setOnClickListener(new OnClick());
        btnGetUpMode.setOnClickListener(new OnClick());
        btnMediaMode.setOnClickListener(new OnClick());
        btnRomanMode.setOnClickListener(new OnClick());
        btnFunMode.setOnClickListener(new OnClick());
        btnHuiKeMode.setOnClickListener(new OnClick());

        return view;
    }

    public void initView(){
        btnBackMode = view.findViewById(R.id.mode_btn_zaijia);
        btnLeaveMode = view.findViewById(R.id.mode_btn_lijia);
        btnAutoMode = view.findViewById(R.id.mode_btn_zidong);
        btnSleepMode = view.findViewById(R.id.mode_btn_shuimian);

        btnGetUpMode = view.findViewById(R.id.mode_btn_qichuang);
        btnMediaMode = view.findViewById(R.id.mode_btn_yingyin);
        btnRomanMode = view.findViewById(R.id.mode_btn_langman);
        btnFunMode = view.findViewById(R.id.mode_btn_yule);
        btnHuiKeMode = view.findViewById(R.id.mode_btn_huike);
    }

    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mode_btn_zaijia:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","在家模式启动");
                                String s = "CMD~PCAPP~Mode-HCI*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"在家模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.mode_btn_lijia:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","离家模式启动");
                                String s = "CMD~PCAPP~Mode-LEAVE*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"离家模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.mode_btn_zidong:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","自动模式启动");
                                String s = "CMD~PCAPP~Mode-AUTO*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"自动模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.mode_btn_shuimian:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","睡眠模式启动");
                                String s = "CMD~PCAPP~Mode-SAN*\\r\\n";
                                printWriter.print(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"睡眠模式启动",Toast.LENGTH_SHORT).show();
                    break;

//                case R.id.fab_cool:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.e("TAG","Refrige");
//                                String  s = "Refrige";
//                                printWriter.println(s);
//                                printWriter.flush();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//
//                    Toast.makeText(context,"制冷模式",Toast.LENGTH_SHORT).show();
//                    break;

//                case R.id.fab_wind:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.e("TAG","AirSupply");
//                                String  s = "AirSupply";
//                                printWriter.println(s);
//                                printWriter.flush();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//
//                    Toast.makeText(context,"送风模式",Toast.LENGTH_SHORT).show();
//                    break;

//                case R.id.fab_moist:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.e("TAG","Dehumi");
//                                String  s = "Dehumi";
//                                printWriter.println(s);
//                                printWriter.flush();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//
//                    Toast.makeText(context,"除湿模式",Toast.LENGTH_SHORT).show();
//                    break;

//                case R.id.fab_warm:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.e("TAG","MakeHeat");
//                                String  s = "MakeHeat";
//                                printWriter.println(s);
//                                printWriter.flush();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//
//                    Toast.makeText(context,"制暖模式",Toast.LENGTH_SHORT).show();
//                    break;

//                case R.id.fab_strength:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.e("TAG","WindUP");
//                                String  s = "WindUP";
//                                printWriter.println(s);
//                                printWriter.flush();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//
//                    Toast.makeText(context,"增大风速",Toast.LENGTH_SHORT).show();
//                    break;

                    default:
                        break;

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
