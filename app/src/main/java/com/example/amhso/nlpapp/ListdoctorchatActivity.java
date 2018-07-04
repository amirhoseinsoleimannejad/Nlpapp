package com.example.amhso.nlpapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.amhso.nlpapp.otherclass.doctor;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListdoctorchatActivity extends AppCompatActivity {


    public ListView lv;
    public doctor d[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdoctorchat);


        G.activity=this;

        lv = (ListView)findViewById(R.id.list_doctor);

        HttpPostAsyncTask task = new HttpPostAsyncTask();
        task.execute(G.urlserver + "fetch_doctor_sick_link");



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Log.i("ppppp", "ppppppppppppppppppppppp"+position);
                Intent i = new Intent(G.activity, ChatprivateActivity.class);
                i.putExtra("id_doctor", d[position].getId());
                G.activity.startActivity(i);

            }
        });

    }













    public class HttpPostAsyncTask extends AsyncTask<String, String, String> {


        HttpPost httppost;
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        public ProgressDialog progressDialog;



        @Override
        protected void onPostExecute(String result) {

            Log.i("22222222222222222", "22222222222222222222222222" + result);


            try{
                progressDialog.dismiss();

            }
            catch (Exception e){

            }



            try {


                JSONArray contacts;
                JSONObject jsonObj = new JSONObject(result);
                contacts = jsonObj.getJSONArray("doctor");


                d =new doctor[contacts.length()];


                for (int i = 0; i < contacts.length(); i++) {

                    JSONObject c = contacts.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String phone = c.getString("phone");
                    String mobile = c.getString("mobile");



                    String text =name+"  "+phone+"  "+mobile;

                    d[i]=new doctor(text , id);


                }


                String s[]=new String[d.length];
                for (int i=0; i<d.length ; i++){
                    s[i]=d[i].getName();
                }



                ArrayAdapter adapter = new ArrayAdapter<String>(G.activity,
                        R.layout.activity_listview, s);
                lv.setAdapter(adapter);





            } catch (Exception e) {


                Log.i("eeeeeee", "eeeeeee: " + e.toString());
//
                Toast.makeText(G.activity, "برای شما هیچ پزشکی تعیین نشده است.", Toast.LENGTH_LONG).show();


            }


        }






        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(G.activity,
                    "لطفاً منتظر بمانید",
                    "با تشکر");
        }



        // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
        @Override
        protected String doInBackground(String... params) {

            try {


                Log.i("urluuuuuuuuuuuuuuu", "doInBackground: "+params[0]);

                httpclient=new DefaultHttpClient();
                httppost= new HttpPost(params[0]); // make sure the url is correct.
                //add your data

                Log.i("uuuuuu", "urluuuuuuuuuuuu "+params[0]);


                nameValuePairs = new ArrayList<NameValuePair>(2);



                SharedPreferences sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

                Log.i("dddddddddd", "doInBackground: "+sharedpreferences.getString(G.id_sick ,"0")+"".trim());

                nameValuePairs.add(new BasicNameValuePair("id_sick",sharedpreferences.getString(G.id_sick ,"0")+"".trim()));

//                nameValuePairs.add(new BasicNameValuePair("id_user","27".trim()));


                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String response = httpclient.execute(httppost, responseHandler);
                System.out.println("Response : " + response);
                return response;



            } catch (Exception e) {
                Log.i("error rrrrrrr", e.toString());
            }

            return "0";
        }
    }
}
