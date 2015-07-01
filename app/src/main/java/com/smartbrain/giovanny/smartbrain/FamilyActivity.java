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

    //arrays que me jalan las imagenes y lo que debe decir TTS
    private int [] imageArray ={R.drawable.familytree, R.drawable.dad,R.drawable.mom,R.drawable.brother,
            R.drawable.sister,R.drawable.grandpa,R.drawable.grandma};
    private String [] familyWords= {"Lets learn about the family members","first this is dad","and mom","you may have a brother",
            "or a sister","and this is the grandpa","and the gramdma"};
    //views
    private ImageView imageViewer;
    private Button btnNext;
    private Button playBtn;
    // declaracion de TTS y contador para los arrays
    private TextToSpeech tts;
    private int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        // declaro TTS con un contexto nuevo que es en el q esta.
        tts= new TextToSpeech(this, this);
        imageViewer = (ImageView)findViewById(R.id.viewer);
        btnNext= (Button)findViewById(R.id.nextBtn);
        playBtn = (Button)findViewById(R.id.btnPlay);
        playBtn.setEnabled(false);
        imageViewer.setBackgroundResource(imageArray[i]);

        // cada vez que le doy a next aumento el contador que me recorre los arrays y cambio de frases y de imagenes

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

    // necesario para poner a "hablar" al TTS y llamar a un metodo llamado speakOut solo si hay datos que hablar
    //y si se soporta el lenguaje seteado
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

    // le dice que hablar, son todas las variables que estan en el array
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
