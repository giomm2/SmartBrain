package com.smartbrain.giovanny.smartbrain.animals;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;

public class AnimalsLearningActivity extends Activity implements TextToSpeech.OnInitListener {


    // array and counter for the array
    private String[] animalArray={"Hi, lets Learn about the animals. Please tab next","Dog","Cat","Rabbit","Cow"
            ,"Horse","Chicken","Pig","sheep","Lion","Bear"};
    private int imageArray[]={0,R.drawable.animalsdog,R.drawable.animalscat,R.drawable.animalsrabbit
            ,R.drawable.animalscow,R.drawable.animalshorse,R.drawable.animalschicken,R.drawable.animalspig
            ,R.drawable.animalssheep,R.drawable.animalslion,R.drawable.animalsbear};
    private int animalCount =0;
    // views
    private Button play;
    private Button repeat;
    private Button next;
    private Button back;
    private ImageView animalViewer;
    // TTS
    private TextToSpeech tts;
    //animaciones
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_learning);

        // seteo de Views
        play = (Button)findViewById(R.id.play);
        repeat = (Button)findViewById(R.id.repeat);
        next = (Button)findViewById(R.id.next);
        back = (Button)findViewById(R.id.back);
        animalViewer = (ImageView) findViewById(R.id.animalViewer);
        repeat.setEnabled(false);
        //animacion
        animation = AnimationUtils.loadAnimation(AnimalsLearningActivity.this, R.anim.fade_in_animation);

        // tts
        tts = new TextToSpeech(this, this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeat.setEnabled(true);
                if(animalCount !=10)
                    animalCount++;
                else {
                    animalCount = 10;
                    play.setVisibility(View.VISIBLE);
                }
                switch (animalCount){
                    case 1:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 2:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 3:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 4:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        speakOut();
                        break;
                    case 5:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 6:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 7:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 8:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 9:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 10:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animalCount ==0)
                    animalCount = 0;
                else if(animalCount ==1)
                    animalCount =1;
                else
                    animalCount --;

                switch (animalCount){
                    case 1:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 2:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 3:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 4:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 5:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 6:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 7:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 8:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 9:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                    case 10:
                        animalViewer.setBackgroundResource(imageArray[animalCount]);
                        animalViewer.startAnimation(animation);
                        speakOut();
                        break;
                }
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
    }

    // text that will be speak out
    public void speakOut(){
        tts.speak(animalArray[animalCount],TextToSpeech.QUEUE_FLUSH,null);
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
    protected void onDestroy() {
        if(tts != null){
            tts.stop();
        }
    }
}