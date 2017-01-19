package com.sleimi.abir.weddingplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.sleimi.abir.weddingplanner.MainActivity.userlogin;

public class ModifierMail extends AppCompatActivity {
    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */
//act new_adr re_new_adr pwd
    EditText act,new_adr,re_new_adr,pwd ;
    Button bprofil;
    String tact,tpwd,tnew_adr,tre_new_adr;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String URL_UPLOAD = "http://www.enicarthage-robots.com/prj_android/modifier_email_cpt.php";
    //private static final String URL_UPLOAD = "http://196.224.13.156/atomehaffeli/modifier_email_cpt.php";
    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        act = (EditText) findViewById(R.id.act);
        new_adr = (EditText) findViewById(R.id.new_adr);
        pwd = (EditText) findViewById(R.id.pwd);
        re_new_adr = (EditText) findViewById(R.id.re_new_adr);
        bprofil = (Button) findViewById(R.id.bprofil);
        bprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cancel = false;
                View focusView = null;
                //ttel,tetat_civil;

                tact = act.getText().toString();
                tpwd = pwd.getText().toString();
                tnew_adr = new_adr.getText().toString();
                tre_new_adr = re_new_adr.getText().toString();
                if (TextUtils.isEmpty(tact) || TextUtils.isEmpty(tpwd)|| TextUtils.isEmpty(tnew_adr)|| TextUtils.isEmpty(tre_new_adr)){
                    if (TextUtils.isEmpty(tact)) {
                        act.setError(getString(R.string.error_txt));
                        focusView = act;
                        cancel = true;
                    }
					else if (TextUtils.isEmpty(tnew_adr)) {
                        new_adr.setError(getString(R.string.error_txt));
                        focusView = new_adr;
                        cancel = true;
                    }
					else if (TextUtils.isEmpty(tre_new_adr)) {
                        re_new_adr.setError(getString(R.string.error_txt));
                        focusView = re_new_adr;
                        cancel = true;
                    }
					else if (TextUtils.isEmpty(tpwd)) {
                        pwd.setError(getString(R.string.error_txt));
                        focusView = pwd;
                        cancel = true;
                    }
                }
                    else if (!tact.equals(userlogin)) {
                        act.setError("Ce n'est pas votre adresse actuelle! SVP entrez votre adresse!");
                        focusView = act;
                        cancel = true;
                    }
                else if (!tpwd.equals(MainActivity.userpassword)) {
                    pwd.setError("Ce n'est pas votre mot de passe actuel! SVP entrez votre mot de passe!");
                    focusView = pwd;
                    cancel = true;
                }
                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {

                    if (tact.equals(tnew_adr)){
                        Toast.makeText(getApplicationContext(), "La nouvelle adresse entrée est identique à ladresse actuelle !", Toast.LENGTH_LONG).show();
                    }else if (!tre_new_adr.equals(tnew_adr)){
                        Toast.makeText(getApplicationContext(), "Les adresses entrées ne sont pas identiques !"/*+t1+t2*/, Toast.LENGTH_LONG).show();
                    }else
                    {
                    try {
                    upload(tact, tpwd,tnew_adr);
                } catch (Exception e) {
                    Toast.makeText(ModifierMail.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                    }
                }

            }

        });

    }
    public void upload(final String tact , final String tpwd, final String tnew_adr) throws Exception {

        new AsyncTask<Void, Integer, String>() {


            @Override
            protected String doInBackground(Void... voids) {
                RequestBody requestBody;

                requestBody = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addFormDataPart("email", MainActivity.userlogin)
                        .addFormDataPart("password", MainActivity.userpassword)
                        .addFormDataPart("new_email", tnew_adr)
                        .build();

                Request request = new Request.Builder()
                        .url(URL_UPLOAD)
                        .post(requestBody)
                        .build();



                Response response = null;

                try {
                    // On exécute la requête
                    response = client.newCall(request).execute();

                    String responseStr = response.body().string();

                    return responseStr;


                } catch (IOException e) {
                    Toast.makeText(ModifierMail.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                return  null;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);

                    int success = Integer.valueOf(jsonObject.getString(TAG_SUCCESS));
                    String message = jsonObject.getString(TAG_MESSAGE);
                    //ticket = jsonObject.getString("ticket");

                    // Si c'est 1 donc l'upload s'est bien faite
                    if (success == 1) {

                        Toast.makeText(getApplicationContext(), "Vous avez modifié votre adresse maintenant !", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ModifierMail.this, MainActivity.class);
                        startActivity(i);

                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"erreur !", Toast.LENGTH_LONG).show();}

                    // On affiche le message à l'utilisateur
                    //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    Toast.makeText(ModifierMail.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


        }.execute();


    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.logout) {
            AlertDialog.Builder a_builder=new AlertDialog.Builder(this);
            a_builder.setMessage("Vous voulez déconnecter ?")
                    .setCancelable(false)
                    .setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i = new Intent(ModifierMail.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setPositiveButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
            AlertDialog alert=a_builder.create();
            alert.setTitle("Déconnexion ");
            alert.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
