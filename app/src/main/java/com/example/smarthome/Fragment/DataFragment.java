package com.example.smarthome.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;
import com.example.smarthome.Utils.WebViewUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

//接受数据界面
public class DataFragment extends Fragment {

    private Context context;
    private TextView txtWenDu;
    private TextView txtGuangQiang;
    private TextView txtShiDu;
    private TextView txtYanWuNongDu;
    private TextView txtTianQi;
    private TextView txtZhiNengChaZuoYi;
    private TextView txtHuanQi;
    private TextView txtZhiNengChaZuoEr;
    private TextView txtJiaShi;
    private TextView txtMoShi;

    private TextView txtTemperature;
    private TextView txtLightStrength;
    private TextView txtHumidity;
    private TextView txtSmokeScope;
    private TextView txtWeather;
    private TextView txtSmartSocket1;
    private TextView txtAeration;
    private TextView txtSmartSocket2;
    private TextView txtHumidification;
    private TextView txtPresentMode;

    private Socket client = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    private static Handler handler;
    private String[] strings;
    String subsititue;
    ConnectionApplication connectionApplication;

//    private WebView webView;
//    private FrameLayout frameLayout;
//    String url = "http://47.106.149.91:9000/javascript_simple.html";

    private View view;

    private Typeface typeface;
    private Typeface typeface1;

    //初始化界面
    public void initView(){
       // switchData = view.findViewById(R.id.switch_data);

        txtTemperature = view.findViewById(R.id.txt_temperature);
        txtLightStrength = view.findViewById(R.id.txt_light_strength);
        txtHumidity = view.findViewById(R.id.txt_humidity);
        txtSmokeScope = view.findViewById(R.id.txt_somke_scope);
        txtWeather = view.findViewById(R.id.txt_weather);
        txtSmartSocket1 = view.findViewById(R.id.txt_smart_socket1);
        txtAeration = view.findViewById(R.id.txt_aeration);
        txtSmartSocket2 = view.findViewById(R.id.txt_smart_socket2);
        txtHumidification = view.findViewById(R.id.txt_humidification);
        txtPresentMode = view.findViewById(R.id.txt_present_mode);

        txtWenDu = view.findViewById(R.id.txt_wenDu);
        txtGuangQiang = view.findViewById(R.id.txt_guangQiang);
        txtShiDu = view.findViewById(R.id.txt_shiDu);
        txtYanWuNongDu = view.findViewById(R.id.txt_yanWuNongDu);
        txtTianQi = view.findViewById(R.id.txt_tianQi);
        txtZhiNengChaZuoYi = view.findViewById(R.id.txt_chaZuoYi);
        txtZhiNengChaZuoEr = view.findViewById(R.id.txt_chaZuoEr);
        txtJiaShi = view.findViewById(R.id.txt_jiaShi);
        txtMoShi = view.findViewById(R.id.txt_moShi);
        txtHuanQi = view.findViewById(R.id.txt_huanQi);

//        webView = view.findViewById(R.id.webview);
//        frameLayout = view.findViewById(R.id.flVideoContainer);

        typeface = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont1.ttf");

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data, container, false);

        initView();
        this.context = getActivity();

        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        inputStream = ((ConnectionApplication)getActivity().getApplication()).getInputStream();
        inputStreamReader = ((ConnectionApplication)getActivity().getApplication()).getInputStreamReader();
        bufferedReader = ((ConnectionApplication)getActivity().getApplication()).getBufferedReader();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

//        WebViewUtils.createWebView(webView,url);
//        webView.loadUrl(url);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                while (true) {
                                    Log.e("TAG", "DataFragment");
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
                                                        strings[i] = strings[i].substring(j + 2, chars.length);
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
//        if (webView != null) {
//            webView.destroy();
//        }
        super.onDestroy();
    }
}
