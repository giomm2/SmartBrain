package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Cryven on 25/06/2015.
 */
public class MenuMediumActivity extends Activity {

    private ImageView cloud;
    private ImageView flower;
    private ImageView flower1;
    private ImageView flower2;
    private ImageView smoke;
    private MediaPlayer wind;
    private MediaPlayer river;
    private GestureDetectorCompat gestureDetectorCompat;
    private MediaPlayer music;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_medium);
        cloud = (ImageView)findViewById(R.id.cloud);
        flower = (ImageView)findViewById(R.id.flower1);
        flower1 = (ImageView)findViewById(R.id.flower2);
        flower2 = (ImageView)findViewById(R.id.flower3);
        smoke = (ImageView) findViewById(R.id.smoke);

        gestureDetectorCompat= new GestureDetectorCompat(this, new MyGestureListener());

        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();

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

        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                river.stop();
                wind.stop();
                music.stop();
            }
        });
        flower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                river.stop();
                wind.stop();
                music.stop();
            }
        });
        flower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                river.stop();
                wind.stop();
                music.stop();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        music.stop();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        flowerMovement();
        smokeAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();
        flowerMovement();
        smokeAnimation();
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
        movement= AnimationUtils.loadAnimation(this, R.anim.bubble);
        movement.reset();
        smoke.startAnimation(movement);

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            if(event2.getX() < event1.getX()){
                //switch another activity
                Intent intent = new Intent(
                        MenuMediumActivity.this, HardMenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }else if(event2.getX() > event1.getX()){
                Intent intent = new Intent(
                        MenuMediumActivity.this, MenuEasyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
            }

            return true;
        }
    }
}
