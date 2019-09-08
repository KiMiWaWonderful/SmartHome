package com.example.smarthome.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.tts.client.SpeechSynthesizer;
import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;
import com.example.smarthome.Utils.ConverToTTSUtils;
import com.example.smarthome.Utils.ConvertToOrderUtils;
import com.example.smarthome.Utils.VoiceUtils;
import com.example.smarthome.Utils.WebViewUtils;
import com.example.smarthome.asrfinishjson.AsrFinishJsonData;
import com.example.smarthome.asrpartialjson.AsrPartialJsonData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

//发送指令界面
public class ActionFragment extends Fragment implements EventListener {

    private static final String TAG = "ActionFragment";

   // private Switch switchAction;

    private Context context;

    private View view;

    private Button btnBack;//回家模式
    private Button btnLeave;//离家模式
    private Button btnAuto;//自动模式
    private Button btnSleep;//睡眠模式

    private Button btnRestart;//重启
    private  Button btnAeration;//换气
    private  Button btnLight;//灯
    private Button btnSmartSocket1;//插座1
    private Button btnDoor;//门
    private  Button btnHumidification;//加湿
    private Button btnCurtain;//窗帘
    private  Button btnSmartSocket2;//插座2
    private Button btnAirCondition;//空调
    private Button btnTurnUpTemperature;//升温
    private Button btnTurnDownTemperature;//降温
    private Button btnComfortTemperature;//舒适温度
    private Button btnCoolMode;//制冷
    private Button btnWindMode;//送风
    private Button btnMoistMode;//除湿
    private Button btnWarmMode;//制暖
    private Button btnAutoMode;//自动
    private Button btnStrengthMode;//增大风速

   // private TextView txtYuYin;
    private TextView txtHuiJiaMoShi;
    private TextView txtLiJiaMoShi;
    private TextView txtZiDonggMoShi;
    private TextView txtShuiMianMoShi;
    private TextView txtChongQi;
    private TextView txtHuanQiEr;
    private TextView txtDeng;
    private TextView txtChaZuoYiEr;
    private TextView txtMen;
    private TextView txtJiaShiEr;
    private TextView txtChuangLian;
    private TextView txtChaZuoErEr;
    private TextView txtKongTiao;
    private TextView txtTiGaoWenDu;
    private TextView txtJiangDiWenDu;
    private TextView txtShuShiWenDu;
    private TextView txtZhiLengMoShi;
    private TextView txtSongFengMoShi;
    private TextView txtChuShiMoShi;
    private TextView txtZhiNuanMoShi;
    private TextView txtZiDongMoShi;
    private TextView txtZengDaMoShi;

    private Typeface typeface;
    private Typeface typeface1;
    private Typeface typeface2;
    private Typeface typeface3;

    private byte[] buffer;

    private WebView webView;
    private FrameLayout frameLayout;
    private String url = "http://47.106.149.91:9000/javascript_simple.html";

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    private SpeechSynthesizer mSpeechSynthesizer;
    private EventManager asr;
    private Button btnStartRecord;
    private String final_result;

