package com.sleimi.abir.weddingplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sleimi.abir.weddingplanner.Model.Username;
import com.squareup.okhttp.OkHttpClient;

public class EnvoyerMail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*
    Created by Abir Sleimi
    Git AbirSleimi
     */
    EditText edtMail;
    String temail;
    private final OkHttpClient client = new OkHttpClient();
    GMailSender sender;

    private static final String TAG_SUCCESS = "success";
    private final OkHttpClient client2 = new OkHttpClient();


    private static final String TAG_SUCCESS2 = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.envoyer_mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sender = new GMailSender("mail application", "mot de passe");
        //sender = new GMailSender("le.docteur.du.coeur@gmail.com", "docteure");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
                Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        edtMail = (EditText) findViewById(R.id.edtMail);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Envoie du mail", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                temail = edtMail.getText().toString();
                String username = new Username().getUsername();
                if (TextUtils.isEmpty(temail)) {
                    Toast.makeText(getApplicationContext(), "Saisissez votre message!", Toast.LENGTH_LONG).show();
                } else {

                    try { //Admin
                        //sender.sendMail("e-mail envoyé par " + username, temail + "", "le.docteur.du.coeur@gmail.com", "sleimi.abir3@gmail.com");
                        sender.sendMail("e-mail envoyé par " + username, temail + "", "mail application", "mail admin");
                        //Toast.makeText(getApplicationContext(), "email envoyé !", Toast.LENGTH_LONG).show();
                        if (!MainActivity.userlogin.equals("")) { //client
                            //sender.sendMail("e-mail envoyé à un admin", temail + "", "le.docteur.du.coeur@gmail.com", MainActivity.userlogin);
                            sender.sendMail("e-mail envoyé à un admin", temail + "", "mail application", MainActivity.userlogin);
                        }
                        Toast.makeText(EnvoyerMail.this, "E-mail envoyé!", Toast.LENGTH_LONG).show();
                        edtMail.setText("");
                    } catch (Exception e) {
                        Toast.makeText(EnvoyerMail.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    //Intent i = new Intent(Consulter_profile.this, ModifierProfil.class);
                    //startActivity(i);
                }
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String username = new Username().getUsername();
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

                            Intent i = new Intent(EnvoyerMail.this, MainActivity.class);
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