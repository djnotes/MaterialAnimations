package com.udemy.mehdi.materialanimations;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SharedOne extends AppCompatActivity implements View.OnClickListener {

    ImageView sheep;
    private Activity mActivity = this;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_one);

        sheep = findViewById(R.id.sheep);

        sheep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sheep:
                startActivity(new Intent(mContext, SharedTwo.class), ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle());
        }
    }
}
