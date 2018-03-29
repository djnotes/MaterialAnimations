package com.udemy.mehdi.materialanimations;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    //Todo: https://www.youtube.com/watch?v=CPxkoe2MraA

    private static final String PACKAGE = "com.udemy.mehdi.materialanimations";
    private static final int ANIM_DURATION = 500;
    ImageView detailImage;
    private int mOriginalOrientation;
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;
    private static TimeInterpolator sDecelerator = new DecelerateInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = findViewById(R.id.detailImage);


        //Get intent
        Bundle bundle = getIntent().getExtras();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), bundle.getInt(PACKAGE + ".resourceId"));
        final int thumbnailTop = bundle.getInt(PACKAGE + ".top");
        final int thumbnailLeft = bundle.getInt(PACKAGE + ".left");
        final int thumbnailWidth = bundle.getInt(PACKAGE + ".width");
        final int thumbnailHeight = bundle.getInt(PACKAGE + ".height");
        mOriginalOrientation = bundle.getInt(PACKAGE + ".orientation");

        detailImage.setImageBitmap(bmp);


        if (savedInstanceState == null) {
            ViewTreeObserver observer = detailImage.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    detailImage.getViewTreeObserver().removeOnPreDrawListener(this);

                    int [] location = new int [2];
                    detailImage.getLocationOnScreen(location);
                    mLeftDelta = thumbnailLeft - location[0];
                    mTopDelta = thumbnailTop - location[1];

                    mWidthScale = (float) thumbnailWidth / detailImage.getWidth();
                    mHeightScale = (float) thumbnailHeight / detailImage.getHeight();

                    runEnterAnimation();

                    return true;
                }
            });
        }
    }

    private void runEnterAnimation() {
        final long duration = (long) (ANIM_DURATION * CustomAnimationActivity.sAnimationScale);

        detailImage.setPivotX(0);
        detailImage.setPivotY(0);
        detailImage.setScaleX(mWidthScale);
        detailImage.setScaleY(mHeightScale);
        detailImage.setTranslationX(mLeftDelta);
        detailImage.setTranslationY(mTopDelta);

        //Animate and go from thumbnail to fullsize
        detailImage.animate().setDuration(duration)
                .scaleX(1).scaleY(1)
                .translationX(0).translationY(0)
                .setInterpolator(sDecelerator);

        //Fade in the black background
//        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0, 255);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
