package com.example.amhso.nlpapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private String lang;

    @Override
    public void onBackPressed() {

        finishAffinity();
        System.exit(0);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);


        lang = sharedpreferences.getString(G.lang ,null);


        Log.i("lang", "dddddddddddddddd "+lang);
        if(lang.equals("ps")){

            Button turn = (Button) findViewById(R.id.turn);
            turn.setText("نویت اخیستنه");



            Button test = (Button) findViewById(R.id.test);
            test.setText("د Mmpi ازموینه");


            Button visit = (Button) findViewById(R.id.visit);
            visit.setText("ټول اسناد وګورئ");


            Button mmpi = (Button) findViewById(R.id.mmpi);
            mmpi.setText("د ازموینې حالت اخیستل");



            Button chat_all = (Button) findViewById(R.id.chatall);
            chat_all.setText("د ډاکټرانو څانګه");


            Button chat_private = (Button) findViewById(R.id.list_doctor_chat);
            chat_private.setText("د اړیکو لپاره د ډاکټرانو لیست");


            Button list_doctor = (Button) findViewById(R.id.list_doctor);
            list_doctor.setText("د ډاکټرانو لیست");




            Button exit_lan = (Button) findViewById(R.id.exit);
            exit_lan.setText("وتنه");


        }

        Button turn= (Button) findViewById(R.id.turn);
        turn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        CategoryturnActivity.class);
                startActivity(myIntent);
            }
        });



        Button test= (Button) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        MmpiActivity.class);
                startActivity(myIntent);
            }
        });





        Button visit= (Button) findViewById(R.id.visit);
        visit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        VisitActivity.class);
                startActivity(myIntent);
            }
        });





        Button mmpi= (Button) findViewById(R.id.mmpi);
        mmpi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        VisitmmpiActivity.class);
                startActivity(myIntent);
            }
        });






        Button chatall= (Button) findViewById(R.id.chatall);
        chatall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        ChatallActivity.class);
                startActivity(myIntent);

            }
        });







        Button chatprivate= (Button) findViewById(R.id.list_doctor_chat);
        chatprivate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        ListdoctorchatActivity.class);
                startActivity(myIntent);

            }
        });








        Button list_doctor= (Button) findViewById(R.id.list_doctor);
        list_doctor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MenuActivity.this,
                        CategoryturnActivity.class);
                myIntent.putExtra("all",true);
                startActivity(myIntent);

            }
        });







        Button exit= (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                sharedpreferences.edit().clear().apply();

                Intent myIntent = new Intent(MenuActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
                finish();

            }
        });




    }
}
