package com.example.amhso.nlpapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LangActivity extends AppCompatActivity {



    @Override
    public void onBackPressed() {

        finishAffinity();
        System.exit(0);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang);






        Button fa = (Button) findViewById(R.id.fa);
        fa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(G.lang,"fa");
                editor.commit();


                Intent myIntent = new Intent(LangActivity.this,MenuActivity.class);
                startActivity(myIntent);


            }
        });







        Button ps = (Button) findViewById(R.id.ps);
        ps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(G.lang,"ps");
                editor.commit();



                Intent myIntent = new Intent(LangActivity.this,MenuActivity.class);
                startActivity(myIntent);
            }
        });





    }

}
