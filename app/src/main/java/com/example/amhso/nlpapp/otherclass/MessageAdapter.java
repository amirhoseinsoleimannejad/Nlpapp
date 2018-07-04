package com.example.amhso.nlpapp.otherclass;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amhso.nlpapp.G;
import com.example.amhso.nlpapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MessageAdapter extends ArrayAdapter<message> {

    private final Activity context;
    private final List<message>  itemname;

    public MessageAdapter(Activity context, List<message> itemname) {
        super(context, R.layout.listmessage, itemname);
        this.context=context;
        this.itemname=itemname;
    }




    public View getView(int position,View view,ViewGroup parent) {




        View listItem = view;
        MyWrapper wrapper = null;


        try {


            if (listItem == null) {

                listItem = LayoutInflater.from(context).inflate(R.layout.listmessage, parent, false);
                wrapper = new MyWrapper(listItem);
                listItem.setTag(wrapper);

            } else {
                wrapper = (MyWrapper) listItem.getTag();
            }


            Log.i("ttttttttttttttttttttt", "adapter: "+itemname.get(position).getText());




            if (itemname.get(position).getSelf()){

                wrapper.getText_person().setVisibility(View.GONE);
                wrapper.getText().setVisibility(View.VISIBLE);


                wrapper.getProfile().setVisibility(View.GONE);
                wrapper.getSelf_profile().setVisibility(View.VISIBLE);


                String body_message = itemname.get(position).getText();
                String date = itemname.get(position).getDate();
                String time = itemname.get(position).getTime();

                body_message += "\n";
                body_message += date;
                body_message += "\n";
                body_message += time;


                wrapper.getText().setText(body_message);



                ImageView img_profile=wrapper.getSelf_profile();
                Picasso.with(G.activity)
                        .load(G.ServerImg+itemname.get(position).getImg())
                        .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3 )
                        .into(img_profile);
                Log.i("imageimageimage", "11111111: "+G.ServerImg+itemname.get(position).getImg());

            }


            else{


                wrapper.getText().setVisibility(View.GONE);
                wrapper.getText_person().setVisibility(View.VISIBLE);



                wrapper.getProfile().setVisibility(View.VISIBLE);
                wrapper.getSelf_profile().setVisibility(View.GONE);



                String body_message = itemname.get(position).getText();
                String date = itemname.get(position).getDate();
                String time = itemname.get(position).getTime();

                body_message += "\n";
                body_message += date;
                body_message += "\n";
                body_message += time;


                wrapper.getText_person().setText(body_message);




                ImageView img=wrapper.getProfile();
                Picasso.with(G.activity)
                        .load(G.ServerImg+itemname.get(position).getImg())
                        .resize(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels / 3 )
                        .into(img);

                Log.i("imageimageimage", "2222222: "+G.ServerImg+itemname.get(position).getImg());
            }



        }
        catch (Exception e){
            Log.i("eeeeee", "eeeeeeeeeeeeeeee"+e.toString());
        }



        return listItem;


    };







    class MyWrapper
    {
        private View base;
        private TextView message;
        private TextView message_person;
        private ImageView self_profile;
        private ImageView profile;


        public MyWrapper(View base)
        {
            this.base = base;
        }



        public TextView getText(){
            if(message == null){
                message = (TextView) base.findViewById(R.id.text);
            }
            return message;
        }




        public TextView getText_person(){
            if(message_person == null){
                message_person = (TextView) base.findViewById(R.id.text_person);
            }
            return message_person;
        }




        public ImageView getSelf_profile(){
            if(self_profile == null){
                self_profile = (ImageView) base.findViewById(R.id.image_profile_self);

            }
            return self_profile;
        }





        public ImageView getProfile(){
            if(profile == null){
                profile = (ImageView) base.findViewById(R.id.image_profile);
            }
            return profile;
        }


    }
}
