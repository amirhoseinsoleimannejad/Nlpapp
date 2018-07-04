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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class CategoryturnActivity extends AppCompatActivity {

    private Spinner CitySpinner, OstanSpinner,ShahrestanSpinner,PartSpinner,DoctorSpinner;
    private Button btnSubmit;
    private String Code;

    private int expertise=-1;
    private int part=-1;
    private List<StringAndIntegers> listcityname,listostan,listshahrestan,listpart,listexpertise;


    private Boolean all;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryturn);

        G.activity=this;




        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            all=bundle.getBoolean("all");

        }
        else{
            all=false;
        }



        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
        String lang = sharedpreferences.getString(G.lang ,null);
        if(lang.equals("ps")){


            TextView city=(TextView) findViewById(R.id.cityt);
            city.setText("ښارونو");

            TextView ostan=(TextView) findViewById(R.id.ostant);
            ostan.setText("ولایت");


            TextView shahrestan=(TextView) findViewById(R.id.shahrestant);
            shahrestan.setText("ښار");


            TextView part=(TextView) findViewById(R.id.partt);
            part.setText("برخه");


            TextView expertise=(TextView) findViewById(R.id.expertiset);
            expertise.setText("ځانتیا");


            Button turnEnter2 = (Button) findViewById(R.id.turnEnter);

            turnEnter2.setText("جاري ساتل");
        }




        listcityname = new ArrayList<StringAndIntegers>();
        listshahrestan = new ArrayList<StringAndIntegers>();
        listpart = new ArrayList<StringAndIntegers>();
        listostan = new ArrayList<StringAndIntegers>();
        listexpertise = new ArrayList<StringAndIntegers>();



        Button turnEnter = (Button) findViewById(R.id.turnEnter);




        // Capture button clicks
        turnEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                if(expertise!=-1){

                    if (all){

                        Intent myIntent = new Intent(CategoryturnActivity.this,ListdoctorallActivity.class);
                        CategoryturnActivity.this.startActivity(myIntent);

                    }
                    else{
                        Intent myIntent = new Intent(CategoryturnActivity.this,ListdoctorsActivity.class);
                        myIntent.putExtra("expertise", ""+expertise);
                        myIntent.putExtra("part",""+part);
                        CategoryturnActivity.this.startActivity(myIntent);
                    }

                }
                else{
                    Toast.makeText(CategoryturnActivity.this,"لطفا تخصص را انتخاب کنید باتشکر",
                        Toast.LENGTH_SHORT).show();
                }

            }
        });



        sharedpreferences = getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);

        Code = sharedpreferences.getString(G.code, "");
        new sendhttp(G.FetchlistCity+"?code="+Code,"aaa",Code).execute();


        CitySpinner = (Spinner) findViewById(R.id.city);
        OstanSpinner = (Spinner) findViewById(R.id.ostan);
        ShahrestanSpinner = (Spinner) findViewById(R.id.shahrestan);
        PartSpinner = (Spinner) findViewById(R.id.part);
        DoctorSpinner = (Spinner) findViewById(R.id.expertise);




        CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {



                try {

                    StringAndIntegers SI=listcityname.get((int)id);
                    new sendhttp(G.FetchlistOstan+"?city="+SI.getInteger(),"aaa",Code).execute();


                } catch (IndexOutOfBoundsException e) {

                    Log.i("Websocket", ""+listcityname.size());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




       OstanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {



                try {

                    StringAndIntegers SI=listostan.get((int)id);
                    new sendhttp(G.FetchlistShahrestan+"?ostan="+SI.getInteger(),"aaa",Code).execute();


                } catch (IndexOutOfBoundsException e) {

                    Log.i("Websocket", ""+listcityname.size());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });






        ShahrestanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                try {

                    StringAndIntegers SI=listshahrestan.get((int)id);
                    new sendhttp(G.FetchlistPart+"?shahrestan="+SI.getInteger()+"&code="+Code,"aaa",Code).execute();

                } catch (IndexOutOfBoundsException e) {

                    Log.i("Websocket", ""+listcityname.size());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });












        PartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                try {

                    StringAndIntegers SI=listpart.get((int)id);
                    part=(int)SI.getInteger();
                    new sendhttp(G.FetchListExpertise+"?code="+Code,"aaa",Code).execute();


                } catch (IndexOutOfBoundsException e) {

                    Log.i("Websocket", ""+listcityname.size());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        DoctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                try {

                    StringAndIntegers SI=listexpertise.get((int)id);
                    expertise=SI.getInteger();


                } catch (IndexOutOfBoundsException e) {

                    Log.i("Websocket", ""+listcityname.size());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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




            String message[]=this.text.split("~");


            List<String> list = new ArrayList<String>();


            Log.i("Websocket", this.text );


            if(message[0].equals("city")){

                try {

                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");


                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");


                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("ps")){


                           name = c.getString("name_p");


                        }


                        StringAndIntegers SI=new StringAndIntegers(name,Integer.parseInt(id));
                        listcityname.add(SI);
                        list.add(name);

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    CitySpinner.setAdapter(dataAdapter);


                }
                catch (Exception e){

                    if(message[1].equals("0")){

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        CitySpinner.setAdapter(dataAdapter);
                    }
                }
            }





            if(message[0].equals("ostan")){

//                Toast.makeText(CategoryturnActivity.this, String.valueOf(CitySpinner.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
                listostan.clear();
                try {

                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");


                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");

                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("ps")){


                            name = c.getString("name_p");


                        }

                        StringAndIntegers SI=new StringAndIntegers(name,Integer.parseInt(id));
                        listostan.add(SI);
                        list.add(name);

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    OstanSpinner.setAdapter(dataAdapter);


                }
                catch (Exception e){

                    if(message[1].equals("0")){

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        OstanSpinner.setAdapter(dataAdapter);
                    }

                }
            }








            if(message[0].equals("shahrestan")){

                listshahrestan.clear();

//                Toast.makeText(CategoryturnActivity.this, String.valueOf(CitySpinner.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
                try {

                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");


                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        StringAndIntegers SI=new StringAndIntegers(name,Integer.parseInt(id));
                        listshahrestan.add(SI);
                        list.add(name);

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ShahrestanSpinner.setAdapter(dataAdapter);


                }
                catch (Exception e){

                    if(message[1].equals("0")){

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ShahrestanSpinner.setAdapter(dataAdapter);
                    }

                }
            }













            if(message[0].equals("part")){

                listpart.clear();

                try {

                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");


                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");


                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("ps")){


                            name = c.getString("name_p");


                        }


                        StringAndIntegers SI=new StringAndIntegers(name,Integer.parseInt(id));
                        listpart.add(SI);
                        list.add(name);

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    PartSpinner.setAdapter(dataAdapter);


                }
                catch (Exception e){

                    if(message[1].equals("0")){

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        PartSpinner.setAdapter(dataAdapter);
                    }

                }
            }







            if(message[0].equals("expertise")){

                try {
                    listexpertise.clear();

                    JSONArray contacts;
                    JSONObject jsonObj = new JSONObject(message[1]);
                    contacts = jsonObj.getJSONArray("contacts");

                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");

                        SharedPreferences sharedpreferences = G.activity.getSharedPreferences(G.MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedpreferences.getString(G.lang ,null);
                        if(lang.equals("ps")){


                            name = c.getString("name_p");


                        }


                        StringAndIntegers SI=new StringAndIntegers(name,Integer.parseInt(id));
                        listexpertise.add(SI);
                        list.add(name);

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    DoctorSpinner.setAdapter(dataAdapter);


                }
                catch (Exception e){

                    if(message[1].equals("0")){
                        expertise=-1;
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CategoryturnActivity.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        DoctorSpinner.setAdapter(dataAdapter);
                    }


                }
            }













        }

        @Override
        protected void onPreExecute() {
//
//            progressDialog = ProgressDialog.show(CategoryturnActivity.this,
//                    "لطفاً منتظر بمانید",
//                    "با تشکر");
        }

        @Override
        protected String doInBackground(String... params) {



            try {

                URL url = new URL(this.urlstring);


                Log.i("rrrrrrrrrrr", "uuuuuuuuuuuuuuuuu"+url.toString());
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