    public void initView(){
      //  switchAction = view.findViewById(R.id.switch_action);

        btnBack = view.findViewById(R.id.btn_back_mode);
        btnLeave = view.findViewById(R.id.btn_leave_mode);
        btnAuto = view.findViewById(R.id.btn_auto_mode);
        btnSleep = view.findViewById(R.id.btn_sleep_mode);


        btnStartRecord = view.findViewById(R.id.btn_start_record);
        btnAeration = view.findViewById(R.id.btn_aeration);
        btnCurtain = view.findViewById(R.id.btn_curtain);
        btnDoor = view.findViewById(R.id.btn_door);
        btnHumidification = view.findViewById(R.id.btn_humidification);
        btnLight = view.findViewById(R.id.btn_light);
        btnSmartSocket1 = view.findViewById(R.id.btn_socket1);
        btnSmartSocket2 = view.findViewById(R.id.btn_socket2);
        btnAirCondition = view.findViewById(R.id.btn_airCondition);
        btnRestart = view.findViewById(R.id.btn_restart);
        btnTurnUpTemperature = view.findViewById(R.id.btn_turnUpTemperature);
        btnTurnDownTemperature = view.findViewById(R.id.btn_turnDownTemperature);
        btnComfortTemperature = view.findViewById(R.id.btn_comfortTemperature);
        btnCoolMode = view.findViewById(R.id.btn_coolMode);
        btnWindMode = view.findViewById(R.id.btn_windMode);
        btnMoistMode = view.findViewById(R.id.btn_moistMode);
        btnWarmMode = view.findViewById(R.id.btn_warmMode);
        btnAutoMode = view.findViewById(R.id.btn_autoMode);
        btnStrengthMode = view.findViewById(R.id.btn_strengthMode);

//        txtYuYin = view.findViewById(R.id.txt_start_speaking);
        txtHuiJiaMoShi = view.findViewById(R.id.txt_back_mode);
        txtLiJiaMoShi = view.findViewById(R.id.txt_leave_mode);
        txtZiDonggMoShi = view.findViewById(R.id.txt_auto_mode);
        txtShuiMianMoShi = view.findViewById(R.id.txt_sleep_mode);
        txtChongQi = view.findViewById(R.id.txt_chongQi);
        txtHuanQiEr = view.findViewById(R.id.txt_huanQiEr);
        txtDeng = view.findViewById(R.id.txt_deng);
        txtChaZuoYiEr = view.findViewById(R.id.txt_chaZuoYiEr);
        txtMen = view.findViewById(R.id.txt_men);
        txtJiaShiEr = view.findViewById(R.id.txt_jiaShiEr);
        txtChuangLian = view.findViewById(R.id.txt_chuangLian);
        txtChaZuoErEr = view.findViewById(R.id.txt_chaZuoErEr);
        txtKongTiao = view.findViewById(R.id.txt_kongTiao);
        txtTiGaoWenDu = view.findViewById(R.id.txt_tiGaoWenDu);
        txtJiangDiWenDu = view.findViewById(R.id.txt_jiangDiWenDu);
        txtShuShiWenDu = view.findViewById(R.id.txt_shuShiWenDu);
        txtZhiLengMoShi = view.findViewById(R.id.txt_zhiLengMoShi);
        txtSongFengMoShi = view.findViewById(R.id.txt_songFengMoShi);
        txtChuShiMoShi = view.findViewById(R.id.txt_chuShiMoShi);
        txtZhiNuanMoShi = view.findViewById(R.id.txt_zhiNuanMoShi);
        txtZiDongMoShi = view.findViewById(R.id.txt_ziDongMoShi);
        txtZengDaMoShi = view.findViewById(R.id.txt_zengDaMoShi);

        webView = view.findViewById(R.id.webview);
        frameLayout = view.findViewById(R.id.flVideoContainer);

        typeface = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont.ttf");
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont1.ttf");
        typeface2 = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont2.ttf");
        typeface3 = Typeface.createFromAsset(getActivity().getAssets(),"font/iconfont3.ttf");

       // txtYuYin.setTypeface(typeface3);
        txtHuiJiaMoShi.setTypeface(typeface3);
        txtLiJiaMoShi.setTypeface(typeface3);
        txtShuiMianMoShi.setTypeface(typeface3);
        txtZiDonggMoShi.setTypeface(typeface3);
        txtChongQi.setTypeface(typeface);
        txtHuanQiEr.setTypeface(typeface2);
        txtDeng.setTypeface(typeface);
        txtChaZuoYiEr.setTypeface(typeface);
        txtMen.setTypeface(typeface);
        txtJiaShiEr.setTypeface(typeface);
        txtChuangLian.setTypeface(typeface);
        txtChaZuoErEr.setTypeface(typeface);
        txtKongTiao.setTypeface(typeface2);
        txtTiGaoWenDu.setTypeface(typeface2);
        txtJiangDiWenDu.setTypeface(typeface2);
        txtShuShiWenDu.setTypeface(typeface2);
        txtZhiLengMoShi.setTypeface(typeface2);
        txtSongFengMoShi.setTypeface(typeface2);
        txtChuShiMoShi.setTypeface(typeface2);
        txtZhiNuanMoShi.setTypeface(typeface2);
        txtZiDongMoShi.setTypeface(typeface);
        txtZengDaMoShi.setTypeface(typeface2);
    }

    @SuppressLint("JavascriptInterface")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_action,container,false);

        initView();

        asr = EventManagerFactory.create(getContext(), "asr");
        asr.registerListener(this);

        this.context = getActivity();

        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

        WebViewUtils.createWebView(webView,url);
        webView.loadUrl(url);

