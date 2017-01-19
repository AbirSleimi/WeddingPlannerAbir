package com.sleimi.abir.weddingplanner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class NewAccountActivity extends AppCompatActivity {
/*
    Created by Abir Sleimi
    Git AbirSleimi
     */

    private AutoCompleteTextView TF_email;
    private EditText TF_password1;
    private EditText TF_password2;
    private EditText TF_nom;
    private EditText TF_prenom;
    private EditText TF_cin;
    private EditText TF_tel;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    private RadioGroup radioEtatGroup;
    private RadioButton radioEtatButton;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String URL_UPLOAD = "http://www.enicarthage-robots.com/prj_android/creation_cpt.php";
    //private static final String URL_UPLOAD = "http://196.224.13.156/atomehaffeli/creation_cpt.php";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        TF_email = (AutoCompleteTextView) findViewById(R.id.TF_email);
        TF_password1 = (EditText) findViewById(R.id.TF_password1);
        TF_password2 = (EditText) findViewById(R.id.TF_password2);
        TF_nom = (EditText) findViewById(R.id.TF_nom);
        TF_prenom = (EditText) findViewById(R.id.TF_prenom);
        TF_cin = (EditText) findViewById(R.id.TF_cin);
        TF_tel = (EditText) findViewById(R.id.TF_tel);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        radioEtatGroup = (RadioGroup) findViewById(R.id.radioetat);

        Button cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }/*
                EditText a = (EditText) findViewById(R.id.TF_username);
                String str = a.getText().toString();
                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG).show();
                Intent i = new Intent(LoginActivity.this, SecondActivity.class);
                startActivity(i);*/

        });

        Button register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(TF_email.getText().toString()) || TextUtils.isEmpty(TF_password1.getText().toString()) || TextUtils.isEmpty(TF_password2.getText().toString())
                        || TextUtils.isEmpty(TF_nom.getText().toString()) || TextUtils.isEmpty(TF_prenom.getText().toString()) || TextUtils.isEmpty(TF_tel.getText().toString()) || TextUtils.isEmpty(TF_cin.getText().toString())) {
                    if (TextUtils.isEmpty(TF_email.getText().toString())) {
                        TF_email.setError(getString(R.string.error_txt));/*
                        TextInputLayout TF_emaile = (TextInputLayout) findViewById(R.id.emailWrapper);
                        TF_emaile.setErrorEnabled(true);
                        TF_emaile.setError("You need to enter a name");*/
                        //TF_email.setError(Html.fromHtml("<font color='green' background-color='green' >error</font>"));
/*
                        int ecolor = R.color.colorAccent; // whatever color you want
                        String estring = "Input is incorrect";
                        ForegroundColorSpan fgcspan = new ForegroundColorSpan(getResources().getColor(ecolor));
                        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
                        ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
                        TF_email.setError(ssbuilder);
*/
                        focusView = TF_email;
                        cancel = true;
                    }
                    else if (TextUtils.isEmpty(TF_password1.getText().toString())){
                        TF_password1.setError(getString(R.string.error_txt));
                    focusView = TF_password1;
                    cancel = true;}
                    else if (TextUtils.isEmpty(TF_password2.getText().toString())){
                        TF_password2.setError(getString(R.string.error_txt));
                    focusView = TF_password2;
                    cancel = true;}
                    else if (TextUtils.isEmpty(TF_nom.getText().toString())){
                        TF_nom.setError(getString(R.string.error_txt));
                    focusView = TF_nom;
                    cancel = true;}
                    else if (TextUtils.isEmpty(TF_prenom.getText().toString())){
                        TF_prenom.setError(getString(R.string.error_txt));
                    focusView = TF_prenom;
                    cancel = true;
                    }
                    else if (TextUtils.isEmpty(TF_tel.getText().toString())){
                        TF_tel.setError(getString(R.string.error_txt));
                        focusView = TF_tel;
                        cancel = true;}
                    else if (TextUtils.isEmpty(TF_cin.getText().toString())){
                        TF_cin.setError(getString(R.string.error_txt));
                        focusView = TF_cin;
                        cancel = true;}

                }if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    String t0=TF_email.getText().toString();
                    String t1=TF_password1.getText().toString();
                    String t2=TF_password2.getText().toString();
                    String t3=TF_nom.getText().toString();
                    String t4=TF_prenom.getText().toString();
                    String t5=TF_tel.getText().toString();
                    String t8=TF_cin.getText().toString();
                    if (!(t1.equals(t2))){
                        Toast.makeText(getApplicationContext(), "Les mots de passe entrés ne sont pas identiques !"/*+t1+t2*/, Toast.LENGTH_LONG).show();
                    }else
                    {
                        try {
                            // get selected radio button from radioGroup
                            int selectedIdS = radioSexGroup.getCheckedRadioButtonId();
                            // find the radiobutton by returned id
                            radioSexButton = (RadioButton) findViewById(selectedIdS);
                            String t6=radioSexButton.getText().toString();
                            //Toast.makeText(NewAccountActivity.this, t6 /*radioSexButton.getText()*/, Toast.LENGTH_SHORT).show();

                            // get selected radio button from radioGroup
                            int selectedIdE = radioEtatGroup.getCheckedRadioButtonId();
                            // find the radiobutton by returned id
                            radioEtatButton = (RadioButton) findViewById(selectedIdE);
                            String t7=radioEtatButton.getText().toString();

                            //Toast.makeText(NewAccountActivity.this, radioEtatButton.getText(), Toast.LENGTH_SHORT).show();


                            upload(t0,t1,t2,t3,t4,t5,t6,t7,t8);
                        } catch (Exception e) {
                            Toast.makeText(NewAccountActivity.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }


    public void upload(final String t0 , final String t1, final String t2, final String t3, final String t4, final String t5, final String t6,final String t7,final String t8) throws Exception {

        new AsyncTask<Void, Integer, String>() {


            @Override
            protected String doInBackground(Void... voids) {
                RequestBody requestBody;

                requestBody = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addFormDataPart("email", t0)
                        .addFormDataPart("passwd", t1)
                        .addFormDataPart("nom", t3)
                        .addFormDataPart("prenom", t4)
                        .addFormDataPart("tel", t5)
                        .addFormDataPart("sex", t6)
                        .addFormDataPart("etat_civil", t7)
                        .addFormDataPart("cin", t8)
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
                    Toast.makeText(NewAccountActivity.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
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

                        Toast.makeText(getApplicationContext(), "Vous etes inscrit maintenant !", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(NewAccountActivity.this, MainActivity.class);
                        startActivity(i);

                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"erreur !", Toast.LENGTH_LONG).show();}

                    // On affiche le message à l'utilisateur
                    //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    Toast.makeText(NewAccountActivity.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


        }.execute();


    }
    public boolean validateMail(String mail) {
        return mail.length() > 6;
    }

    public boolean validatePassword(String password) {
        return password.length() > 4;
    }
    public boolean validateCin(String cin) {
        return cin.length() == 8;
    }
}
