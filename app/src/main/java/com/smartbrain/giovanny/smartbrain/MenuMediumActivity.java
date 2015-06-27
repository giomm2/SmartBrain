package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Cryven on 25/06/2015.
 */
public class MenuMediumActivity extends Activity {

    private ImageView cloud;
    private ImageView cloud1;
    private ImageView cloud2;
    private ImageView flower;
    private ImageView flower1;
    private ImageView flower2;
    private ImageView smoke;
    private MediaPlayer wind;
    private MediaPlayer river;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_medium);
        cloud = (ImageView)findViewById(R.id.cloud);
        flower = (ImageView)findViewById(R.id.flower1);
        flower1 = (ImageView)findViewById(R.id.flower2);
        flower2 = (ImageView)findViewById(R.id.flower3);
        smoke = (ImageView) findViewById(R.id.smoke);

        //Backround sound of wind

        wind = MediaPlayer.create(MenuMediumActivity.this, R.raw.wind);
        wind.setLooping(true); // Set looping
        wind.setVolume(100, 100);
        wind.start();

        //Backround sound of river
        river = MediaPlayer.create(MenuMediumActivity.this, R.raw.river);
        wind.setLooping(true);
        river.setVolume(100, 100);
        river.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        flowerMovement();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowerMovement();
    }

    @Override
    protected void onStop() {
        super.onStop();
        wind.stop();
        river.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wind.stop();
        river.stop();
    }

    public void flowerMovement(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.flower_animation);
        movement.reset();
        flower2.startAnimation(movement);
        flower1.startAnimation(movement);
        flower.startAnimation(movement);
    }

    public void smokeAnimation(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.smoke_animation);
        movement.reset();
        smoke.startAnimation(movement);

    }
}
