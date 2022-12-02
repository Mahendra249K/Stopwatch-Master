package com.mahendra249k.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private TextView text1,text2;
    private Button btnget;
    Animation atg,anim1,anim2,anim3;
    ImageView photo;
    private Toast backToast;
    private long backPressTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        btnget=findViewById(R.id.btnstart);
        photo=findViewById(R.id.photo);
        atg= AnimationUtils.loadAnimation(this,R.anim.atg);
        anim1= AnimationUtils.loadAnimation(this,R.anim.anim1);
        anim2= AnimationUtils.loadAnimation(this,R.anim.anim2);
        anim3= AnimationUtils.loadAnimation(this,R.anim.anim3);


        photo.startAnimation(atg);
        text1.startAnimation(anim1);
        text2.startAnimation(anim2);
        btnget.startAnimation(anim3);


        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });


    }

    public void onBackPressed() {

        if(backPressTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
            backToast= Toast.makeText(getBaseContext(),"Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressTime=System.currentTimeMillis();
    }
}