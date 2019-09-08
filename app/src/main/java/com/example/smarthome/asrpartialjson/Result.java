package com.example.smarthome.asrpartialjson;

import java.util.ArrayList;

public class Result {

    private ArrayList<String> uncertain_word;
    private ArrayList<String> word;

    public ArrayList<String> getUncertain_word() {
        return uncertain_word;
    }

    public void setUncertain_word(ArrayList<String> uncertain_word) {
        this.uncertain_word = uncertain_word;
    }

    public ArrayList<String> getWord() {
        return word;
    }

    public void setWord(ArrayList<String> word) {
        this.word = word;
    }
}
