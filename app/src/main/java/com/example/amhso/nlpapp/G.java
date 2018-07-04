package com.example.amhso.nlpapp;



import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.amhso.nlpapp.otherclass.FontsOverride;


public class G extends Application {

    public static Context context;
    public static Activity activity;

    public static String urlserver="http://www.darmlneh.com/android/";
    public static String ServerImg="http://www.darmlneh.com/uploads/";
    public static String urlwebview="http://www.darmlneh.com/";



    public static final String MyPREFERENCES = "MyPrefs";
    public static final String id_sick = "id_sick";
    public static final String code = "code";
    public static final String lang = "lang";





    public static final String CheckLoginUrl= G.urlserver+"auth";
    public static final String FetchlistCity= G.urlserver+"fetchlistcity";
    public static final String FetchlistOstan= G.urlserver+"fetchlistostan";
    public static final String FetchlistShahrestan= G.urlserver+"fetchlistshahrestan";
    public static final String FetchlistPart= G.urlserver+"fetchlistpart";
    public static final String FetchlistDoctor= G.urlserver+"fetchlistdoctor";
    public static final String FetchlistTurn= G.urlserver+"fetchlistturn";
    public static final String FetchlistDoctorAll= G.urlserver+"fetchlistdoctorall";

    public static final String Turn= G.urlserver+"turn";
    public static final String FetchListMmpi= G.urlserver+"fetchlistmmpi";
    public static final String FetchListVisit= G.urlserver+"fetchlistvisit";
    public static final String FetchListVisitMmpi= G.urlserver+"fetchlistvisitmmpi";
    public static final String FetchListExpertise= G.urlserver+"fetchlistexpertise";




















    @Override
    public void onCreate() {

        context = getApplicationContext();
        super.onCreate();


        FontsOverride.setDefaultFont(context, "DEFAULT", "pasho_fa.ttf");
        FontsOverride.setDefaultFont(context, "MONOSPACE", "pasho_fa.ttf");
        FontsOverride.setDefaultFont(context, "SERIF", "pasho_fa.ttf");
        FontsOverride.setDefaultFont(context, "SANS_SERIF", "pasho_fa.ttf");



    }




}