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

import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;
import java.util.Locale;

public class SecondaryColorLearningActivity extends Activity implements TextToSpeech.OnInitListener {

    // variables que necesito para la actividad
    private ImageView green;
    private ImageView purple;
    private ImageView orange;
    private ImageView next;
    private ImageView privious;
    private ImageView secondaryColorViewer;
    private ImageView playButton;
    private String paymentStatus;
    // TTS
    private TextToSpeech tts;
    // arreglo con palabras para el tts
    private String[] array ={"You can take the secondary colors, and combine them to from tertiary colors. Please touch next to continue"
            ,"purple plus orange make brown","purple and green form wine","green and orange make olive"
            ,"and when combine all together, we make grey, touch the play controller button to play with all the colors"};
    // int que me recorre el arreglo
    int i=0;
    //animaciones necesarias
    private Animation greenMovement;
    private Animation purpleMovement;
    private Animation orangeMovement;
    private Animation colorFadeIn;

    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_color_learning);

        // seteo de ids
        green = (ImageView) findViewById(R.id.green);
        purple = (ImageView) findViewById(R.id.purple);
        orange= (ImageView) findViewById(R.id.orange);
        secondaryColorViewer = (ImageView)findViewById(R.id.secondaryColorViewer);
        next = (ImageView) findViewById(R.id.next);
        privious =(ImageView) findViewById(R.id.previus);
        playButton = (ImageView) findViewById(R.id.playButton);
        // tts para que hable la vvara
        tts= new TextToSpeech(this, this);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");
        //animacion de el color recien formado
        colorFadeIn= AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_invisible_tovisible);

        //boton next que tiene un switch case para ver que hablar y que mostrar con sus respectivas animaciones
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privious.setVisibility(View.VISIBLE);
                if(i ==4) {
                    i = 4;
                }else
                    i++;
                switch (i){
                    case 1:
                        speakOut();
                        purpleMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_blue_transition);
                        orangeMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_red_transition);
                        purpleMovement.reset();
                        orangeMovement.reset();
                        purple.startAnimation(purpleMovement);
                        orange.startAnimation(orangeMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.brown);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                    case 2:
                        speakOut();
                        purpleMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_blue_transition);
                        greenMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.green_up_animation);
                        purpleMovement.reset();
                        greenMovement.reset();
                        purple.startAnimation(purpleMovement);
                        green.startAnimation(greenMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.wine);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                    case 3:
                        speakOut();
                        orangeMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_red_transition);
                        greenMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.green_up_animation);
                        orangeMovement.reset();
                        greenMovement.reset();
                        orange.startAnimation(orangeMovement);
                        green.startAnimation(greenMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.olive);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                    case 4:
                        speakOut();
                        orangeMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_red_transition);
                        greenMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.green_up_animation);
                        purpleMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_blue_transition);
                        purpleMovement.reset();
                        orangeMovement.reset();
                        greenMovement.reset();
                        orange.startAnimation(orangeMovement);
                        green.startAnimation(greenMovement);
                        purple.startAnimation(purpleMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.grey);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        playButton.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        privious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i ==1)
                    i=1;
                else
                    i--;
                switch (i){
                    case 1:
                        speakOut();
                        purpleMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_blue_transition);
                        orangeMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_red_transition);
                        purpleMovement.reset();
                        orangeMovement.reset();
                        purple.startAnimation(purpleMovement);
                        orange.startAnimation(orangeMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.wine);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                    case 2:
                        speakOut();
                        purpleMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_blue_transition);
                        greenMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.green_up_animation);
                        purpleMovement.reset();
                        greenMovement.reset();
                        purple.startAnimation(purpleMovement);
                        green.startAnimation(greenMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.brown);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                    case 3:
                        speakOut();
                        orangeMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_red_transition);
                        greenMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.green_up_animation);
                        orangeMovement.reset();
                        greenMovement.reset();
                        orange.startAnimation(orangeMovement);
                        green.startAnimation(greenMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.olive);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                    case 4:
                        speakOut();
                        orangeMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_red_transition);
                        greenMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.green_up_animation);
                        purpleMovement = AnimationUtils.loadAnimation(SecondaryColorLearningActivity.this, R.anim.color_blue_transition);
                        purpleMovement.reset();
                        orangeMovement.reset();
                        greenMovement.reset();
                        orange.startAnimation(orangeMovement);
                        green.startAnimation(greenMovement);
                        purple.startAnimation(purpleMovement);
                        secondaryColorViewer.setVisibility(View.VISIBLE);
                        secondaryColorViewer.setBackgroundResource(R.drawable.grey);
                        secondaryColorViewer.startAnimation(colorFadeIn);
                        break;
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondaryColorLearningActivity.this, ColorsGameActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                SecondaryColorLearningActivity.this.finish();
            }
        });
    }

    private void speakOut(){
        tts.speak(array[i], TextToSpeech.QUEUE_FLUSH, null);
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
            Intent intent =new Intent (SecondaryColorLearningActivity.this, MenuMediumActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            SecondaryColorLearningActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}