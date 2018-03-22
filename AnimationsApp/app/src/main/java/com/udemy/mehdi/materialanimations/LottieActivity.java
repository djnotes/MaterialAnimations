package com.udemy.mehdi.materialanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.airbnb.lottie.LottieAnimationView;

//Animation files courtesy of https://www.lottiefiles.com/aep


public class LottieActivity extends AppCompatActivity {

    LottieAnimationView heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        heart = findViewById(R.id.heartAnimation);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.invalidate();
                if(heart.isAnimating()) heart.pauseAnimation();
                else heart.playAnimation();
            }
        });
    }
}
