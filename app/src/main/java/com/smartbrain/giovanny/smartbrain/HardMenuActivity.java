package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(HardMenuActivity.this, MenuEasyActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }
        });


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


}
