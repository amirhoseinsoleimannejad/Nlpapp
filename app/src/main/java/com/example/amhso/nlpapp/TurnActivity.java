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

public class TurnActivity extends AppCompatActivity {


    public String[] TurnList ;
    public int index;

    public StringAndIntegers [] ListStringInteger;
    public int doctor=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            doctor=bundle.getInt("id_doctor");

        }
        else{
            finish();
        }


        index=0;




        SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

        String Code = sharedpreferences.getString(G.code, "");
        new sendhttp(G.FetchlistTurn+"?id_doctor="+doctor,"aaa",Code).execute();



        ListView lv = (ListView)findViewById(R.id.mobile_list);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

//                Toast.makeText(TurnActivity.this,""+id, Toast.LENGTH_LONG).show();

                int id_turn=ListStringInteger[(int)id].getInteger();
                SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

                String Code = sharedpreferences.getString(G.code, "");
                new sendhttp(G.Turn+"?id_turn="+id_turn +"&code="+Code,"aaa",Code).execute();

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





            if(progressDialog.isShowing())
                progressDialog.dismiss();


            String message[]=this.text.split("~");

            if(message[0].equals("turn") ){

                try {


                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");


                    TurnList=new String[contacts.length()];
                    ListStringInteger=new StringAndIntegers[contacts.length()];
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        ListStringInteger[index]=new StringAndIntegers(name,Integer.parseInt(id));
                        TurnList[index]=name;
                        index++;
                    }




                    ArrayAdapter adapter = new ArrayAdapter<String>(TurnActivity.this,
                            R.layout.activity_listview, TurnList);
                    ListView listView = (ListView) findViewById(R.id.mobile_list);
                    listView.setAdapter(adapter);

                }
                catch (Exception e){



                        Toast.makeText(TurnActivity.this,"برای این پزشک هیچ نوبتی تعریف نشده است.", Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(TurnActivity.this,MenuActivity.class);
                        TurnActivity.this.startActivity(myIntent);




                }
            }



            if(message[0].equals("turnclick") && message[1].equals("1")){
                Toast.makeText(TurnActivity.this,"نوبت به درستی برای شما انجام شد", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(TurnActivity.this,MenuActivity.class);
                TurnActivity.this.startActivity(myIntent);
            }
            else if(message[0].equals("turnclick")){
                Toast.makeText(TurnActivity.this,"به علت ترافیک نوبت دهی را چند دقیقه دیگر امتحان کنید.", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(TurnActivity.this,MenuActivity.class);
                TurnActivity.this.startActivity(myIntent);
            }









        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(TurnActivity.this,
                    "لطفاً منتظر بمانید",
                    "با تشکر");
        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);





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
