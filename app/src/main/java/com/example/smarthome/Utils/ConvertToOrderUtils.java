package com.example.smarthome.Utils;

//将语音转换为字符串
public class ConvertToOrderUtils {

    public static String convert(String string){
        if(string.equals("你在干嘛") || string.equals("你在干吗")){
            return "WhatAreYouDoing";
        }else if(string.equals("太亮了") || string.equals("太亮")){
            return "TooBright";
        }else if(string.equals("太暗了")){
            return "TooDark";
        }else if(string.equals("关灯")){
            return "LightOff";
        }else if(string.equals("开灯")){
            return "LightOn";
        }else if(string.equals("请关加湿器") || string.equals("景观加湿器") ||string.equals("秦观加湿器")){
            return "HumidificationOff";
        }else if(string.equals("请开加湿器") || string.equals("请开家湿器") || string.equals("请开家湿气")){
            return "HumidificationOn";
        }else if(string.equals("请关风扇") || string.equals("请观风扇")){
            return "AerationOff";
        }else if(string.equals("请开风扇") || string.equals("请开风扇")){
            return "AerationOn";
        }else if(string.equals("提高温度")){
            return "RaiseTM";
        }else if(string.equals("最低温度")){
            return "LowestTmp";
        }else if(string.equals("舒适温度") || string.equals("淑适温度") || string.equals("舒氏温度") || string.equals("苏轼温")){
            return "ComfortTM";
        }else if(string.equals("制暖模式") || string.equals("制暖制暖模式")){
            return "MakeHeat";
        }else if(string.equals("送风模式")){
            return "AirSupply";
        }else if(string.equals("除湿模式")){
            return "Dehumi";
        }else if(string.equals("制冷模式")){
            return "Refrige";
        }else if(string.equals("自动模式")){
            return "AUTO";
        }else if(string.equals("关闭空调")){
            return "AirConOff";
        }else if(string.equals("启动空调")){
            return "AirConOn";
        }else if(string.equals("模块重启")){
            return "Restart";
        }else if(string.equals("没事了")){
            return "Okay";
        }else if(string.equals("我要出门啦") || string.equals("我要出门了")){
            return "GoOut";
        }else if(string.equals("关门")){
            return "DoorOff";
        }else if(string.equals("开门")){
            return "DoorOn";
        }else if(string.equals("我要睡了")){
            return "Sleep";
        }else if(string.equals("排插二") || string.equals("排插案") || string.equals("排钻胺")){
            return "Socket2";
        }else if(string.equals("排插一")){
            return "Socket1";
        } else if(string.equals("改变风向")){
            return "WindChange";
        }else if(string.equals("增强风速") || string.equals("增强分数") || string.equals("增强的风俗")){
            return "WindUP";
        }else if(string.equals("降低温度")){
            return "ReduceTM";
        }else if(string.equals("开窗")){
            return "CurtainOn";
        }else if(string.equals("关窗")){
            return "CurtainOff";
        }else if(string.equals("最大亮度")){
            return "Brightest";
        }else if(string.equals("最小亮度")){
            return "Darkest";
        }
        else {
            return "无法识别";
        }
    }
}
