package ahmux.nutritionpoint;

/* ######################################## */
/*  Fit Food  */
/* ##### rks507469@gmail.com ######  */
/* ######################################## */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RemindersActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2, b3, b4, b5,b6,b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.reminders_title);

        // Creating a notification channel on oreo and above
        createNotificationChannel();

        SharedPreferences sharedPreferences = getSharedPreferences("SettingsData", Activity.MODE_PRIVATE);

        b1 = (Button)findViewById(R.id.min15Btn);
        b1.setOnClickListener(this);

        b2 = (Button)findViewById(R.id.min30Btn);
        b2.setOnClickListener(this);

        b3 = (Button)findViewById(R.id.h1Btn);
        b3.setOnClickListener(this);

        b4 = (Button)findViewById(R.id.h2Btn);
        b4.setOnClickListener(this);

        b5 = (Button)findViewById(R.id.h4Btn);
        b5.setOnClickListener(this);

        b6 = (Button)findViewById(R.id.startBtn);
        b6.setOnClickListener(this);

        b7 = (Button)findViewById(R.id.stopBtn);
        b7.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("SettingsData", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        long delay = sharedPreferences.getInt("water_delay", 300);


        if (view.getId() == R.id.min15Btn || delay == 15){
            editor.putInt("water_delay", 15);
            b1.setTextColor(Color.parseColor("#FFCC00"));
            b2.setTextColor(Color.WHITE);
            b3.setTextColor(Color.WHITE);
            b4.setTextColor(Color.WHITE);
            b5.setTextColor(Color.WHITE);
        }
        else if (view.getId() == R.id.min30Btn || delay == 30){
            editor.putInt("water_delay", 30);
            b1.setTextColor(Color.WHITE);
            b2.setTextColor(Color.parseColor("#FFCC00"));
            b3.setTextColor(Color.WHITE);
            b4.setTextColor(Color.WHITE);
            b5.setTextColor(Color.WHITE);
        }
        else if (view.getId() == R.id.h1Btn || delay == 60){
            editor.putInt("water_delay", 60);
            b1.setTextColor(Color.WHITE);
            b2.setTextColor(Color.WHITE);
            b3.setTextColor(Color.parseColor("#FFCC00"));
            b4.setTextColor(Color.WHITE);
            b5.setTextColor(Color.WHITE);
        }
        else if (view.getId() == R.id.h2Btn || delay == 120){
            editor.putInt("water_delay", 120);
            b1.setTextColor(Color.WHITE);
            b2.setTextColor(Color.WHITE);
            b3.setTextColor(Color.WHITE);
            b4.setTextColor(Color.parseColor("#FFCC00"));
            b5.setTextColor(Color.WHITE);
        }
        else if (view.getId() == R.id.h4Btn || delay == 240){
            editor.putInt("water_delay", 240);
            b1.setTextColor(Color.WHITE);
            b2.setTextColor(Color.WHITE);
            b3.setTextColor(Color.WHITE);
            b4.setTextColor(Color.parseColor("#FFCC00"));
            b5.setTextColor(Color.WHITE);
        }
        else if (view.getId() == R.id.startBtn){
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3*60*1000, pendingIntent);
            editor.putString("water_reminder", "true");
            Toast.makeText(this, "Water Reminder Started", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId() == R.id.stopBtn){
            editor.putString("water_reminder", "false");
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this, "Water Reminders Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FitFoodWaterReminderChannel";
            String description = "Channel for Water Reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("FitFood", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
