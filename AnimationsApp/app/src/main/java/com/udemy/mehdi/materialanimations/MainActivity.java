package com.udemy.mehdi.materialanimations;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        animator.setDuration(1000);
        animator.start();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(mContext, "Item clicked: " + position, Toast.LENGTH_LONG).show();
        switch(position) {

            case 3:
                performAnimation();
                break;

        }
    }
}
