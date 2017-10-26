package pe.edu.utp.corpgenprojectburningencounters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import pe.edu.utp.corpgenprojectburningencounters.R;

public class SplashActivity extends AppCompatActivity {

    final static int SPLASH_OUT_TIME = 10000;
    final static int SLEEP_INTERVAL = 100;
    final static String TAG = "Coorp Gen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Thread splashDelayer = new Thread(){
            int wait = 0;

            @Override
            public void run(){
                try {
                    super.run();
                    while(wait < SPLASH_OUT_TIME){
                        sleep(SLEEP_INTERVAL);
                        wait += SPLASH_OUT_TIME;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d(TAG,"Warning on SplashActivity");
                }finally {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
            }
        };
        splashDelayer.start();
    }

}
