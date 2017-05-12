package com.hudawei.shadersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hudawei on 2017/5/11.
 *
 */

public class RadarActivity extends AppCompatActivity {
    private RadarView radarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        radarView = (RadarView) findViewById(R.id.radarView);
    }

    public void start(View view) {
        radarView.start();
    }

    public void stop(View view) {
        radarView.stop();
    }

}
