package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MenuEasyActivity extends Activity {

    private ImageView fishblue;
    private ImageView fishorange;
    private ImageView bubble;
    private ImageButton bfamily;
    private ImageButton bvowel;
    private ImageButton bbody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_easy);

        fishblue=(ImageView)findViewById(R.id.imgblue);
        fishorange=(ImageView)findViewById(R.id.imgorange);
        bubble=(ImageView)findViewById(R.id.imgbubble);
        bfamily=(ImageButton)findViewById(R.id.btnfamily);
        bvowel=(ImageButton)findViewById(R.id.btnvowel);
        bbody=(ImageButton)findViewById(R.id.btnbody);
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
        bubble.startAnimation(movn);


    }

    private void bubble(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.bubble);
        movn.reset();
        bbody.startAnimation(movn);
        bvowel.startAnimation(movn);
        bfamily.startAnimation(movn);
    }


}