//        switchAction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(!isChecked){
//                    btnAeration.setEnabled(false);
//                    btnCurtain.setEnabled(false);
//                    btnDoor.setEnabled(false);
//                    btnHumidification.setEnabled(false);
//                    btnLight.setEnabled(false);
//                    btnSmartSocket1.setEnabled(false);
//                    btnSmartSocket2.setEnabled(false);
//                    btnRestart.setEnabled(false);
//
//                    Toast.makeText(context,"关闭回家模式",Toast.LENGTH_SHORT).show();
//                }else {
//
//                    //发送指示开启这个模式
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String s = "ActionOn";
//                            printWriter.println(s);
//                            printWriter.flush();
//                        }
//                    }).start();
//
//                    //Toast.makeText(context,"开启回家模式",Toast.LENGTH_SHORT).show();
//
//                    Log.e("TAG", "ActionFragment");

                    //回家模式
                    btnBack.setOnClickListener(new OnClick());

                   //离家模式
                    btnLeave.setOnClickListener(new OnClick());

                    //自动模式
                    btnAuto.setOnClickListener(new OnClick());

                    //睡眠模式
                    btnSleep.setOnClickListener(new OnClick());

                    //开始说话
                    btnStartRecord.setOnClickListener(new OnClick());

                    //换气
                    btnAeration.setOnClickListener(new OnClick());

                    //窗帘
                    btnCurtain.setOnClickListener(new OnClick());

                    //门
                    btnDoor.setOnClickListener(new OnClick());

                    //加湿
                    btnHumidification.setOnClickListener(new OnClick());

                    //灯
                    btnLight.setOnClickListener(new OnClick());

                    //插座1
                    btnSmartSocket1.setOnClickListener(new OnClick());

                    //插座2
                    btnSmartSocket2.setOnClickListener(new OnClick());

                    //重启
                    btnRestart.setOnClickListener(new OnClick());

                    //空调
                    btnAirCondition.setOnClickListener(new OnClick());

                    //提高温度
                    btnTurnUpTemperature.setOnClickListener(new OnClick());

                    //降低温度
                    btnTurnDownTemperature.setOnClickListener(new OnClick());

                    //舒适温度
                    btnComfortTemperature.setOnClickListener(new OnClick());

                    //制冷模式
                    btnCoolMode.setOnClickListener(new OnClick());

                    //送风模式
                    btnWindMode.setOnClickListener(new OnClick());

                    //除湿模式
                    btnMoistMode.setOnClickListener(new OnClick());

                    //制暖模式
                    btnWarmMode.setOnClickListener(new OnClick());

                    //自动模式
                    btnAutoMode.setOnClickListener(new OnClick());

                    //增大风速
                    btnStrengthMode.setOnClickListener(new OnClick());
