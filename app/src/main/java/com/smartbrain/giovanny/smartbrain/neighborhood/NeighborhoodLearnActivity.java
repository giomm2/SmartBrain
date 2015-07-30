package com.smartbrain.giovanny.smartbrain.neighborhood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;

public class NeighborhoodLearnActivity extends Activity implements TextToSpeech.OnInitListener {
    //imagenes necesarias
    private ImageView next;
    private ImageView previous;
    private ImageView repeat;
    private ImageView playBtn;
    private ImageView imageViewer;
    //arrays que necesitare
    private int [] imageArray = {0,R.drawable.teacherf,R.drawable.teacherm,R.drawable.school1,R.drawable.policeman
            ,R.drawable.police_woman,R.drawable.police_station,R.drawable.fire_woman,R.drawable.fireman
            ,R.drawable.firestation,R.drawable.doctorf,R.drawable.doctorm,R.drawable.hospital};
    private String [] serverWords ={"hello, lets learn about the neighborhood members, please touch next to continue"
            ,"she is a teacher","and he, is a professor","they both work at the school","he is a policeman"
            ,"and she is a policewoman","they work at the police station","she is a fire-woman","and he is a fire-man"
            ,"they both work at the fire station","she is a doctor","and he is a doctor too","they treat patients, at the hospital" +
            ", if you want to play with them, touch the play controller button"};
    // tts
    private TextToSpeech tts;
    // contador
    private int counter=0;

    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood_learn);


        extras = getIntent().getExtras();
        name = extras.getString("NAME");

        // tts initializer
        tts= new TextToSpeech(this,this);
        // seteo de botones
        next = (ImageView) findViewById(R.id.next);
        previous = (ImageView) findViewById(R.id.back);
        repeat = (ImageView) findViewById(R.id.repeat);
        playBtn = (ImageView) findViewById(R.id.playBtn);
        // seteo de imagViewer
        imageViewer = (ImageView) findViewById(R.id.imageViewer);
        // funcionalidad de los botones
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter == 12)
                    counter = 12;
                else
                    counter++;
                repeat.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                switch (counter){
                    case 1:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 2:

                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 3:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 4:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 5:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 6:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 7:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 8:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 9:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 10:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 11:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 12:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        playBtn.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter ==1) {
                    counter = 1;
                    previous.setVisibility(View.INVISIBLE);
                }else
                    counter --;

                switch (counter){
                    case 1:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 2:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 3:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 4:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 5:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 6:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 7:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 8:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 9:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 10:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 11:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        break;
                    case 12:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[counter]);
                        playBtn.setVisibility(View.VISIBLE);
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
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NeighborhoodLearnActivity.this, ConmunityActivity.class);
                bundle.putString("NAME", name);
                intent.putExtras(bundle);
                startActivity(intent);
                NeighborhoodLearnActivity.this.finish();
            }
        });
    }

    public void speakOut(){
        tts.speak(serverWords[counter],TextToSpeech.QUEUE_FLUSH,null);
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
        if (tts == null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }



}
