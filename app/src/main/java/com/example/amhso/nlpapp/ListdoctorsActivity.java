package com.example.amhso.nlpapp;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListdoctorsActivity extends AppCompatActivity {


    public String[] DoctorsList ;
    public int index;
    public int expertise;
    public int part;

    public ListView lv;
    public StringAndIntegers [] ListStringInteger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdoctors);

        G.activity=this;


        Bundle bundle = getIntent().getExtras();
        String s=bundle.getString("expertise");


        expertise=Integer.parseInt(s);
        s=bundle.getString("part");
        part=Integer.parseInt(s);

        index=0;



        lv = (ListView)findViewById(R.id.list_doctor);

        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

        String Code = sharedpreferences.getString(G.code, "");
        new sendhttp(G.FetchlistDoctor+"?expertise="+expertise+"&part="+part+"&code="+Code,"aaa",Code).execute();







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


            Log.i("result", "result: "+result);


            if(progressDialog.isShowing())
                progressDialog.dismiss();



            String s[]=result.split("~");


            if(s[0].equals("doctor")) {


                try {


                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(s[1]);
                    contacts = jsonObj.getJSONArray("contacts");


                    DoctorsList = new String[contacts.length()];
                    ListStringInteger = new StringAndIntegers[contacts.length()];

                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String mobile = c.getString("mobile");
                        String address = c.getString("address");
                        String salery_d = c.getString("salery");


                        String text= name + "  "+mobile+"  "+address+"  "+salery_d;

                        ListStringInteger[index] = new StringAndIntegers(text, Integer.parseInt(id));
                        DoctorsList[index] = text;
                        index++;
                    }





                    ArrayAdapter adapter = new ArrayAdapter<String>(G.activity,
                            R.layout.activity_listview, DoctorsList);
                    lv.setAdapter(adapter);



//                    ArrayAdapter adapter = new ArrayAdapter<String>(G.activity,
//                            R.layout.activity_listview, DoctorsList);
//
//
//
//                    lv.setAdapter(adapter);


                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {


//                            int id_turn = ListStringInteger[(int) id].getInteger();
//                            SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
//
//                            String Code = sharedpreferences.getString(G.code, "");
//                            new sendhttp(G.Turn + "?id_turn=" + id_turn, "aaa", Code).execute();



                            Intent myIntent = new Intent(G.activity,TurnActivity.class);
                            myIntent.putExtra("id_doctor",ListStringInteger[(int) id].getInteger());
                            G.activity.startActivity(myIntent);


                        }
                    });


                } catch (Exception e) {


                    Log.i("eeeeeee", "eeeeeee: " + e.toString());
//
                    Toast.makeText(G.activity, "برای این منتطفه و تخصص هیچ نوبتی تعریف نشده است.", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(G.activity, MenuActivity.class);
                    G.activity.startActivity(myIntent);


                }

            }
            else{

            }



        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(ListdoctorsActivity.this,
                    "لطفاً منتظر بمانید",
                    "با تشکر");
        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);


                Log.i("ffffffff", "uuuuuuuuuuuuuuuuuu"+url.toString());




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
