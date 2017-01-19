package com.sleimi.abir.weddingplanner;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
/*
    Created by Abir Sleimi
    Git AbirSleimi
     */

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {/*
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {/*
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {/*
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition) + groupPosition+
                                childPosition , Toast.LENGTH_SHORT)
                        .show();*/
                if (groupPosition == 0) {
                    ////Toast.makeText(getApplicationContext(), " 1", Toast.LENGTH_SHORT).show();

                    if (childPosition == 0){//Toast.makeText(getApplicationContext(), " 1", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SecondActivity.this, SplashScreenCostumes.class);
                        startActivity(i);}
                    if (childPosition == 1){
                        //Toast.makeText(getApplicationContext(), " 2", Toast.LENGTH_SHORT).show();}
                        Intent i = new Intent(SecondActivity.this, CoiffureH.class);
                        startActivity(i);
                }
                }
                if (groupPosition == 1) { //Toast.makeText(getApplicationContext(), " 2", Toast.LENGTH_SHORT).show();
                    if (childPosition == 0){
                        Intent i = new Intent(SecondActivity.this, SplashScreenRobe.class);
                        startActivity(i);}
                    if (childPosition == 1){
                        Intent i = new Intent(SecondActivity.this, SplashScreenCoiff.class);
                        startActivity(i);
                    }
                    if (childPosition == 2){}

                }
                if (groupPosition == 2) {
                    //Toast.makeText(getApplicationContext(), " 3", Toast.LENGTH_SHORT).show();
                    if (childPosition == 0){}
                    if (childPosition == 1){}
                    if (childPosition == 2){}
                    if (childPosition == 3){}
                    if (childPosition == 4){}
                    if (childPosition == 5){}
                    if (childPosition == 6){}
                    if (childPosition == 7){}
                    if (childPosition == 8){}
                    if (childPosition == 9){
                        Intent i = new Intent(SecondActivity.this, Jus.class);
                        startActivity(i);

                    }

                }
                if (groupPosition == 3) {
                    //Toast.makeText(getApplicationContext(), " 4", Toast.LENGTH_SHORT).show();
                    if (childPosition == 0){
                        Intent i = new Intent(SecondActivity.this, SplashScreenVoy.class);
                        startActivity(i);
                    }
                    if (childPosition == 1){}
                }
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Homme");
        listDataHeader.add("Femme");
        listDataHeader.add("Le jour J");
        listDataHeader.add("Mois du miel");


        // Adding child data
        List<String> Homme = new ArrayList<String>();
        Homme.add("Costumes");
        Homme.add("Coiffure");

        List<String> Femme = new ArrayList<String>();
        Femme.add("Robes");
        Femme.add("Coiffure");
        Femme.add("Massage");

        List<String> Le_jour_J = new ArrayList<String>();
        Le_jour_J.add("Fleurs");
        Le_jour_J.add("Voitures");
        Le_jour_J.add("Photographes");
        Le_jour_J.add("Voitures");
        Le_jour_J.add("Salles");
        Le_jour_J.add("Groupes musicals");
        Le_jour_J.add("Tables");
        Le_jour_J.add("Salés");
        Le_jour_J.add("Baklawa");
        Le_jour_J.add("Jus");

        List<String> Mois_du_miel = new ArrayList<String>();
        Mois_du_miel.add("Voyages de noce à l'etranger");
        Mois_du_miel.add("Voyages de noce en Tunisie");

        listDataChild.put(listDataHeader.get(0), Homme); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Femme);
        listDataChild.put(listDataHeader.get(2), Le_jour_J);
        listDataChild.put(listDataHeader.get(3), Mois_du_miel);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.chat) {
            Intent i = new Intent(SecondActivity.this, Chat.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.logout) {
            AlertDialog.Builder a_builder=new AlertDialog.Builder(this);
            a_builder.setMessage("Vous voulez déconnecter ?")
                    .setCancelable(false)
                    .setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i = new Intent(SecondActivity.this, MainActivity.class);
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

        if (id == R.id.chat) {
            Intent i = new Intent(SecondActivity.this, Chat.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.help) {
            Intent i = new Intent(SecondActivity.this, Info.class);
            startActivity(i);
            return true;
        }


        if (id == R.id.nav_profil) {
             Intent i = new Intent(SecondActivity.this, ModifierProfil.class);
             startActivity(i);
            return true;
        }
        if (id == R.id.nav_send) {
             Intent i = new Intent(SecondActivity.this, EnvoyerMail.class);
             startActivity(i);
            return true;
        }
        if (id == R.id.nav_invit) {
            Intent i = new Intent(SecondActivity.this, InviterAmi.class);
            startActivity(i);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}