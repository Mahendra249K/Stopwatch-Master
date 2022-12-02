package com.mahendra249k.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private ImageView circle, pin;
    private TextView laps;
    private Button btnstart, btnstop;
    private Animation roundanim, roundanim2;
    private Chronometer timerhere;
    Vibrator vibrator;
    Animation atg, anim4;
    private long backPressTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        circle = findViewById(R.id.circle);
        pin = findViewById(R.id.pin);
        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        timerhere = findViewById(R.id.timerhere);
        laps = findViewById(R.id.leps);
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        anim4 = AnimationUtils.loadAnimation(this, R.anim.anim4);
        vibrator = (Vibrator) getSystemService(HomeActivity.VIBRATOR_SERVICE);
        roundanim = AnimationUtils.loadAnimation(this, R.anim.roundanim);
        roundanim2 = AnimationUtils.loadAnimation(this, R.anim.roundanim2);
        circle.startAnimation(roundanim2);
        btnstop.setAlpha(0);
        btnstop.setEnabled(false);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin.startAnimation(roundanim);
                vibrator.vibrate(60);
                circle.clearAnimation();

                //set Button
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();

                timerhere.setBase(SystemClock.elapsedRealtime());
                timerhere.start();
                btnstart.setEnabled(false);
                btnstop.setEnabled(true);
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin.clearAnimation();
                circle.startAnimation(roundanim2);
                vibrator.vibrate(400);
                timerhere.stop();

                btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstop.animate().alpha(0).setDuration(300).start();

                btnstart.setEnabled(true);
                btnstop.setEnabled(false);

                String lap = timerhere.getText().toString();
                laps.startAnimation(anim4);
                laps.setText("Last lap " + lap);
            }
        });

    }


    @Override
    public void onBackPressed() {

        if (backPressTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
}