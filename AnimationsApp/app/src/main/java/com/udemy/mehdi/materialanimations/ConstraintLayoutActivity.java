package com.udemy.mehdi.materialanimations;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.Placeholder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;

public class ConstraintLayoutActivity extends AppCompatActivity implements View.OnClickListener {
ConstraintLayout rootLayout;
ImageView img1, img2, img3;
Placeholder placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        initViews();
    }

    private void initViews() {

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);

        rootLayout = findViewById(R.id.rootLayout);
        placeholder = findViewById(R.id.placeholder);
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(rootLayout);
        placeholder.setContentId(v.getId());
    }
}
