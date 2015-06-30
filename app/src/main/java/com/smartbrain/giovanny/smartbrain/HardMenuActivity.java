package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class HardMenuActivity extends Activity {

    private ImageView baloom;
    private ImageView baloom2;
    private ImageView baloom3;
    private ImageView zeppelin;
    private ImageView rocket;
    private ImageView rocket2;
    private ImageView firework;
    private ImageView firework2;
    private RelativeLayout layout;
    private MediaPlayer player;
    private MediaPlayer selectSound;
    private GestureDetectorCompat gestureDetectorCompat;



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
        layout = (RelativeLayout) findViewById(R.id.layout);

        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());

        //Musica de fondo
        player = MediaPlayer.create(HardMenuActivity.this, R.raw.fireflies);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();

        //sonido de escoger las opciones
        selectSound = MediaPlayer.create(HardMenuActivity.this, R.raw.pop);
        selectSound.setVolume(100,100);









        baloom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound.start();
                Intent intent = new Intent(HardMenuActivity.this, MenuEasyActivity.class);
                startActivity(intent);
                player.stop();
            }
        });

        baloom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound.start();
                Intent intent = new Intent(HardMenuActivity.this, MenuEasyActivity.class);
                startActivity(intent);
                player.stop();
            }
        });

        baloom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound.start();
                Intent intent = new Intent(HardMenuActivity.this, MenuEasyActivity.class);
                startActivity(intent);
                player.stop();
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
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
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        leftZeppelin();
        player = MediaPlayer.create(HardMenuActivity.this, R.raw.fireflies);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();
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
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
            }else if(event2.getX() > event1.getX()){
                Intent intent = new Intent(
                        HardMenuActivity.this, MenuMediumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
            }

            return true;
        }
    }

}
