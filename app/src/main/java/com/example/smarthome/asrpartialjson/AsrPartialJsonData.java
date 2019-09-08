package com.example.smarthome.asrpartialjson;

import java.util.ArrayList;

public class AsrPartialJsonData {

    private ArrayList<String> results_recognition;
    private OriginResult originResult;
    private String error;
    private String best_result;
    private String result_type;

    public ArrayList<String> getResults_recognition() {
        return results_recognition;
    }

    public void setResults_recognition(ArrayList<String> results_recognition) {
        this.results_recognition = results_recognition;
    }

    public OriginResult getOriginResult() {
        return originResult;
    }

    public void setOriginResult(OriginResult originResult) {
        this.originResult = originResult;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBest_result() {
        return best_result;
    }

    public void setBest_result(String best_result) {
        this.best_result = best_result;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }
}
