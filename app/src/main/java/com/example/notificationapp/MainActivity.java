package com.example.notificationapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker timePicker;
    PendingIntent pendingIntent;
    Calendar calendar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.time_picker);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        intent = new Intent(MainActivity.this,AlarmReceiver.class);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Hii(View view) {

            calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
            calendar.set(Calendar.MINUTE,timePicker.getMinute());


            int requestCode = new Random().nextInt(100001) + 1;

            intent.putExtra("requestCode",requestCode);

            Log.e("----------","" + requestCode);


            pendingIntent = PendingIntent.getBroadcast(MainActivity.this,requestCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);


    }
}
