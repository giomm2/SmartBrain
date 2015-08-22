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
    private MediaPlayer selectSound;


    private GestureDetectorCompat gestureDetectorCompat;
    private MediaPlayer music;
    private int pointsC;
    private Button btnexit;
    ToastActivity toastActivity=new ToastActivity();
    ToastExitActivity toastExitActivity= new ToastExitActivity();

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
        btnexit=(Button)findViewById(R.id.btn_exit);
        userName.setText(extras.getString("NAME"));
        points.setText("Points: " + extras.getInt("POINTS"));
        gestureDetectorCompat= new GestureDetectorCompat(this, new MyGestureListener());
        //Backround sound
        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();
        selectSound = MediaPlayer.create(MenuMediumActivity.this, R.raw.pop);
        pointsC=extras.getInt("POINTS");



        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toastExitActivity.showDialog(MenuMediumActivity.this, " Do you want to exit? ");

            }
        });

        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pointsC>=2000){
                selectSound.start();
                music.stop();
                Intent intent = new Intent(MenuMediumActivity.this, NeighborhoodLoadingActivity.class);
                    bundle.putString("NAME", extras.getString("NAME"));
                    bundle.putInt("POINTS", extras.getInt("POINTS"));
                    bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                MenuMediumActivity.this.finish();
                }
                else{

                    toastActivity.showDialog(MenuMediumActivity.this,"Sorry, you need more than 2000 points.");
                }
            }
        });
        flower1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pointsC>=4000){
                selectSound.start();
                music.stop();
                Intent intent = new Intent(MenuMediumActivity.this, AnimalsLoadingActivity.class);
                    bundle.putString("NAME", extras.getString("NAME"));
                    bundle.putInt("POINTS", extras.getInt("POINTS"));
                    bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                MenuMediumActivity.this.finish();
                }
                else{

                    toastActivity.showDialog(MenuMediumActivity.this,"Sorry, you need more than 4000 points.");
                }
            }
        });
        flower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pointsC>=3000){

                selectSound.start();
                Intent intent = new Intent(MenuMediumActivity.this, ColorsLoadingActivity.class);
                    bundle.putString("NAME", extras.getString("NAME"));
                    bundle.putInt("POINTS", extras.getInt("POINTS"));
                    bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                music.stop();
                MenuMediumActivity.this.finish();
                }
                    else{

                        toastActivity.showDialog(MenuMediumActivity.this,"Sorry, you need more than 3000 points.");
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
        flowerMovement();
        smokeAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        music.start();
        flowerMovement();
        smokeAnimation();
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
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
                music.stop();
                MenuMediumActivity.this.finish();
            }else if(event2.getX() > event1.getX()){
                Intent intent = new Intent(
                        MenuMediumActivity.this, MenuEasyActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
                music.stop();
                MenuMediumActivity.this.finish();
            }

            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.stop();
    }
}
