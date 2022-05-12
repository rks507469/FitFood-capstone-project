package ahmux.nutritionpoint;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Handler splashHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashHandler.postDelayed(splashRunnable, 2000);
    }

    //Create Runnable object r
    Runnable splashRunnable = new Runnable() {
        @Override
        public void run() {
            try{
                //start NavActivity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                //close SplashActivity
                finish();
            }catch (Exception e){}
        }
    };
}
