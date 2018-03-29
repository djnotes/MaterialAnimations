package com.udemy.mehdi.materialanimations;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;

public class CustomAnimationActivity extends AppCompatActivity {

    private static final String PACKAGE = "com.udemy.mehdi.materialanimations";
    private static final String TAG = "CustomAnimationActivity";
    public static int sAnimationScale = 1;
    Context mContext = this;
    GridLayout gridLayout;
    int mImages[] = {R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3};
    HashMap<ImageView, PictureInfo> mPictureData = new HashMap<>();

    private View.OnClickListener thumbnailClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_animation);

        gridLayout = findViewById(R.id.grid);
        gridLayout.setColumnCount(3);
        gridLayout.setUseDefaultMargins(true);

        for (int i = 0; i < 18; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImages[i % mImages.length]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            gridLayout.addView(imageView, 250, 200);
            PictureInfo pInfo = new PictureInfo();
            pInfo.resId = mImages[ i % mImages.length];
            mPictureData.put(imageView, pInfo);
            RelativeLayout container = (RelativeLayout) gridLayout.getChildAt(i);
            container.setOnClickListener(thumbnailClickListener);

        }

        thumbnailClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Thumbnail clicked", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: ");
               int [] screenLocation = new int[2];
               v.getLocationOnScreen(screenLocation);
                Intent subActivity = new Intent(mContext, DetailActivity.class);
                int orientation = getResources().getConfiguration().orientation;
                PictureInfo pInfo = mPictureData.get(v);
                subActivity.putExtra(PACKAGE + ".orientation", orientation )
                        .putExtra(PACKAGE + ".resourceId", pInfo.resId)
                        .putExtra(PACKAGE + ".left" , screenLocation[0])
                        .putExtra(PACKAGE + ".top", screenLocation[1])
                        .putExtra(PACKAGE + ".width", v.getWidth())
                        .putExtra(PACKAGE + ".height", v.getHeight());
                startActivity(subActivity);

                overridePendingTransition(0, 0);
            }
        };



    }

}
