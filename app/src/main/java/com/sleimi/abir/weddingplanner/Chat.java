package com.sleimi.abir.weddingplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.sleimi.abir.weddingplanner.Model.ListMsgModel;
import com.sleimi.abir.weddingplanner.Model.Username;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat extends AppCompatActivity {
    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */
    EditText ETmsg;
    String tmsg="";
    private ListView lvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        lvMsg = (ListView) findViewById(R.id.lvMsgChat);
        /*
        affichage liste dès la date de la connex du client
         */
        String username = new Username().getUsername();
       //new JSONTask().execute("http://196.224.18.72/atomehaffeli/list_msg.php?id="+username);

        new JSONTask().execute("http://www.enicarthage-robots.com/prj_android/list_msg.php?id="+username);
            ETmsg = (EditText) findViewById(R.id.ETmsg);
            ImageButton Msgbtn = (ImageButton) findViewById(R.id.msg_button);//ImageButton
            Msgbtn.setOnClickListener( new View.OnClickListener() {

                //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Snackbar.make(view, "Send a msg", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                    tmsg = ETmsg.getText().toString();

                    if (TextUtils.isEmpty(tmsg) ) {
                    Toast.makeText(getApplicationContext(), "Saisissez votre message!" , Toast.LENGTH_LONG).show();
                }
                else {
                    Snackbar.make(view, "Envoi du msg", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    tmsg = ETmsg.getText().toString();

                    attemptChat();

                    String username = new Username().getUsername();
                    //new JSONTask().execute("http://196.224.18.72/atomehaffeli/list_msg.php?id=" + username);
                        new JSONTask().execute("http://www.enicarthage-robots.com/prj_android/list_msg.php?id=" + username);
                    ETmsg.setText("");

                }

            }


        });

    }


        private void attemptChat() {
            HashMap postData = new HashMap();
            postData.put("id", MainActivity.userlogin);
            postData.put("id_msg", tmsg);
            PostResponseAsyncTask task = new PostResponseAsyncTask(Chat.this, postData,"Attends SVP...", new AsyncResponse() {
                @Override
                public void processFinish(String result) {

                }
            });
            //task.execute("http://196.224.18.72/atomehaffeli/ajout_msg.php");

            task.execute("http://www.enicarthage-robots.com/prj_android/ajout_msg.php");

    }



    public class JSONTask extends AsyncTask<String,String,List<ListMsgModel>> {

        @Override
        protected List<ListMsgModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);
                StringBuffer buffer = new StringBuffer();
                String line="";
                while((line = reader.readLine())!= null){
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("response");

                List<ListMsgModel> ListMsgModelList = new ArrayList<>();
                for(int i=0; i<parentArray.length(); i++){
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    ListMsgModel ListMsgModel=new ListMsgModel();
                    ListMsgModel.setUsername(finalObject.getString("username"));
                    ListMsgModel.setMsg(finalObject.getString("msg"));
                    ListMsgModel.setD_H_msg(finalObject.getString("D_H_msg"));
                    ListMsgModelList.add(ListMsgModel);
                }

                return ListMsgModelList;

            } catch (MalformedURLException e){ Toast.makeText(Chat.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }catch(IOException e){ Toast.makeText(Chat.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (JSONException e) {Toast.makeText(Chat.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } finally{
                if(connection!=null){
                    connection.disconnect();
                }
                try{
                    if(reader !=null){
                        reader.close();
                    }
                }catch(IOException e){ Toast.makeText(Chat.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ListMsgModel> result) {
            super.onPostExecute(result);
            ListMsgAdapter adapter = new ListMsgAdapter(getApplicationContext(), R.layout.content_row_list_msg,result);
            lvMsg.setAdapter(adapter);/*
            super.onPostExecute(result);
            final ListMsgAdapter adapter = new ListMsgAdapter(getApplicationContext(),R.layout.content_row_list_msg,result);*/
            //lvMsg.setAdapter(adapter);
/*
            final Handler handler = new Handler();
            handler.postDelayed( new Runnable() {

                @Override
                public void run() {


                    adapter.notifyDataSetChanged();
                    handler.postDelayed( this, 2 * 1000 );
                }
            }, 2 * 1000 );*/
            /*
            lvMsg.setAdapter(adapter);
            super.onPostExecute(result);

            lvMsg.setAdapter(adapter);*/

        }

    }


    public class ListMsgAdapter extends ArrayAdapter {

        private List<ListMsgModel> ListMsgModelList;
        private int resource;
        private LayoutInflater inflater;
        public ListMsgAdapter(Context context, int resource, List<ListMsgModel> objects) {
            super(context,resource,objects);
            ListMsgModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView ==null){
                convertView = inflater.inflate(resource,null);

            }

            TextView textUsername_msg;
            TextView textMsg;
            TextView textDate_Msg;

            textUsername_msg = (TextView)convertView.findViewById(R.id.usernameMsg);
            textMsg = (TextView)convertView.findViewById(R.id.textMsg);
            textDate_Msg = (TextView)convertView.findViewById(R.id.DateMsg);

            textUsername_msg.setText(ListMsgModelList.get(position).getUsername());
            textMsg.setText(ListMsgModelList.get(position).getMsg());
            textDate_Msg.setText(ListMsgModelList.get(position).getD_H_msg());


            return convertView;
        }

    }

}
