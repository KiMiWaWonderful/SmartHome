package com.example.smarthome.Fragment;

//import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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

public class DataTwoFragment extends Fragment {

    private Context context;
    private View view;
    private String[] strings;

    //iconfont
    private Typeface typeface;
    private Typeface typeface1;
    private TextView txtWenDu;
    private TextView txtGuangQiang;
    private TextView txtShiDu;
    private TextView txtYanWuNongDu;
    private TextView txtTianQi;
    private TextView txtHuanQi;
    private TextView txtZhiNengChaZuoYi;
    private TextView txtZhiNengChaZuoEr;
    private TextView txtJiaShi;
    private TextView txtMoShi;

    private TextView txtTemperature;
    private TextView txtLightStrength;
    private TextView txtHumidity;
    private TextView txtSmokeScope;
    private TextView txtWeather;
    private TextView txtAeration;
    private TextView txtSmartSocket1;
    private TextView txtSmartSocket2;
    private TextView txtHumidification;
    private TextView txtPresentMode;

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
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont1.ttf");

        txtWenDu = view.findViewById(R.id.datatwo_icon_wendu);
        txtGuangQiang = view.findViewById(R.id.datatwo_icon_guangqiang);
        txtShiDu = view.findViewById(R.id.datatwo_icon_shidu);
        txtYanWuNongDu = view.findViewById(R.id.datatwo_icon_yanwunongdu);
        txtTianQi = view.findViewById(R.id.datatwo_icon_tianqi);
        txtHuanQi = view.findViewById(R.id.datatwo_icon_huanqi);
        txtZhiNengChaZuoYi = view.findViewById(R.id.datatwo_icon_chazuoyi);
        txtZhiNengChaZuoEr = view.findViewById(R.id.datatwo_icon_chazuoer);
        txtJiaShi = view.findViewById(R.id.datatwo_icon_jiashi);
        txtMoShi = view.findViewById(R.id.datatwo_icon_moshi);

        txtWenDu.setTypeface(typeface);
        txtGuangQiang.setTypeface(typeface);
        txtShiDu.setTypeface(typeface);
        txtYanWuNongDu.setTypeface(typeface);
        txtTianQi.setTypeface(typeface);
        txtZhiNengChaZuoYi.setTypeface(typeface);
        txtZhiNengChaZuoEr.setTypeface(typeface);
        txtJiaShi.setTypeface(typeface);
        txtHuanQi.setTypeface(typeface);
        txtMoShi.setTypeface(typeface1);

        txtTemperature = view.findViewById(R.id.datatwo_txt_temperature);
        txtLightStrength = view.findViewById(R.id.datatwo_txt_light_strength);
        txtHumidity = view.findViewById(R.id.datatwo_txt_humidity);
        txtSmokeScope = view.findViewById(R.id.datatwo_txt_smoke_scope);
        txtWeather = view.findViewById(R.id.datatwo_txt_weather);
        txtAeration = view.findViewById(R.id.datatwo_txt_aeration);
        txtSmartSocket1 = view.findViewById(R.id.datatwo_txt_socket1);
        txtSmartSocket2 = view.findViewById(R.id.datatwo_txt_socket2);
        txtHumidification = view.findViewById(R.id.datatwo_txt_humidification);
        txtPresentMode = view.findViewById(R.id.datatwo_txt_present_mode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_two,container,false);

        initView();
        this.context = getActivity();

        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        inputStream = ((ConnectionApplication)getActivity().getApplication()).getInputStream();
        inputStreamReader = ((ConnectionApplication)getActivity().getApplication()).getInputStreamReader();
        bufferedReader = ((ConnectionApplication)getActivity().getApplication()).getBufferedReader();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        Log.e("TAG", "DataTwoFragment");
                        int count = 0;
                        while (count == 0) {
                            count = inputStream.available();
                        }
                        Log.e("能读的大小", count + "");
                        byte[] b = new byte[count];
                        inputStream.read(b);
                        String strRead = new String(b);
                        strRead = String.copyValueOf(strRead.toCharArray(), 0, b.length);
                        Log.e("strRead", strRead);

