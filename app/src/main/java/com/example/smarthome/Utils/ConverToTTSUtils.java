package com.example.smarthome.Utils;

//将字符串转换为语音输出
public class ConverToTTSUtils {

    public static String convert(String string){
        if(string.equals("WhatAreYouDoing") ){
            return "我在呢";
        }else if(string.equals("TooBright") ){
            return "亮度改变中";
        }else if(string.equals("TooDark")){
            return "亮度改变中";
        }else if(string.equals("LightOff")){
            return "关灯中";
        }else if(string.equals("LightOn")){
            return "开灯中";
        }else if(string.equals("HumidificationOff") ){
            return "加湿器关闭中";
        }else if(string.equals("HumidificationOn") ){
            return "加湿器开启中";
        }else if(string.equals("AerationOff") ){
            return "风扇关闭中";
        }else if(string.equals("AerationOn") ){
            return "风扇打开中";
        }else if(string.equals("RaiseTM")){
            return "温度提高中";
        }else if(string.equals("LowestTmp")){
            return "现在是最低温度";
        }else if(string.equals("ComfortTM")){
            return "现在是舒适温度";
        }else if(string.equals("MakeHeat") ){
            return "切换到制暖模式";
        }else if(string.equals("AirSupply")){
            return "切换到送风模式";
        }else if(string.equals("Dehumi")){
            return "切换到除湿模式";
        }else if(string.equals("Refrige")){
            return "切换到制冷模式";
        }else if(string.equals("AUTO")){
            return "切换到自动模式";
        }else if(string.equals("AirConOff")){
            return "空调关闭中";
        }else if(string.equals("AirConOn")){
            return "空调启动中";
        }else if(string.equals("Restart")){
            return "模块重启中";
        }else if(string.equals("Okay")){
            return "好的";
        }else if(string.equals("GoOut") ){
            return "好的主人";
        }else if(string.equals("DoorOff")){
            return "关门中";
        }else if(string.equals("DoorOn")){
            return "开门中";
        }else if(string.equals("Sleep")){
            return "好的主人";
        }else if(string.equals("Socket2Close") ){
            return "排插二关闭中";
        }else if(string.equals("Socket2Open") ){
            return "排插二开启中";
        }else if(string.equals("Socket1Close")){
            return "排插一关闭中";
        }else if(string.equals("Socket1Open")){
            return "排插一开启中";
        } else if(string.equals("WindChange")){
            return "风向改变中";
        }else if(string.equals("WindUP") ){
            return "风速增强中";
        }else if(string.equals("ReduceTM")){
            return "温度降低中";
        }else if(string.equals("CurtainOn")){
            return "窗帘开启中";
        }else if(string.equals("CurtainOff")){
            return "窗帘关闭中";
        }else if(string.equals("Brightest")){
            return "亮度改变中";
        }else if(string.equals("Darkest")){
            return "亮度改变中";
        }
        else {
            return "有杂音";
        }
    }
}
