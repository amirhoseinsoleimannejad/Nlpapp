package com.example.amhso.nlpapp.otherclass;

/**
 * Created by amhso on 18/05/2018.
 */

public class doctor {

    private String name;
    private String id;

    public doctor(String name, String phone){
        this.name=name;
        this.id=phone;

    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }
}
