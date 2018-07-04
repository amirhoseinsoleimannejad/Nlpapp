package com.example.amhso.nlpapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class StringAndIntegers {

    private String string;
    private int integer;

    public StringAndIntegers(String string,int integer){
        this.integer=integer;
        this.string=string;
    }


    public int getInteger(){
        return this.integer;
    }



    public String getString(){
        return this.string;
    }




}