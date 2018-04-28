package com.androindian.raj.roommanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Spalsh extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    String UserMobile,UserPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //cd
                SharedPreferences preferences=
                        getSharedPreferences("Room",MODE_PRIVATE);
                UserMobile=preferences.getString("Mobile","");
                UserPass=preferences.getString("Password","");

                if(UserPass.equals("") && UserMobile.equals("")){
                    Intent i = new Intent(Spalsh.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(Spalsh.this, RoomMenu.class);
                    startActivity(i);
                    finish();
                }




        }
    }, SPLASH_TIME_OUT);
}
}
