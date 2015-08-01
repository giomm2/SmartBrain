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
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.animals.AnimalsLoadingActivity;
import com.smartbrain.giovanny.smartbrain.colors.ColorsLoadingActivity;
import com.smartbrain.giovanny.smartbrain.neighborhood.NeighborhoodLoadingActivity;

public class MenuMediumActivity extends Activity  {

    private ImageView cloud;
    private ImageView flower;
    private ImageView flower1;
    private ImageView flower2;
    private ImageView smoke;
    private TextView userName;
    private TextView points;
    Bundle bundle = new Bundle();
    Bundle extras;

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
        // bundle
        extras = getIntent().getExtras();
        userName = (TextView) findViewById(R.id.userName);
        points = (TextView) findViewById(R.id.points);
        userName.setText(extras.getString("NAME"));
        points.setText("Points: " + extras.getInt("POINTS"));
        gestureDetectorCompat= new GestureDetectorCompat(this, new MyGestureListener());
        //Backround sound
        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();



        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                music.stop();
                Intent intent = new Intent(MenuMediumActivity.this, NeighborhoodLoadingActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        flower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                music.stop();
                Intent intent = new Intent(MenuMediumActivity.this, AnimalsLoadingActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        flower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMediumActivity.this, ColorsLoadingActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
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

    }

    @Override
    protected void onPause() {
        super.onPause();

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
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                MenuMediumActivity.this.finish();
            }else if(event2.getX() > event1.getX()){
                Intent intent = new Intent(
                        MenuMediumActivity.this, MenuEasyActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
                MenuMediumActivity.this.finish();
            }

            return true;
        }
    }
}
