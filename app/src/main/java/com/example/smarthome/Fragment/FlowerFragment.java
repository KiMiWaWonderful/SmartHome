package com.example.smarthome.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class FlowerFragment extends Fragment {

    private View view;
    private TextView txtSoilMoist;
    private TextView txtLightStrength;
    private TextView txtTemperature;
    private TextView txtAirMoist;

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    public void initView(){
        txtSoilMoist = view.findViewById(R.id.flower_txt_soil_moist);
        txtLightStrength = view.findViewById(R.id.flower_txt_light_strength);
        txtTemperature = view.findViewById(R.id.flower_txt_temperature);
        txtAirMoist = view.findViewById(R.id.flower_txt_air_moist);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flower,container,false);
        initView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
