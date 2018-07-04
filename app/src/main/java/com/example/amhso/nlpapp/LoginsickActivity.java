package com.example.amhso.nlpapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginsickActivity extends AppCompatActivity {



    private Button button;
    private String username;
    private String password;



    @Override
    public void onBackPressed() {

        finishAffinity();
        System.exit(0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsick);




        G.activity=this;


        Button SendButton = (Button) findViewById(R.id.send);




        Bundle bundle = this.getIntent().getExtras();
        if(bundle!= null)
        {
            boolean isActivityToBeFinish =  this.getIntent().getExtras().getBoolean("finishstatus");
            if(isActivityToBeFinish)
            {

                this.finish();
            }
        }

        // Capture button clicks
        SendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {




                EditText Epassword=(EditText) findViewById(R.id.pass);




                password=Epassword.getText().toString();


                SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(G.code,password);
                editor.commit();



                new sendhttp(G.CheckLoginUrl+"?code="+password,"aaa",password).execute();


            }
        });

















        Button signup=(Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {




                Intent myIntent = new Intent(G.activity,SignupActivity.class);
                G.activity.startActivity(myIntent);





            }
        });

    }








    public class sendhttp extends AsyncTask<String, String, String> {
        public String text;
        public String  data;
        public String urlstring;

        public String pass;



        public ProgressDialog progressDialog;






        public sendhttp(String urlstring,String data,String pass){


            this.data=data;
            this.urlstring=urlstring;

            this.pass=pass;





        }




        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            this.text=result;


            Log.i("dd", "33333333333333333333333"+result);

            progressDialog.dismiss();

            String message[]=result.split(":");
            if(message[0].equals("1")){//visitore


                SharedPreferences sharedpreferences = LoginsickActivity.this.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(G.id_sick,message[1]);

                editor.commit();



//                sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
//                sharedpreferences.edit().remove(G.login).commit();

               String lang = sharedpreferences.getString(G.lang ,null);

                if(lang!=null){
                    Intent myIntent = new Intent(LoginsickActivity.this,MenuActivity.class);
                    LoginsickActivity.this.startActivity(myIntent);
                    G.activity.finish();
                }
                else{
                    Intent myIntent = new Intent(LoginsickActivity.this,LangActivity.class);
                    LoginsickActivity.this.startActivity(myIntent);
                    G.activity.finish();
                }



            }




            else{
                Toast.makeText(LoginsickActivity.this,"مشخصات وارد شده اشتباه است.", Toast.LENGTH_LONG).show();

            }





        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(LoginsickActivity.this,
                    "لطفاً منتظر بمانید",
                    "با تشکر");
        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);


                Log.i("uuuuuuuuuuuuuuuuu", "doInBackground: "+url.toString());


                // Send POST data request

                HttpURLConnection conn = null;

                conn = (HttpURLConnection) url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = null;

                conn.setRequestProperty(
                        "Authorization",
                        "Basic " + Base64.encodeToString((""+":"+this.pass).getBytes(), Base64.NO_WRAP));
                wr = new OutputStreamWriter(conn.getOutputStream());


                wr.write(this.data);


                wr.flush();


                // Get the server response

                BufferedReader reader = null;

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response

                while ((line = reader.readLine()) != null) {
                    // Append server response in string
                    sb.append(line);

                }


                this.text = sb.toString();

                conn.disconnect();





            }
            catch (Exception e){

                this.text=e.toString();

            }



            return this.text;
        }





    }

}
