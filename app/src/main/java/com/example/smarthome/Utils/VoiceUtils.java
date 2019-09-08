package com.example.smarthome.Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//导入百度语音，设置
public class VoiceUtils {

    private String appId = "16261791";
    private String appKey = "737ndl6pED37wFTdmxhkxR8l";
    private String secretKey = "rET22rMF81cn3kX61WvWNDR1MAnLblZt";

    private SpeechSynthesizer mSpeechSynthesizer;
    private String mSampleDirPath;
    private static final String SAMPLE_DIR_NAME = "baiduTTS";

    private static final String AS = "bd_etts_common_speech_as_mand_eng_high_am_v3.0.0_20170516.dat";
    private static final String F7 = "bd_etts_common_speech_f7_mand_eng_high_am-mix_v3.0.0_20170512.dat";
    private static final String M15 = "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";
    private static final String YYJW = "bd_etts_common_speech_yyjw_mand_eng_high_am-mix_v3.0.0_20170512.dat";
    private static final String TEXT = "bd_etts_text.dat";


    //初始化
    public  void init(Context context, int speaker){
        initialEnv(context);
        initialTts(context,speaker);
    }
    //获取解析器
    public SpeechSynthesizer getSyntheszer(){
        return mSpeechSynthesizer;
    }

    //初始化配置文件
    private void initialEnv(Context context) {
        if (mSampleDirPath == null) {
            String sdcardPath = Environment.getExternalStorageDirectory().toString();
            mSampleDirPath = sdcardPath + "/" + SAMPLE_DIR_NAME;
        }
        makeDir(mSampleDirPath);
        copyFromAssetsToSdcard(context,false, AS, mSampleDirPath + "/" + AS);
        copyFromAssetsToSdcard(context,false, F7, mSampleDirPath + "/" + F7);
        copyFromAssetsToSdcard(context,false, M15, mSampleDirPath + "/" + M15);
        copyFromAssetsToSdcard(context,false, YYJW, mSampleDirPath + "/" + YYJW);
        copyFromAssetsToSdcard(context,false, TEXT, mSampleDirPath + "/" + TEXT);

    }

    private void makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    /**
     * 将资源语音文件复制到手机SD卡中
     * @param isCover 是否覆盖已存在的目标文件
     * @param source
     * @param dest
     */
    private void copyFromAssetsToSdcard(Context context,boolean isCover, String source, String dest) {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = context.getResources().getAssets().open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //初始化解析器
    private void initialTts(Context context,int speaker) {
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        this.mSpeechSynthesizer.setContext(context);
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(new MyListnener());
        // 文本模型文件路径 (离线引擎使用)
//        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
//                + TEXT_MODEL_NAME);
//        // 声学模型文件路径 (离线引擎使用)
//        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
//                + SPEECH_FEMALE_MODEL_NAME);
        // 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
        this.mSpeechSynthesizer.setAppId(appId);
        // 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
        this.mSpeechSynthesizer.setApiKey(appKey, secretKey);
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，4--情感女声，3--普通男声，6--特殊男声。。。）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, speaker+"");
        // 设置Mix模式的合成策略
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
        // 初始化tts
        mSpeechSynthesizer.initTts(TtsMode.MIX);
        // 加载离线英文资源（提供离线英文合成功能)
//        mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath
//                + "/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME);

    }

    //合成监听器
    class MyListnener implements SpeechSynthesizerListener {
        @Override
        public void onSynthesizeStart(String s) {
            //合成准备工作
            sendMessage("准备开始合成,序列号:" + s);
        }
        @Override
        public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {
            //合成数据和进度的回调接口，分多次回调
            Log.i("MessageListener", "合成进度回调, progress：" + i + ";序列号:" + s );
        }
        @Override
        public void onSynthesizeFinish(String s) {
            //合成正常结束，每句合成正常结束都会回调，如果过程中出错，则回调onError，不再回调此接口
            sendMessage("合成结束回调, 序列号:" + s);
        }
        @Override
        public void onSpeechStart(String s) {
            //播放开始，每句播放开始都会回调
            sendMessage("播放开始回调, 序列号:" + s);
        }
        @Override
        public void onSpeechProgressChanged(String s, int i) {
            //播放进度回调接口，分多次回调
            Log.i("MessageListener", "播放进度回调, progress：" + i + ";序列号:" + s );
        }
        @Override
        public void onSpeechFinish(String s) {
            // 播放正常结束，每句播放正常结束都会回调，如果过程中出错，则回调onError,不再回调此接口
            sendMessage("播放结束回调, 序列号:" + s);
        }

        @Override
        public void onError(String s, SpeechError speechError) {
            sendErrorMessage("错误发生：" + speechError.description + "，错误编码："
                    + speechError.code + "，序列号:" + s);
        }

        private void sendMessage(String message){
            sendMessage(message,false);
        }

        private void sendErrorMessage(String message){
            sendMessage(message,true);
        }

        protected void sendMessage(String message, boolean isError){
            if(isError){
                Log.e("MessageListener",message);
            }else {
                Log.i("MessageListener",message);
            }
        }
    }
}
