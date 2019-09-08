package com.example.smarthome.asrfinishjson;

public class AsrFinishJsonData {

    private String error;
    private String sub_error;
    private String desc;
    private OriginResult originResult;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSub_error() {
        return sub_error;
    }

    public void setSub_error(String sub_error) {
        this.sub_error = sub_error;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public OriginResult getOriginResult() {
        return originResult;
    }

    public void setOriginResult(OriginResult originResult) {
        this.originResult = originResult;
    }
}
