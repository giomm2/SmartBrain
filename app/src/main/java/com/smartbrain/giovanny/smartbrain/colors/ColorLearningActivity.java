package com.smartbrain.giovanny.smartbrain.colors;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.MenuEasyActivity;
import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;
import java.util.Locale;

public class ColorLearningActivity extends Activity implements TextToSpeech.OnInitListener {

    // set de las variables que necesito
    private ImageView blueColor;
    private ImageView redColor;
    private ImageView yellowColor;
    private ImageView blueColor2;
    private ImageView colorViewer;
    private ImageView next;
    private ImageView previus;
    private ImageView secondaryColors;
    // animaciones necesarias
    private Animation movement;
    private Animation movement2;
    private Animation yellowMovement;
    private Animation blueAnimation;
    private Animation colorFadeIn;
    private String paymentStatus;
    // TTS
    private TextToSpeech tts;
    // variable que me va a recorrer los arreglos
    int i = 0;
    // arreglo con palabras para el TTS
    private String[] colorWords = {"Now we are going to learn about the colors, first we have the primary, and secondary colors. Please tab the arrow to continue"
            ,"first, we have blue and red combining to create purple.","now we combine yellow and blue to make green"
            ,"and finally we use red and yellow to make orange, press the purple color to continue"};


    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_learning);

        // seteo de ids
        blueColor = (ImageView)findViewById(R.id.blue);
        redColor = (ImageView) findViewById(R.id.red);
        yellowColor= (ImageView) findViewById(R.id.yellow);
        blueColor2= (ImageView) findViewById(R.id.blue2);
        colorViewer = (ImageView)findViewById(R.id.colorViewer);
        next= (ImageView)findViewById(R.id.next);
        previus= (ImageView)findViewById(R.id.previus);
        secondaryColors = (ImageView) findViewById(R.id.secondaryColors);
        // tts para que hable la vvara
        tts= new TextToSpeech(this, this);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");

        //animacion de el color recien formado
        colorFadeIn= AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_invisible_tovisible);

        //boton next que tiene un switch case para ver que hablar y que mostrar con sus respectivas animaciones
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previus.setVisibility(View.VISIBLE);
                if(i ==3) {
                    i = 3;
                }else
                    i++;
                switch (i){
                    case 1:
                        speakOut();
                        movement = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_blue_transition);
                        movement2 = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_red_transition);
                        movement2.reset();
                        movement.reset();
                        blueColor.startAnimation(movement);
                        redColor.startAnimation(movement2);
                        colorViewer.setVisibility(View.VISIBLE);
                        colorViewer.setBackgroundResource(R.drawable.colorpurple);
                        colorViewer.startAnimation(colorFadeIn);
                        break;
                    case 2:
                        speakOut();
                        yellowMovement = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_yellow_animation);
                        blueAnimation = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_blueup_animation);
                        yellowMovement.reset();
                        blueAnimation.reset();
                        yellowColor.startAnimation(yellowMovement);
                        blueColor2.startAnimation(blueAnimation);
                        colorViewer.setVisibility(View.VISIBLE);
                        colorViewer.setBackgroundResource(R.drawable.colorgreen);
                        colorViewer.startAnimation(colorFadeIn);
                        break;
                    case 3:
                        speakOut();
                        yellowMovement = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_yellow_animation);
                        movement2 = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_red_transition);
                        movement2.reset();
                        yellowMovement.reset();
                        yellowColor.startAnimation(yellowMovement);
                        redColor.startAnimation(movement2);
                        colorViewer.setVisibility(View.VISIBLE);
                        colorViewer.setBackgroundResource(R.drawable.colororange);
                        colorViewer.startAnimation(colorFadeIn);
                        secondaryColors.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });

        // boton previous que se devuelve en caso de ser necesario si asi lo quiere el usuario
        previus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i == 1)
                    i =1;
                else
                    i--;
                switch (i){
                    case 1:
                        speakOut();
                        movement = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_blue_transition);
                        movement2 = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_red_transition);
                        movement2.reset();
                        movement.reset();
                        blueColor.startAnimation(movement);
                        redColor.startAnimation(movement2);
                        colorViewer.setVisibility(View.VISIBLE);
                        colorViewer.setBackgroundResource(R.drawable.colorpurple);
                        colorViewer.startAnimation(colorFadeIn);
                        break;
                    case 2:
                        speakOut();
                        yellowMovement = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_yellow_animation);
                        blueAnimation = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_blueup_animation);
                        yellowMovement.reset();
                        blueAnimation.reset();
                        yellowColor.startAnimation(yellowMovement);
                        blueColor2.startAnimation(blueAnimation);
                        colorViewer.setVisibility(View.VISIBLE);
                        colorViewer.setBackgroundResource(R.drawable.colorgreen);
                        colorViewer.startAnimation(colorFadeIn);
                        break;
                    case 3:
                        speakOut();
                        yellowMovement = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_yellow_animation);
                        movement2 = AnimationUtils.loadAnimation(ColorLearningActivity.this, R.anim.color_red_transition);
                        movement2.reset();
                        yellowMovement.reset();
                        yellowColor.startAnimation(yellowMovement);
                        redColor.startAnimation(movement2);
                        colorViewer.setVisibility(View.VISIBLE);
                        colorViewer.setBackgroundResource(R.drawable.colororange);
                        colorViewer.startAnimation(colorFadeIn);
                        break;
                }
            }
        });


        // boton que me lleva a los colores secundarios
        secondaryColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorLearningActivity.this, SecondaryColorLearningActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                ColorLearningActivity.this.finish();
            }
        });

    }

    private void speakOut(){
        tts.speak(colorWords[i], TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (ColorLearningActivity.this, MenuMediumActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            ColorLearningActivity.this.finish();
            tts.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

}