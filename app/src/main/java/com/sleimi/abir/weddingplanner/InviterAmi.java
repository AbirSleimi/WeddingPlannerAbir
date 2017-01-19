package com.sleimi.abir.weddingplanner;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sleimi.abir.weddingplanner.Model.Username;
import com.squareup.okhttp.OkHttpClient;

public class InviterAmi extends AppCompatActivity {
    /*
        Created by Abir Sleimi
        Git AbirSleimi
         */
	//EditText edtMail;
    String temail;
    private final OkHttpClient client = new OkHttpClient();
    GMailSender sender;

    private static final String TAG_SUCCESS = "success";
    private final OkHttpClient client2 = new OkHttpClient();


    private static final String TAG_SUCCESS2 = "success";
	EditText ETmsg;
    //String tmsg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inviter_ami);
		//sender = new GMailSender("le.docteur.du.coeur@gmail.com", "docteure");
        sender = new GMailSender("mail application", "mot de passe");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
                Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        ETmsg = (EditText) findViewById(R.id.ETmsg);
		            ImageButton Msgbtn = (ImageButton) findViewById(R.id.msg_button);//ImageButton
            Msgbtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temail = ETmsg.getText().toString();
					//temail = edtMail.getText().toString();
                String username = new Username().getUsername();
                    if (TextUtils.isEmpty(temail) ) {
                    Toast.makeText(getApplicationContext(), "Saisissez votre message!" , Toast.LENGTH_LONG).show();
                }
                else {
                    try { 
						//client
                        //sender.sendMail("Invitation", "Vous avez envoyé une invitation pour l'aplication Atome Haffeli à " + temail + "" , "le.docteur.du.coeur@gmail.com", MainActivity.userlogin);
                        sender.sendMail("Invitation", "Vous avez envoyé une invitation pour l'aplication Atome Haffeli à " + temail + "" , "mail application", MainActivity.userlogin);
                        if (!MainActivity.userlogin.equals("")) { //invité
                            //sender.sendMail("Invitation","Vous avez reçu une invitation à l'aplication Atome Haffeli de la part de " + MainActivity.userlogin , "le.docteur.du.coeur@gmail.com",temail + "" );
                            sender.sendMail("Invitation","Vous avez reçu une invitation à l'aplication Atome Haffeli de la part de " + MainActivity.userlogin , "mail application",temail + "" );
                        }
                        Toast.makeText(InviterAmi.this, "Invitation envoyée!", Toast.LENGTH_LONG).show();
                        ETmsg.setText("");
                    } catch (Exception e) {
                        Toast.makeText(InviterAmi.this, "Vérifiez votre connexion!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }

            }


        });
        
    }
}
