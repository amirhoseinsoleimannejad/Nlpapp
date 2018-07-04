package com.example.amhso.nlpapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private SharedPreferences sharedpreferences;
    private String checklogin;
    private String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        G.activity=this;


        sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
//        sharedpreferences.edit().remove(G.login).commit();
//        sharedpreferences.edit().remove(G.lang).commit();

        checklogin = sharedpreferences.getString(G.id_sick ,"-1");
        lang = sharedpreferences.getString(G.lang ,null);



        if(isInternetOn() && checklogin.equals("-1") ) {
            Intent myIntent = new Intent(MainActivity.this,
                    LoginsickActivity.class);
            startActivity(myIntent);
            finish();
        }


        else if(isInternetOn() && (!checklogin.equals("-1")) && lang==null){
            Intent myIntent = new Intent(MainActivity.this,
                    LangActivity.class);
            startActivity(myIntent);
            finish();

        }


        else if(isInternetOn() && (!checklogin.equals("-1")) && lang!=null){
            Intent myIntent = new Intent(MainActivity.this,
                    MenuActivity.class);
            startActivity(myIntent);
            finish();

        }


        else{
            Toast.makeText(MainActivity.this, "ابتدا اینترنت را وصل کنید باتشکر", Toast.LENGTH_LONG).show();

            this.finish();
        }
}








    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

//            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {


            return true;
        }
        return true;
    }
}
