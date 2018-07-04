package com.example.amhso.nlpapp.otherclass;

/**
 * Created by amhso on 14/05/2018.
 */

public class message {

    public String text;
    public String date;
    public String time;
    public Boolean self;
    public String img;


    public message(String text,String date,String time,Boolean self,String img){

        this.text=text;
        this.date=date;
        this.time=time;
        this.self=self;
        this.img=img;
    }




    public String getText(){
        return this.text;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public String getImg(){
        return this.img;
    }

    public Boolean getSelf(){
        return this.self;
    }

}
