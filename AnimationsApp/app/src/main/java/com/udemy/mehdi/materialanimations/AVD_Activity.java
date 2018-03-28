package com.udemy.mehdi.materialanimations;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AVD_Activity extends AppCompatActivity {

    ImageView image;
    private boolean tick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avd_);

        image = findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAVD();
            }
        });
    }

    private void initAVD() {
        AnimatedVectorDrawable crossToTick = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_cross_to_tick);
        AnimatedVectorDrawable tickToCross = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_tick_to_cross);
        AnimatedVectorDrawable crossTick = tick ? crossToTick : crossToTick;
        image.setImageDrawable(crossTick);
        tick = !tick;
        if (crossToTick != null)
            crossToTick.start();
    }
}
