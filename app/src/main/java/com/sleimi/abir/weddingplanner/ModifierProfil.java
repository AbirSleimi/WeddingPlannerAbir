package com.sleimi.abir.weddingplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/*
    Created by Abir Sleimi
    Git AbirSleimi
     */
public class ModifierProfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	TextView nom,prenom,cin,sex;
    EditText tel,etat_civil;
    Button bprofil;
    String ttel,tetat_civil;

	//private static final String URL_UPLOAD = "http://196.224.18.72/atomeHaffeli/afficher_cpt.php";
    private static final String URL_UPLOAD = "http://www.enicarthage-robots.com/prj_android/afficher_cpt.php";
    private final OkHttpClient client = new OkHttpClient();
    GMailSender sender;
    private static final String TAG_SUCCESS = "success";
    //private static final String URL_UPLOAD2 = "http://196.224.18.72/atomeHaffeli/modifier_cpt.php";
    private static final String URL_UPLOAD2 = "http://www.enicarthage-robots.com/prj_android/modifier_cpt.php";
    private final OkHttpClient client2 = new OkHttpClient();
    private static final String TAG_SUCCESS2 = "success";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
		
		cin = (TextView) findViewById(R.id.cin);
        nom = (TextView) findViewById(R.id.nom);
        prenom = (TextView) findViewById(R.id.prenom);
		sex = (TextView) findViewById(R.id.sex);
        tel = (EditText) findViewById(R.id.tel);
        etat_civil = (EditText) findViewById(R.id.etat_civil);
        bprofil = (Button) findViewById(R.id.bprofil);
        bprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

			//ttel,tetat_civil;
               
                ttel = tel.getText().toString();
                tetat_civil = etat_civil.getText().toString();

                try {
                    upload(ttel, tetat_civil, MainActivity.userlogin, MainActivity.userpassword);
                } catch (Exception e) {
                    Toast.makeText(ModifierProfil.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }

        });

                try {
                    upload(MainActivity.userlogin, MainActivity.userpassword);
                } catch (Exception e) { Toast.makeText(ModifierProfil.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
    }
	public void upload(final String l,final String e,final String code,final String pass) throws Exception {

        new AsyncTask<Void, Integer, String>() {


            @Override
            protected String doInBackground(Void... voids) {
                RequestBody requestBody;
                requestBody = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addFormDataPart("email", code)
                        .addFormDataPart("password",pass)
                        .addFormDataPart("tel",l)
                        .addFormDataPart("etat_civil",e)
                        .build();



                Request request = new Request.Builder()
                        .url(URL_UPLOAD2)
                        .post(requestBody)
                        .build();

                Response response = null;

                try {
                    // On exécute la requête
                    response = client2.newCall(request).execute();

                    String responseStr = response.body().string();

                    return responseStr;


                } catch (IOException e) { Toast.makeText(ModifierProfil.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                return null;

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);

                    int success = Integer.valueOf(jsonObject.getString(TAG_SUCCESS2));
                    if (success == 1)
                    {
                        Toast.makeText(getApplicationContext(), "Votre profil est modifié !", Toast.LENGTH_LONG).show();


                    }
                    else {


                        Toast.makeText(getApplicationContext(), "erreur !", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) { Toast.makeText(ModifierProfil.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


        }.execute();
    }



    public void upload(final String code,final String pass) throws Exception {

        new AsyncTask<Void, Integer, String>() {


            @Override
            protected String doInBackground(Void... voids) {
                RequestBody requestBody;

                requestBody = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addFormDataPart("user", code)
                        .addFormDataPart("password",pass)
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


                } catch (IOException e) { Toast.makeText(ModifierProfil.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
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
                    String chnom = jsonObject.getString("nom");
                    String chprenom = jsonObject.getString("prenom");
                    String chcin = jsonObject.getString("cin");
                    String chsex = jsonObject.getString("sex");
                    String chtel = jsonObject.getString("tel");
                    String chetat_civil = jsonObject.getString("etat_civil");

                    // Si c'est 1 donc l'upload s'est bien faite
                    if (success == 1) {
                        nom.setText(chnom);
                        prenom.setText(chprenom);
                        cin.setText(chcin);
                        sex.setText(chsex);
                        tel.setText(chtel);
                        etat_civil.setText(chetat_civil);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"erreur !", Toast.LENGTH_LONG).show();}

                    // On affiche le message à l'utilisateur

                } catch (JSONException e) { Toast.makeText(ModifierProfil.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


        }.execute();


    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu) {
            getMenuInflater().inflate(R.menu.menu_profile, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.params) {
                AlertDialog.Builder a_builder=new AlertDialog.Builder(this);
                a_builder.setMessage("Vous voulez modifier ?")
                        .setCancelable(false)
                        .setNegativeButton("Adresse email", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(ModifierProfil.this, ModifierMail.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .setPositiveButton("Mot de passe", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(ModifierProfil.this, ModifierPassword.class);
                                startActivity(i);
                                finish();

                            }
                        });
                AlertDialog alert=a_builder.create();
                alert.setTitle("Modifier info compte ");
                alert.show();

                return true;
            }

            if (id == R.id.logout) {
                AlertDialog.Builder a_builder=new AlertDialog.Builder(this);
                a_builder.setMessage("Vous voulez déconnecter ?")
                        .setCancelable(false)
                        .setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(ModifierProfil.this, MainActivity.class);
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

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            int id = item.getItemId();
            android.support.v4.app.FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();

            if (id == R.id.nav_profil) {
                // Intent i = new Intent(Consulter_profile.this, Consulter_profile.class);
                //startActivity(i);
                return true;
            }

            if (id == R.id.nav_send) {
                // Intent i = new Intent(Consulter_profile.this, EnvoyerMail.class);
                // startActivity(i);
                return true;
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    }