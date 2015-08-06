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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.Numbers.NumbersLoadingActivity;
import com.smartbrain.giovanny.smartbrain.practice.PracticeLoadingActivity;
import com.smartbrain.giovanny.smartbrain.shapes.ShapesLoadingActivity;


public class HardMenuActivity extends Activity {

    private ImageView baloom;
    private ImageView baloom2;
    private ImageView baloom3;
    private ImageView zeppelin;
    private ImageView rocket;
    private ImageView rocket2;
    private ImageView firework;
    private ImageView firework2;
    private MediaPlayer selectSound;
    private GestureDetectorCompat gestureDetectorCompat;
    private MediaPlayer music;
    private TextView userName;
    private TextView points;
    Bundle bundle = new Bundle();
    Bundle extras;
    private int pointsC;
    ToastActivity toastActivity=new ToastActivity();
    private Button btnexit;
    ToastExitActivity toastExitActivity= new ToastExitActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_menu);

        baloom = (ImageView) findViewById(R.id.baloom);
        baloom2 = (ImageView) findViewById(R.id.baloom2);
        baloom3 = (ImageView) findViewById(R.id.baloom3);
        zeppelin = (ImageView) findViewById(R.id.zeppelin);
        rocket = (ImageView) findViewById(R.id.rocket);
        rocket2 = (ImageView) findViewById(R.id.rocket2);
        firework = (ImageView) findViewById(R.id.firework);
        firework2 = (ImageView) findViewById(R.id.firework2);
        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        // bundle
        extras = getIntent().getExtras();
        userName = (TextView) findViewById(R.id.userName);
        points = (TextView) findViewById(R.id.points);
        btnexit=(Button)findViewById(R.id.btn_exit);
        userName.setText(extras.getString("NAME"));
        points.setText("Points: " + extras.getInt("POINTS"));
        //sonido de escoger las opciones
        selectSound = MediaPlayer.create(HardMenuActivity.this, R.raw.pop);
        //selectSound.setVolume(100,100);

        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();
        pointsC=extras.getInt("POINTS");

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toastExitActivity.showDialog(HardMenuActivity.this, " Do you want to exit? ");

            }
        });
        baloom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pointsC>=5500){

                selectSound.start();
                Intent intent = new Intent(HardMenuActivity.this, NumbersLoadingActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
                music.stop();}
                else{

                    toastActivity.showDialog(HardMenuActivity.this,"Sorry, you need more than 5500 points.");
                }
            }
        });

        baloom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pointsC>=8500){
                selectSound.start();
                Intent intent = new Intent(HardMenuActivity.this, PracticeLoadingActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
                music.stop();}
                else{

                    toastActivity.showDialog(HardMenuActivity.this,"Sorry, you need more than 8500 points.");
                }
            }
        });

        baloom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pointsC>=7000){
                selectSound.start();
                Intent intent = new Intent(HardMenuActivity.this, ShapesLoadingActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                intent.putExtras(bundle);
                startActivity(intent);
                music.stop();}
                else{

                    toastActivity.showDialog(HardMenuActivity.this,"Sorry, you need more than 7000 points.");
                }
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
        leftZeppelin();
        topBaloomMovement();
        leftBaloomMovement();
        rightBallomMovement();
        rocketMovement();
        fireworkEffect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        leftZeppelin();
        music.start();
        topBaloomMovement();
        leftBaloomMovement();
        rightBallomMovement();
        rocketMovement();
        fireworkEffect();
    }

    private void leftZeppelin(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.leftanim);
        movement.reset();
        zeppelin.startAnimation(movement);

    }
    private void topBaloomMovement(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.up_down_animation);
        movement.reset();
        baloom.startAnimation(movement);
    }

    private void leftBaloomMovement(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.down_up_animation);
        movement.reset();
        baloom3.startAnimation(movement);
    }

    private void rightBallomMovement(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.down_up_slow_animation);
        movement.reset();
        baloom2.startAnimation(movement);
    }

    private void rocketMovement(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.rocket_animation);
        movement.reset();
        rocket.startAnimation(movement);
        rocket2.startAnimation(movement);

    }

    private void fireworkEffect(){
        Animation movement;
        movement= AnimationUtils.loadAnimation(this, R.anim.explosive_animation);
        movement.reset();
        firework.startAnimation(movement);
        firework2.startAnimation(movement);
    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            if(event2.getX() < event1.getX()){
                //switch another activity
                Intent intent = new Intent(
                        HardMenuActivity.this, MenuEasyActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                music.stop();
                HardMenuActivity.this.finish();
            }else if(event2.getX() > event1.getX()){
                Intent intent = new Intent(
                        HardMenuActivity.this, MenuMediumActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
                music.stop();
                HardMenuActivity.this.finish();
            }

            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        music.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.stop();
    }
}
