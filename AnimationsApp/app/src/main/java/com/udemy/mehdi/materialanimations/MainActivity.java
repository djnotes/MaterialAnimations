package com.udemy.mehdi.materialanimations;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.transition.Slide;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
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
            case R.string.ripple:
                startActivity(new Intent(mContext, RippleActivity.class));
                break;
            case R.string.circular_reveal:
                initCircularReveal(view);
                break;
            case R.string.slide_animation:
                initSlideAnimation(view);
                break;
        }
    }

    private void initSlideAnimation(View view) {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.TOP);
        ViewGroup root = findViewById(android.R.id.content);

        TransitionManager.beginDelayedTransition(root, slide);
        view.setVisibility(View.INVISIBLE);
    }

    private void initCircularReveal(View view) {
        boolean isVeggie = ((ColorDrawable)view.getBackground()) != null && ((ColorDrawable)view.getBackground()).getColor() == Color.GREEN;

        TransitionManager.beginDelayedTransition((ViewGroup) view);
        double finalRadius = Math.hypot(view.getWidth()/2, view.getHeight() / 2);

        if (isVeggie) {
            view.setBackgroundColor(Color.rgb(204, 204,204));
        } else {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, (int) view.getWidth()/2, (int) view.getHeight()/2, 0, (float)finalRadius);
            view.setBackgroundColor(Color.GREEN);
            anim.start();
        }

    }
}
