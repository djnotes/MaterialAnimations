package me.mehdi.transitions;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context mContext = this;
    Scene mScene1, mScene2, mScene3;
    ViewGroup mSceneRoot;
    TransitionManager mTransitionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene_1);

        //Get scene root
        mSceneRoot = findViewById(R.id.container);

        //Inflate TransitionManager
        TransitionInflater transitionInflater = TransitionInflater.from(mContext);

        //Create 3 scenes
        mScene1 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_1, mContext);
        mScene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_2, mContext);
        mScene3 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_3, mContext);
        mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_mgr, mSceneRoot);


    }


    public void onRadioClicked(View view) {
        int radioId = view.getId();
        switch(radioId) {
            case R.id.scene_1:
                mTransitionManager.transitionTo(mScene1);
                break;
            case R.id.scene_2:
                mTransitionManager.transitionTo(mScene2);
                break;
            case R.id.scene_3:
                mTransitionManager.transitionTo(mScene3);
                break;
            case R.id.scene_4:
                //Note that there is no scene_4 in our layouts
                //This is just a dynamic scene
                //We make changes on the fly and then let TransitionManager apply them

                TransitionManager.beginDelayedTransition(mSceneRoot);
                setNewSize(R.id.view_1, 150, 50);
                setNewSize(R.id.view_2, 150, 50);
                setNewSize(R.id.view_3, 150, 50);
                setNewSize(R.id.view_4, 150, 50);
                break;
        }
    }

    private void setNewSize(int id, int width, int height) {
        View view = findViewById(id);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }
}
