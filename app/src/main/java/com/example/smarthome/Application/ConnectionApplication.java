package com.example.smarthome.Application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//负责与服务器进行连接,开启数据流
public  class ConnectionApplication extends Application {

    private Context context;
    private Socket socket = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private OutputStream outputStream = null;
    private OutputStreamWriter outputStreamWriter = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    private PrintWriter printWriter = null;


    private static ConnectionApplication connectionApplication;

    public static ConnectionApplication getInstance(){
        return connectionApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connectionApplication = this;
        context = getApplicationContext();
    }

    //连接的线程
    class ConnectThread implements Runnable{
        @Override
        public void run() {
            try {
                Log.e("msg","开始连接");

                socket = new Socket("39.106.177.244",33333);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                //printWriter第一种构造方式
               outputStreamWriter = new OutputStreamWriter(outputStream);
               bufferedWriter = new BufferedWriter(outputStreamWriter);
               printWriter = new PrintWriter(bufferedWriter);
               //printWriter第二种构造方式
               //printWriter = new PrintWriter(outputStream);
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                Log.e("msg","连接成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //断开连接的线程
    class DisconnectThread implements Runnable{
        @Override
        public void run() {
            try {
                if(!socket.isClosed()){
                    printWriter.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    Log.e("msg","断开连接");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect(){

        ConnectThread connectThread = new ConnectThread();
                        Thread thread = new Thread(connectThread);
                        thread.start();
    }

    public void disConnect(){

        DisconnectThread disconnectThread = new DisconnectThread();
                        Thread thread = new Thread(disconnectThread);
                        thread.start();

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStreamReader getInputStreamReader() {
        return inputStreamReader;
    }

    public void setInputStreamReader(InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public OutputStreamWriter getOutputStreamWriter() {
        return outputStreamWriter;
    }

    public void setOutputStreamWriter(OutputStreamWriter outputStreamWriter) {
        this.outputStreamWriter = outputStreamWriter;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }


}
