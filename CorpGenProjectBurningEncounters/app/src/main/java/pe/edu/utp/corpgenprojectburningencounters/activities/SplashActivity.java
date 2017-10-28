package pe.edu.utp.corpgenprojectburningencounters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.wangyuwei.particleview.ParticleView;
import pe.edu.utp.corpgenprojectburningencounters.R;

/**
 * Created by root on 10/27/17.
 */

public class SplashActivity extends AppCompatActivity {

    ParticleView particleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        particleView = (ParticleView) findViewById(R.id.my_particle_view);
        particleView.startAnim();

    }




}
