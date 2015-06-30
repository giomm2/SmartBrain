package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


public class MenuEasyActivity extends Activity {

    private ImageView fishblue;
    private ImageView fishorange;
    private ImageView bubble1;
    private ImageView bfamily;
    private ImageView bvowel;
    private ImageView bbody;
    private MediaPlayer player;
    private MediaPlayer bubblePop;
    //todos los url
    private String  url =  "http://abcsoft.esy.es/grupoBurbujas.png";
    private String  url2="http://abcsoft.esy.es/pezAzul.png";
    private String  url3="http://abcsoft.esy.es/pez.png";
    private String  url4="http://abcsoft.esy.es/burbujaFamily.png";
    private String  url5="http://abcsoft.esy.es/bubbleVowel.png";
    private String  url6="http://abcsoft.esy.es/bubbleBody.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_easy);

        fishblue=(ImageView)findViewById(R.id.imgblue);
        fishorange=(ImageView)findViewById(R.id.imgorange);
        bubble1=(ImageView)findViewById(R.id.imgbubble);
        bfamily=(ImageView)findViewById(R.id.btnfamily);
        bvowel=(ImageView)findViewById(R.id.btnvowel);
        bbody=(ImageView)findViewById(R.id.btnbody);



        //set back sound
        player = MediaPlayer.create(MenuEasyActivity.this, R.raw.blurry);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();

        // set bubblepop sound
        bubblePop = MediaPlayer.create(MenuEasyActivity.this, R.raw.bubblepop);
        bubblePop.setVolume(100, 100);

        bfamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                bubblePop.start();
                Intent intent = new Intent(MenuEasyActivity.this, HardMenuActivity.class);
                startActivity(intent);
            }
        });
        bbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                bubblePop.start();
                Intent intent = new Intent(MenuEasyActivity.this, HardMenuActivity.class);
                startActivity(intent);
            }
        });
        bvowel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                bubblePop.start();
                Intent intent = new Intent(MenuEasyActivity.this, VowelLearnActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
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
        player = MediaPlayer.create(MenuEasyActivity.this, R.raw.blurry);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();
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


}

