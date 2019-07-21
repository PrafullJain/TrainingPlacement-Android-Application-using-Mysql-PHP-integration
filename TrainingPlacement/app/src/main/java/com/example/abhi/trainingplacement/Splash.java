package com.example.abhi.trainingplacement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView icon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icon1=(ImageView)findViewById(R.id.icon1);
        Animation rotate = AnimationUtils.loadAnimation(Splash.this, R.anim.fadeout);
        icon1.startAnimation(rotate);
       /* SharedPreferences sp = getSharedPreferences("logindata",0);
        final String n1 = sp.getString("emailid","");
        final String p1=sp.getString("password","");*/
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                 //if(!n1.isEmpty() && !p1.isEmpty()) {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    finish();

            }
        },2000);

    }
}


