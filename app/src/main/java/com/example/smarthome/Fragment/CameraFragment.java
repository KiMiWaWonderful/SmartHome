package com.example.smarthome.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.example.smarthome.Application.ConnectionApplication;
import com.example.smarthome.R;
import com.example.smarthome.Utils.WebViewUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class CameraFragment extends Fragment {

    private Context context;
    private View view;
    private WebView webView;
    private FrameLayout frameLayout;
    private String url = "http://47.106.149.91:9000/javascript_simple.html";

    ConnectionApplication connectionApplication;
    private Socket client = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private PrintWriter printWriter = null;

    public void initView(){
        webView = view.findViewById(R.id.webview);
        frameLayout = view.findViewById(R.id.flVideoContainer);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_camera,container,false);
        initView();
        this.context = getActivity();

        client = ((ConnectionApplication)getActivity().getApplication()).getSocket();
        printWriter = ((ConnectionApplication)getActivity().getApplication()).getPrintWriter();

        WebViewUtils.createWebView(webView,url);
        webView.loadUrl(url);

        return view;
    }

    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
