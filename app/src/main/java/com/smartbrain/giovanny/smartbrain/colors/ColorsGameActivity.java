package com.smartbrain.giovanny.smartbrain.colors;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class ColorsGameActivity extends Activity implements TextToSpeech.OnInitListener {

    // 9 imageViews que voy a necesitar para el juego
    private ImageView colorGreen;
    private ImageView colorBlue;
    private ImageView colorOrange;
    private ImageView colorRed;
    private ImageView colorBrown;
    private ImageView colorYellow;
    private ImageView colorGrey;
    private ImageView colorPurple;
    private ImageView colorViewer;
    private ImageView colorWine;
    private ImageView colorOlive;
    // oportunidades
    private ImageView heart;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private ImageView heart4;
    private ImageView heart5;

    // Botones y views comunes que voya  necesitar
    private Button start;
    private Button repeat;

    // animaciones
    private Animation fadeOutAnimation;
    private Animation fadeIn;

    // TTS
    private TextToSpeech tts;

    // arreglos necesarios con instrucciones e imagenes que se van a estar utilizando durante
    //todo el juego Random y variables necesarias para que funcione el juego
    private int imageArray[]={R.drawable.colorblue,R.drawable.brown,R.drawable.wine,R.drawable.olive
            ,R.drawable.coloryellow,R.drawable.colororange,R.drawable.colorred,R.drawable.colorgreen
            ,R.drawable.grey,R.drawable.colorpurple};
    private int number;
    private int i =0;
    private int colorNumber=0;
    private int lives = 0;

    // Random que hace que mi switch case entre entre el rango de numeros deseado y solo eso.
    private Random random = new Random();

    // booleanos que me comprobaran mis juegos
    private boolean selected;
    private boolean selected1;
    private boolean selected2;
    private boolean selected3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_game);
        // seteo de todas los ids
        colorGreen = (ImageView)findViewById(R.id.gameColorGreen);
        colorBlue = (ImageView)findViewById(R.id.gameColorBlue);
        colorBrown = (ImageView)findViewById(R.id.gameColorBrown);
        colorGrey = (ImageView)findViewById(R.id.gameColorGrey);
        colorRed = (ImageView)findViewById(R.id.gameColorRed);
        colorOrange = (ImageView)findViewById(R.id.gameColorOrange);
        colorYellow = (ImageView)findViewById(R.id.gameColorYellow);
        colorOlive = (ImageView)findViewById(R.id.gameColorOlive);
        colorWine = (ImageView)findViewById(R.id.gameColorWine);
        colorPurple= (ImageView) findViewById(R.id.gameColorPurple);
        colorViewer =(ImageView) findViewById(R.id.gameColorViewer);
        start = (Button) findViewById(R.id.start);
        repeat = (Button) findViewById(R.id.repeat);
        heart =(ImageView) findViewById(R.id.heart);
        heart1= (ImageView) findViewById(R.id.heart1);
        heart2 =(ImageView) findViewById(R.id.heart2);
        heart3 =(ImageView) findViewById(R.id.heart3);
        heart4 =(ImageView) findViewById(R.id.heart4);
        heart5 =(ImageView) findViewById(R.id.heart5);



        // set animaciones
        fadeOutAnimation = AnimationUtils.loadAnimation(ColorsGameActivity.this, R.anim.fade_out);
        fadeIn= AnimationUtils.loadAnimation(ColorsGameActivity.this, R.anim.fade_in);

        // drawable resource
        final Drawable redMarkedArea = getResources().getDrawable(R.drawable.red_marked_area);

        // tts to speak
        tts = new TextToSpeech(this,this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = false;
                selected1 = false;
                selected2 = false;
                selected3= false;
                colorNumber=0;
                setImagesToNormalState();
                setImagesToVisible();
                colorViewer.setBackgroundResource(0);
                if(i==9){
                    speakOut("Congratulations!!! You won the game!");
                    Intent intent = new Intent(ColorsGameActivity.this, MenuMediumActivity.class);
                    startActivity(intent);
                    ColorsGameActivity.this.finish();
                }
                number = getListNumber().remove(i);
                switch (number){
                    case 0:
                        speakOut("touch the three primary colors");
                        setImagesForCase1();
                        i++;
                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1= true;
                                if(selected && selected1 && selected2){
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                }else{
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected2 = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("touch the three primary colors");
                            }
                        });
                        break;
                    case 1:
                        speakOut("drag to the red marked area the colors that combined form green");
                        setImagesForCase2();
                        i++;
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("drag to the red marked area the colors that combined form green");
                            }
                        });
                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1= true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber =1;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber =2;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber =3;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber =4;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorViewer.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DRAG_STARTED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_ENTERED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_EXITED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DROP:
                                        if(selected && selected1){
                                            colorViewer.setBackgroundResource(R.drawable.colorgreen);
                                            colorViewer.startAnimation(fadeIn);
                                            speakOut("Well done buddy, Lets continue. Touch start");
                                            setImagesToNormalState();
                                        }else if(selected || selected1){
                                            speakOut("you need one more color buddy");
                                        }else if(colorNumber == 1 || colorNumber==2 ||colorNumber==3 || colorNumber==4){
                                            if(lives == 5){
                                                speakOut("Sorry buddy, lets start again");

                                                ColorsGameActivity.this.recreate();
                                            }else{
                                                speakOut("Sorry try Again");
                                                badAnswers()[lives].setVisibility(View.INVISIBLE);
                                                lives++;
                                            }
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });

                        break;
                    case 2:
                        speakOut("drag to the red marked area the colors that combined form purple");
                        setImagesForCase2();
                        i++;
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("drag to the red marked area the colors that combined form purple");
                            }
                        });
                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=1;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1= true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=2;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=3;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=4;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorViewer.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DRAG_STARTED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_ENTERED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_EXITED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DROP:
                                        if (selected && selected1) {
                                            colorViewer.setBackgroundResource(R.drawable.colorpurple);
                                            colorViewer.startAnimation(fadeIn);
                                            speakOut("Well done buddy, Lets continue. Touch start");
                                            setImagesToNormalState();
                                        } else if (selected || selected1) {
                                            speakOut("you need one more color buddy");
                                        } else if (colorNumber==1 || colorNumber==2|| colorNumber==3 || colorNumber==4) {
                                            if(lives == 5){
                                                speakOut("Sorry buddy, lets start again");
                                                ColorsGameActivity.this.recreate();
                                            }else{
                                                speakOut("Sorry try Again");

                                                badAnswers()[lives].setVisibility(View.INVISIBLE);
                                                lives++;
                                            }
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });

                        break;
                    case 3:
                        speakOut("touch the colors that combined make grey");
                        setImagesForCase3();
                        i++;
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1 = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected2 = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");
                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("touch the colors that combined make grey");
                            }
                        });

                        break;
                    case 4:
                        speakOut("drag to the red marked area the colors that combined form orange");
                        setImagesForCase4();
                        i++;
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("drag to the red marked area the colors that combined form orange");
                            }
                        });
                        //colorYellow,colorRed,colorGreen,colorBlue,colorWine,colorOlive
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1 = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=1;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=2;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=3;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber=4;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorViewer.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DRAG_STARTED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_ENTERED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_EXITED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DROP:
                                        if (selected && selected1) {
                                            colorViewer.setBackgroundResource(R.drawable.colororange);
                                            colorViewer.startAnimation(fadeIn);
                                            speakOut("Well done buddy, Lets continue. Touch start");
                                            setImagesToNormalState();
                                        } else if (selected || selected1) {
                                            speakOut("you need one more color buddy");
                                        } else if (colorNumber==1 || colorNumber==2|| colorNumber==3 || colorNumber==4) {
                                            if(lives == 5){
                                                speakOut("Sorry buddy, lets start again");

                                                ColorsGameActivity.this.recreate();
                                            }else{
                                                speakOut("Sorry try Again");
                                                badAnswers()[lives].setVisibility(View.INVISIBLE);
                                                lives++;
                                            }
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });

                        break;
                    case 5:
                        speakOut("touch all the secondary colors");
                        setImagesForCase1();
                        i++;
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("touch all the secondary colors");
                            }
                        });
                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1 = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected2 = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        break;
                    case 6:
                        speakOut("Touch all the colors that are form by combining the secondary colors");
                        setImagesForCase1();
                        i++;
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("touch all the secondary colors");
                            }
                        });

                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1 = true;
                                if (selected && selected1 && selected2) {
                                    speakOut("Well done buddy, lets continue. Touch start");

                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(lives == 5){
                                    speakOut("Sorry buddy, lets start again");

                                    ColorsGameActivity.this.recreate();
                                }else{
                                    speakOut("Sorry try Again");
                                    badAnswers()[lives].setVisibility(View.INVISIBLE);
                                    lives++;
                                }
                                return true;
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected2 = true;
                                if (selected && selected1 && selected2 && selected3) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected3 = true;
                                if (selected && selected1 && selected2 && selected3) {
                                    speakOut("Well done buddy, lets continue. Touch start");
                                    setImagesToNormalState();
                                } else {
                                    speakOut("Nice");
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                }
                                return true;
                            }
                        });
                        break;
                    case 7:
                        speakOut("drag to the red marked area the colors that combined form olive");
                        setImagesForCase5();
                        i++;
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("drag to the red marked area the colors that combined form olive");
                            }
                        });

                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected= true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                selected1= true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = 1;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = 2;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = 3;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = 4;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });

                        colorViewer.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DRAG_STARTED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_ENTERED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_EXITED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DROP:
                                        if (selected && selected1) {
                                            colorViewer.setBackgroundResource(R.drawable.olive);
                                            colorViewer.startAnimation(fadeIn);
                                            speakOut("Well done buddy, Lets continue. Touch start");
                                            setImagesToNormalState();
                                        } else if (selected || selected1) {
                                            speakOut("you need one more color buddy");
                                        } else if (colorNumber==1 || colorNumber==2|| colorNumber==3 || colorNumber==4) {
                                            if(lives == 5){
                                                speakOut("Sorry buddy, lets start again");

                                                ColorsGameActivity.this.recreate();
                                            }else{
                                                speakOut("Sorry try Again");
                                                badAnswers()[lives].setVisibility(View.INVISIBLE);
                                                lives++;
                                            }
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 8:
                        speakOut("drag to the red marked area one of the primary colors");
                        setImagesForCase1();
                        i++;
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("drag to the red marked area one of the primary colors");
                            }
                        });

                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = R.drawable.colorblue;
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = R.drawable.colorred;
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = R.drawable.coloryellow;
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorViewer.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DRAG_STARTED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_ENTERED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_EXITED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DROP:
                                        if (selected) {
                                            colorViewer.setBackgroundResource(colorNumber);
                                            colorViewer.startAnimation(fadeIn);
                                            speakOut("Well done buddy, Lets continue. Touch start");
                                            setImagesToNormalState();
                                        } else {
                                            if(lives == 5){
                                                speakOut("Sorry buddy, lets start again");

                                                ColorsGameActivity.this.recreate();
                                            }else{
                                                speakOut("Sorry try Again");
                                                badAnswers()[lives].setVisibility(View.INVISIBLE);
                                                lives++;
                                            }
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 9:
                        speakOut("drag to the red marked area one of the secondary colors");
                        setImagesForCase1();
                        i++;
                        colorViewer.setBackgroundDrawable(redMarkedArea);
                        repeat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                speakOut("drag to the red marked area one of the secondary colors");
                            }
                        });

                        colorBlue.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorRed.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorYellow.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorPurple.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = R.drawable.colorpurple;
                                selected = true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.INVISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOrange.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = R.drawable.colororange;
                                selected= true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGreen.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                colorNumber = R.drawable.colorgreen;
                                selected= true;
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.startAnimation(fadeOutAnimation);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorOlive.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorBrown.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorGrey.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        colorWine.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                    ClipData data = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                    v.startDrag(data, shadowBuilder, v, 0);
                                    v.setVisibility(View.VISIBLE);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });

                        colorViewer.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DRAG_STARTED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_ENTERED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DRAG_EXITED:
                                        //do nothing
                                        break;
                                    case DragEvent.ACTION_DROP:
                                        if (selected) {
                                            colorViewer.setBackgroundResource(colorNumber);
                                            colorViewer.startAnimation(fadeIn);
                                            speakOut("Well done buddy, Lets continue. Touch start");
                                            setImagesToNormalState();
                                        } else {
                                            if(lives == 5){
                                                speakOut("Sorry buddy, lets start again");

                                                ColorsGameActivity.this.recreate();
                                            }else{
                                                speakOut("Sorry try Again");
                                                badAnswers()[lives].setVisibility(View.INVISIBLE);
                                                lives++;
                                            }
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                }
            }
        });
    }

    // metodo que me genera una lista random de numeros que no se repite para que las preguntas nunca sean las mismas
    private ArrayList<Integer> getListNumber() {
        ArrayList<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 11) {
            int i = random.nextInt(10);
            if (!numbers.contains(random)) {
                numbers.add(i);
            }
        }
        return numbers;
    }
    //while (numbers.size() < 10) {
    //  int i = random.nextInt(10);
    //for(int j=0;j<numbers.size()-1;j++) {
    //  if (numbers.contains(i)) {
    //    i=random.nextInt(10);
    //}
    //}
    //numbers.add(i);
    //}
    //return numbers;

    // seteo de imagenes
    private void setImagesForCase1() {

        ImageView[] imageViews = {colorBlue, colorBrown, colorWine, colorOlive, colorYellow, colorOrange, colorRed
                , colorGreen, colorGrey,colorPurple};

        for (int i = 0; i < imageViews.length-1; i++) {
            imageViews[i].setBackgroundResource(imageArray[i]);
        }
    }

    private void setImagesForCase2(){
        ImageView[] imageViews ={colorBlue,colorYellow,colorRed,colorGrey,colorOlive,colorBrown};
        int [] array ={R.drawable.colorblue,R.drawable.coloryellow,R.drawable.colorred,R.drawable.grey,R.drawable.olive,R.drawable.brown};
        for(int i=0; i< imageViews.length;i++) {
            imageViews[i].setBackgroundResource(array[i]);
        }
    }

    private void setImagesForCase3(){
        ImageView[] imageViews ={colorYellow,colorRed,colorGreen,colorBrown,colorOrange,colorPurple};
        int [] array ={R.drawable.coloryellow,R.drawable.colorred,R.drawable.colorgreen,R.drawable.brown,R.drawable.colororange,R.drawable.colorpurple};
        for(int i=0; i< imageViews.length;i++) {
            imageViews[i].setBackgroundResource(array[i]);
        }
    }

    private void setImagesForCase4(){
        ImageView[] imageViews ={colorYellow,colorRed,colorGreen,colorBlue,colorWine,colorOlive};
        int [] array ={R.drawable.coloryellow,R.drawable.colorred,R.drawable.colorgreen,R.drawable.colorblue,R.drawable.wine,R.drawable.olive};
        for(int i=0; i< imageViews.length;i++) {
            imageViews[i].setBackgroundResource(array[i]);
        }
    }

    private void setImagesForCase5(){
        ImageView[] imageViews ={colorOrange,colorGrey,colorGreen,colorBrown,colorWine,colorPurple};
        int [] array ={R.drawable.colororange,R.drawable.grey,R.drawable.colorgreen,R.drawable.brown,R.drawable.wine,R.drawable.colorpurple};
        for(int i=0; i< imageViews.length;i++) {
            imageViews[i].setBackgroundResource(array[i]);
        }
    }

    // devolver los image views a blanco
    private void setImagesToNormalState(){
        ImageView[] imageViews = {colorBlue, colorBrown, colorWine, colorOlive, colorYellow, colorOrange, colorRed
                , colorGreen, colorGrey,colorPurple};

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setBackgroundResource(0);

        }
    }

    // setear las imagenes a visible
    private void setImagesToVisible(){
        ImageView[] imageViews = {colorBlue, colorBrown, colorWine, colorOlive, colorYellow, colorOrange, colorRed
                , colorGreen, colorGrey,colorPurple};
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setVisibility(View.VISIBLE);
        }
    }

    // hacer que el tts hable
    private void speakOut(String words){
        tts.speak(words, TextToSpeech.QUEUE_FLUSH, null);
    }
    // bad answers
    private ImageView[] badAnswers() {
        ImageView[] array ={heart,heart1,heart2,heart3,heart4,heart5};
        return array;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("Hi, lets get ready to play. Please tap start");
                Log.e("TTS","TTS is a succes");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts == null) {
            tts.stop();
            tts.shutdown();
        }
        lives =0;
        super.onDestroy();
    }
}