package com.example.myworldclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZoneId;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {


    private int mInterval = 1000;
    private Handler mHandler;

    Button rightbutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DateFormat df = new SimpleDateFormat("hh:mm a");

        //Sydney Clock
        ConstraintLayout sydClock = findViewById(R.id.clockAEST);
        TextView sydTime = sydClock.findViewById(R.id.time);
        ImageView sydImg = sydClock.findViewById(R.id.photog);
        sydImg.setImageResource(R.drawable.sydney);
        TextView syd = sydClock.findViewById(R.id.cityname);
        syd.setText("Sydney");
        TimeZone sydneyTZ = TimeZone.getTimeZone("Australia/Sydney");
        java.util.Calendar sydneyCal = java.util.Calendar.getInstance(sydneyTZ);
        df.setCalendar(sydneyCal);
        df.setTimeZone(sydneyTZ);
        String st = df.format(sydneyCal.getTime());
        sydTime.setText(st);
//        syd.setImageResource();


        //Shanghai clock
        ConstraintLayout shangClock = findViewById(R.id.clockShanghai);
        TextView shanghaiTime = shangClock.findViewById(R.id.time);
        ImageView shangImg = shangClock.findViewById(R.id.photog);
        shangImg.setImageResource(R.drawable.shanghai);
        TextView shang = shangClock.findViewById(R.id.cityname);
        shang.setText("Shanghai");
        TimeZone shangTZ = TimeZone.getTimeZone("Asia/Shanghai");
        java.util.Calendar shangCal = java.util.Calendar.getInstance(shangTZ);
        df.setCalendar(shangCal);
        df.setTimeZone(shangTZ);
        String sht = df.format(shangCal.getTime());
        shanghaiTime.setText(sht);

        //San Sebastian Clock
        ConstraintLayout ssbClock = findViewById(R.id.clockSanSebastian);
        TextView sanSebtime = ssbClock.findViewById(R.id.time);
        ImageView ssbImg = ssbClock.findViewById(R.id.photog);
        ssbImg.setImageResource(R.drawable.sansebastianoverview);
        TextView ssb = ssbClock.findViewById(R.id.cityname);
        ssb.setText("San Sebastian");
        TimeZone ssbTZ = TimeZone.getTimeZone("Europe/Madrid");
        java.util.Calendar ssbCal = java.util.Calendar.getInstance(ssbTZ);
        df.setCalendar(ssbCal);
        df.setTimeZone(ssbTZ);
        String ssbt = df.format(ssbCal.getTime());
        sanSebtime.setText(ssbt);


        //New York Clock
        ConstraintLayout nyClock = findViewById(R.id.clockNewYork);
        TextView nyTime = nyClock.findViewById(R.id.time);
        ImageView nyImg = nyClock.findViewById(R.id.photog);
        nyImg.setImageResource(R.drawable.newyork);
        TextView ny = nyClock.findViewById(R.id.cityname);
        ny.setText("New York");
        TimeZone nyTZ = TimeZone.getTimeZone("America/New_York");
        java.util.Calendar nyCal = java.util.Calendar.getInstance(nyTZ);
        df.setCalendar(nyCal);
        df.setTimeZone(nyTZ);
        String nyt = df.format(nyCal.getTime());
        nyTime.setText(nyt);

        // LA Clock
        ConstraintLayout laClock = findViewById(R.id.clockLA);
        TextView laTime = laClock.findViewById(R.id.time);
        ImageView laImg = laClock.findViewById(R.id.photog);
        laImg.setImageResource(R.drawable.losangeles);
        TextView la = laClock.findViewById(R.id.cityname);
        la.setText("Los Angeles");
        TimeZone laTZ = TimeZone.getTimeZone("America/Los_Angeles");
        java.util.Calendar laCal = java.util.Calendar.getInstance(laTZ);
        df.setCalendar(laCal);
        df.setTimeZone(laTZ);
        String lat = df.format(laCal.getTime());
        laTime.setText(lat);

        //Paris Clock
        ConstraintLayout parisClock = findViewById(R.id.clockParis);
        TextView parisTime = parisClock.findViewById(R.id.time);
        ImageView paImg = parisClock.findViewById(R.id.photog);
        paImg.setImageResource(R.drawable.paris);
        TextView paris = parisClock.findViewById(R.id.cityname);
        paris.setText("Paris");
        TimeZone parisTZ = TimeZone.getTimeZone("Europe/Paris");
        java.util.Calendar parisCal = java.util.Calendar.getInstance(parisTZ);
        df.setCalendar(parisCal);
        df.setTimeZone(parisTZ);
        String pt = df.format(parisCal.getTime());
        parisTime.setText(pt);

        //PerthClock
        ConstraintLayout perthClock = findViewById(R.id.clockPerth);
        TextView perthTime = perthClock.findViewById(R.id.time);
        ImageView perthImg = perthClock.findViewById(R.id.photog);
        perthImg.setImageResource(R.drawable.perth);
        TextView perth = perthClock.findViewById(R.id.cityname);
        perth.setText("Perth");
        TimeZone perthTZ = TimeZone.getTimeZone("Australia/Perth");
        java.util.Calendar perthCal = java.util.Calendar.getInstance(perthTZ);
        df.setCalendar(perthCal);
        df.setTimeZone(perthTZ);
        String pet = df.format(perthCal.getTime());
        perthTime.setText(pet);

        mHandler = new Handler();
        startRepeatingTask();


        rightbutt = findViewById(R.id.twentyFour);
        rightbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchClocks();
            }
        });
    }


    private void switchClocks() {
        Intent intent = new Intent(MainActivity.this, TFHclock.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus();
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }

        private void updateStatus() {
            System.out.println(mInterval);
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }


}




