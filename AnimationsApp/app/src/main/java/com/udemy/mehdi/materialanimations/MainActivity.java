package com.udemy.mehdi.materialanimations;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener{
    ImageView image;
    RecyclerView recyclerView;
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }


    private void initViews() {
        image = findViewById(R.id.image);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        MyAdapter adapter = new MyAdapter(mContext);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    private void performAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(image, "translationX", 200f);
        animator.setDuration(500);
        animator.start();
    }

    @Override
    public void onItemClick(View view, int position) {
        int titleRes = MyAdapter.TITLES[position];
        switch(titleRes) {
            case R.string.animation_move_view:
                performAnimation();
                break;

            case R.string.animation_lottie:
                startActivity(new Intent(mContext, LottieActivity.class));
                break;
            case R.string.constraint_layout_animations:
                startActivity(new Intent(mContext, ConstraintLayoutActivity.class));
                break;
        }
    }
}