                        if (count > 0) {

                            for (int i = 0; i < strRead.length(); i++) {
                                if (strRead.charAt(i) == 'T') {
                                    strRead = strRead.substring(i, strRead.length());
                                    break;
                                }
                            }

                            Log.e("现在的strRead", strRead);

                            strings = strRead.split("_");

                            for (int i = 0; i < strings.length; i++) {
                                final int index = i;

                                //温度
                                if (strings[i].contains("TM") && strings[i].contains("PM")) {
                                    char[] chars = strings[i].toCharArray();
                                    for (int j = 0; j < chars.length; j++) {
                                        if (chars[j] == 'T') {
                                           // strings[i] = strings[i].substring(j + 2, chars.length);
                                            strings[i] = strings[i].substring(j + 2, chars.length);
                                            Log.e("strings[i]：", strings[i]);
                                            break;
                                        }
                                    }
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String temp = strings[index].substring(0, strings[index].length() - 1);
                                            int tem = Integer.parseInt(temp);
                                            Log.e("温度信息：", tem + "");
                                            if (tem >= 40) {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                stringBuffer.append(strings[index]);
                                                stringBuffer.append("(温度过高！）");
                                                SpannableString spannableString = new SpannableString(stringBuffer.toString());
                                                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, stringBuffer.toString().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                txtTemperature.setText(spannableString);
                                            } else {
                                                Log.e("温度信息：", strings[index]);
                                                txtTemperature.setText(strings[index]);
                                            }
                                        }
                                    });
                                } else if (strings[i].contains("TM")) {
                                    strings[i] = strings[i].replace("TM", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String temp = strings[index].substring(0, strings[index].length() - 1);
                                            int tem = Integer.parseInt(temp);
                                            Log.e("温度信息：", tem + "");
                                            if (tem >= 40) {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                stringBuffer.append(strings[index]);
                                                stringBuffer.append("(温度过高！）");
                                                SpannableString spannableString = new SpannableString(stringBuffer.toString());
                                                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, stringBuffer.toString().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                txtTemperature.setText(spannableString);
                                            } else {
                                                Log.e("温度信息：", strings[index]);
                                                txtTemperature.setText(strings[index]);
                                            }
                                        }
                                    });
                                    //湿度
                                } else if (strings[i].contains("HM")) {
                                    strings[i] = strings[i].replace("HM", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String temp = strings[index].substring(0, strings[index].length() - 1);
                                            int hum = Integer.parseInt(temp);
                                            Log.e("湿度信息：", hum + "");
                                            if (hum >= 100) {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                stringBuffer.append(strings[index]);
                                                stringBuffer.append("(湿度过高！）");
                                                SpannableString spannableString = new SpannableString(stringBuffer.toString());
                                                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, stringBuffer.toString().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                txtHumidity.setText(spannableString);
                                            } else {
                                                txtHumidity.setText(strings[index]);
                                            }
                                        }
                                    });
                                } else if (strings[i].contains("WE")) {
                                    strings[i] = strings[i].replace("WE", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtWeather.setText(strings[index]);
                                        }
                                    });
                                } else if (strings[i].contains("AR")) {

                                    strings[i] = strings[i].replace("AR", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtAeration.setText(strings[index]);
                                        }
                                    });

                                } else if (strings[i].contains("HT")) {

                                    strings[i] = strings[i].replace("HT", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtHumidification.setText(strings[index]);
                                        }
                                    });

                                    //光强
                                } else if (strings[i].contains("LS")) {
                                    strings[i] = strings[i].replace("LS", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String temp = strings[index].substring(0, strings[index].length() - 2);
                                            int ls = Integer.parseInt(temp);
                                            Log.e("光强信息：", ls + "");
                                            if (ls >= 10000) {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                stringBuffer.append(strings[index]);
                                                stringBuffer.append("(光度过强！）");
                                                SpannableString spannableString = new SpannableString(stringBuffer.toString());
                                                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, stringBuffer.toString().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                txtLightStrength.setText(spannableString);
                                            } else {
                                                txtLightStrength.setText(strings[index]);
                                            }
                                        }
                                    });

                                    //烟雾浓度
                                } else if (strings[i].contains("SMS")) {
                                    strings[i] = strings[i].replace("SMS", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String temp = strings[index].substring(0, strings[index].length() - 3);
                                            int sms = Integer.parseInt(temp);
                                            Log.e("烟雾浓度信息：", sms + "");
                                            if (sms >= 30) {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                stringBuffer.append(strings[index]);
                                                stringBuffer.append("(烟雾浓度过高！）");
                                                SpannableString spannableString = new SpannableString(stringBuffer.toString());
                                                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, stringBuffer.toString().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                                txtSmokeScope.setText(spannableString);
                                            } else {
                                                txtSmokeScope.setText(strings[index]);
                                            }
                                        }
                                    });

                                } else if (strings[i].contains("SSO")) {

                                    strings[i] = strings[i].replace("SSO", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtSmartSocket1.setText(strings[index]);
                                        }
                                    });

                                } else if (strings[i].contains("SST")) {

                                    strings[i] = strings[i].replace("SST", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtSmartSocket2.setText(strings[index]);
                                        }
                                    });

                                } else if (strings[i].contains("PM")) {

                                    strings[i] = strings[i].replace("PM", "");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            txtPresentMode.setText(strings[index]);
                                        }
                                    });
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
