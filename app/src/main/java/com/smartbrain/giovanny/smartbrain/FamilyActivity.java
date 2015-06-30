package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;


public class FamilyActivity extends Activity implements TextToSpeech.OnInitListener {

    private int [] imageArray ={R.drawable.nube, R.drawable.pez,R.drawable.pezblue,R.drawable.globo,R.drawable.globo2,R.drawable.firework,R.drawable.firework2};
    private String [] familyWords= {"dad","mom","brother","sister","me","grandpa","gramdma"};
    private ImageView imageViewer;
    private Button btnNext;
    private Button playBtn;
    private TextToSpeech tts;
    private int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        tts= new TextToSpeech(this, this);
        imageViewer = (ImageView)findViewById(R.id.viewer);
        btnNext= (Button)findViewById(R.id.nextBtn);
        playBtn = (Button)findViewById(R.id.btnPlay);
        playBtn.setEnabled(false);
        imageViewer.setBackgroundResource(imageArray[i]);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == imageArray.length - 1) {
                    btnNext.setEnabled(false);
                    playBtn.setEnabled(true);
                }else {
                    i++;
                    speakOut();
                    imageViewer.setBackgroundResource(imageArray[i]);
                }
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FamilyActivity.this, FamilyActivityGame.class);
                startActivity(intent);

            }
        });
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

    private void speakOut(){
        tts.speak(familyWords[i], TextToSpeech.QUEUE_FLUSH, null);
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
}
