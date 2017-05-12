package com.hudawei.shadersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMagnifier(View view){
        startActivity(new Intent(this,MagnifierActivity.class));
    }
    public void clickMarquee(View view){
        startActivity(new Intent(this,MarqueeActivity.class));
    }
    public void clickRadar(View view){
        startActivity(new Intent(this,RadarActivity.class));
    }
    public void clickWaterRipples(View view){
        startActivity(new Intent(this,WaterRipplesActivity.class));
    }
}
