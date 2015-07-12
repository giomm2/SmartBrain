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

import com.smartbrain.giovanny.smartbrain.body.BodyTeachActivity1;
import com.smartbrain.giovanny.smartbrain.family.FamilyActivity;
import com.smartbrain.giovanny.smartbrain.vowels.VowelLearnActivity;


public class MenuEasyActivity extends Activity {

    private ImageView fishblue;
    private ImageView fishorange;
    private ImageView bubble1;
    private ImageView bfamily;
    private ImageView bvowel;
    private ImageView bbody;
    private MediaPlayer bubblePop;
    private MediaPlayer music;
    private GestureDetectorCompat gestureDetectorCompat;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_easy);

        // aqui es donde inicializo la variable de deteccion de gestos.
        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());



        fishblue=(ImageView)findViewById(R.id.imgblue);
        fishorange=(ImageView)findViewById(R.id.imgorange);
        bubble1=(ImageView)findViewById(R.id.imgbubble);
        bfamily=(ImageView)findViewById(R.id.btnfamily);
        bvowel=(ImageView)findViewById(R.id.btnvowel);
        bbody=(ImageView)findViewById(R.id.btnbody);

        //set background sound
        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();


        // set bubblepop sound
        bubblePop = MediaPlayer.create(MenuEasyActivity.this, R.raw.bubblepop);
        bubblePop.setVolume(100, 100);

        bfamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                bubblePop.start();
                Intent intent = new Intent(MenuEasyActivity.this, FamilyActivity.class);
                startActivity(intent);
            }
        });
        bbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                bubblePop.start();
                Intent intent = new Intent(MenuEasyActivity.this, BodyTeachActivity1.class);
                startActivity(intent);
            }
        });
        bvowel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                bubblePop.start();
                Intent intent = new Intent(MenuEasyActivity.this, VowelLearnActivity.class);
                startActivity(intent);
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

    public void onStart(){

        super.onStart();
        leftFish();
        upBubble();
        bubble();

    }

    @Override

    public void onResume(){

        super.onResume();
        music= MediaPlayer.create(this,R.raw.fireflies);
        music.setLooping(true);
        music.start();
        leftFish();
        upBubble();
        bubble();
    }


    private void leftFish(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.leftanim);
        movn.reset();
        fishblue.startAnimation(movn);
        fishorange.startAnimation(movn);

    }

    private void upBubble(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.upanim);
        movn.reset();
        bubble1.startAnimation(movn);


    }

    private void bubble(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.bubble);
        movn.reset();
        bbody.startAnimation(movn);
        bvowel.startAnimation(movn);
        bfamily.startAnimation(movn);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            if(event2.getX() < event1.getX()){
                //switch another activity
                Intent intent = new Intent(
                        MenuEasyActivity.this, MenuMediumActivity.class);
                startActivity(intent);
                MenuEasyActivity.this.finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }else if(event2.getX() > event1.getX()){
                Intent intent = new Intent(
                        MenuEasyActivity.this, HardMenuActivity.class);
                startActivity(intent);
                MenuEasyActivity.this.finish();
                overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
            }

            return true;
        }
    }
}