//                }
//            }
//        });
        return view;
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        String result = "";

        if (length > 0 && data.length > 0) {
            result += ", 语义解析结果：" + new String(data, offset, length);
        }

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            // 引擎准备就绪，可以开始说话
            result += "引擎准备就绪，可以开始说话";

        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN)) {
            // 检测到用户的已经开始说话
            result += "检测到用户的已经开始说话";

        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)) {
            // 检测到用户的已经停止说话
            result += "检测到用户的已经停止说话";
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // 临时识别结果, 长语音模式需要从此消息中取出结果
            result += "识别临时识别结果";
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
//            Log.d(TAG, "Temp Params:"+params);
            parseAsrPartialJsonData(params);
        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
            // 识别结束， 最终识别结果或可能的错误
            result += "识别结束";
            btnStartRecord.setEnabled(true);
            asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
            Log.d(TAG, "Result Params:"+params);
            parseAsrFinishJsonData(params);
        }
        Log.e("最终识别结果", result);
    }

    private void start() {
        //tvResult.setText("");
        btnStartRecord.setEnabled(false);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        String event = null;
        event = SpeechConstant.ASR_START;
        params.put(SpeechConstant.PID, 1536); // 默认1536
        params.put(SpeechConstant.DECODER, 0); // 纯在线(默认)
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN); // 语音活动检测
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 2000); // 不开启长语音。开启VAD尾点检测，即静音判断的毫秒数。建议设置800ms-3000ms
        params.put(SpeechConstant.ACCEPT_AUDIO_DATA, false);// 是否需要语音音频数据回调
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);// 是否需要语音音量数据回调

        String json = null; //可以替换成自己的json
        json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json
        asr.send(event, json, null, 0, 0);
       // printResult("输入参数：" + json);
    }

    private void speak(int speaker,String text) {
        //若是每次都这样是不是会有内存问题呢？需要思考改进
        VoiceUtils utils=new VoiceUtils();
        utils.init(getContext(),speaker);
        mSpeechSynthesizer=utils.getSyntheszer();
        this.mSpeechSynthesizer.speak(text);
    }

    private void parseAsrPartialJsonData(String data) {
        Log.e(TAG, "parseAsrPartialJsonData data:"+data);
        Gson gson = new Gson();
        AsrPartialJsonData jsonData = gson.fromJson(data, AsrPartialJsonData.class);
        String resultType = jsonData.getResult_type();
        Log.e(TAG, "resultType:"+resultType);
        if(resultType != null && resultType.equals("final_result")){
            final_result = jsonData.getBest_result();
            //  tvParseResult.setText("解析结果：" + final_result);
        }
    }

    private void parseAsrFinishJsonData(String data) {
        Log.e(TAG, "parseAsrFinishJsonData data:"+data);
        Gson gson = new Gson();
        AsrFinishJsonData jsonData = gson.fromJson(data, AsrFinishJsonData.class);
        String desc = jsonData.getDesc();
        if(desc !=null && desc.equals("Speech Recognize success.")){
            //tvParseResult.setText("解析结果:" + final_result);
            // tvParseResult.setText(ConvertToOrderUtils.convert(final_result));
            Log.e("解析结果", final_result);
            final String en = ConvertToOrderUtils.convert(final_result);

            //不使用多线程
            printWriter.println(en);
                    printWriter.flush();
                    Log.e("提示","已发送");

                    //似乎在多线程里没有跳进去
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    printWriter.println(en);
//                    printWriter.flush();
//                    Log.e("提示","已发送");
//                }
//            }).start();
            //tvParseResult.setText(en);
            String tts = ConverToTTSUtils.convert(en);
            speak(4,tts);
            // speak(4,en);
        }else{
            String errorCode = "\n错误码:" + jsonData.getError();
            String errorSubCode = "\n错误子码:"+ jsonData.getSub_error();
            String errorResult = errorCode + errorSubCode;
           // tvParseResult.setText("解析错误,原因是:" + desc + "\n" + errorResult);
            Log.e("解析错误,原因是", desc + "\n" + errorResult);
        }
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btn_back_mode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","在家模式启动");
                                String s = "ActionOn";
                                printWriter.println(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"在家模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_leave_mode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","离家模式启动");
                                String s = "DataOn";
                                printWriter.println(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"离家模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_auto_mode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","自动模式启动");
                                String s = "AutoOn";
                                printWriter.println(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"自动模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_sleep_mode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","睡眠模式启动");
                                String s = "SleepOn";
                                printWriter.println(s);
                                printWriter.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"睡眠模式启动",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_start_record:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            start();
//                        }
//                    }).start();

                    start();

                    Toast.makeText(context,"请开始说话",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_aeration:
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

                case R.id.btn_curtain:
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

                case R.id.btn_door:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Door");
                                String  s = "Door";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"门",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_humidification:
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

                case R.id.btn_light:
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

                case R.id.btn_socket1:
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

                case R.id.btn_socket2:
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

                case R.id.btn_restart:
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

                case R.id.btn_airCondition:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","AirCon");
                                String  s = "AirCon";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"空调",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_turnUpTemperature:
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

                case R.id.btn_turnDownTemperature:
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

                case R.id.btn_comfortTemperature:
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

                case R.id.btn_coolMode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Refrige");
                                String  s = "Refrige";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"制冷模式",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_windMode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","AirSupply");
                                String  s = "AirSupply";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"送风模式",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_moistMode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","Dehumi");
                                String  s = "Dehumi";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"除湿模式",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_warmMode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","MakeHeat");
                                String  s = "MakeHeat";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"制暖模式",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_autoMode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","AUTO");
                                String  s = "AUTO";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"自动模式",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_strengthMode:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.e("TAG","WindUP");
                                String  s = "WindUP";
                                printWriter.println(s);
                                printWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(context,"增大风速",Toast.LENGTH_SHORT).show();
                    break;

                    default:
                        break;
            }
        }
    }


    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }


}
