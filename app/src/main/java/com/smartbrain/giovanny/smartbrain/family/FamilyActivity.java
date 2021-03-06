package com.smartbrain.giovanny.smartbrain.family;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;


import com.smartbrain.giovanny.smartbrain.MenuEasyActivity;
import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class FamilyActivity extends Activity implements TextToSpeech.OnInitListener {

    //arrays que me jalan las imagenes y lo que debe decir TTS
    private int [] imageArray ={R.drawable.familytree, R.drawable.dad,R.drawable.mom,R.drawable.brother,
            R.drawable.sister,R.drawable.grandpa,R.drawable.grandma};
    private String[] familyWords = {"Lets learn about the family members, please touch the blue arrow", "first, this is dad", "and mom", "you may have a brother",
            "or a sister", "and this is the grandpa", "and the gramdma. touch the control to play with the family members"};
    //views
    private ImageView imageViewer;
    private ImageView btnNext;
    private ImageView playBtn;
    private ImageView repeat;
    // declaracion de TTS y contador para los arrays
    private TextToSpeech tts;
    private int i =0;
    private String paymentStatus;
    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        // declaro TTS con un contexto nuevo que es en el q esta.
        tts= new TextToSpeech(this, this);
        imageViewer = (ImageView)findViewById(R.id.viewer);
        btnNext= (ImageView)findViewById(R.id.nextBtn);
        playBtn = (ImageView)findViewById(R.id.btnPlay);
        imageViewer.setBackgroundResource(imageArray[i]);
        repeat = (ImageView)findViewById(R.id.repeat);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");

        // cada vez que le doy a next aumento el contador que me recorre los arrays y cambio de frases y de imagenes

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    i++;
                switch (i) {
                    case 1:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[i]);
                        break;
                    case 2:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[i]);
                        break;
                    case 3:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[i]);
                        break;
                    case 4:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[i]);
                        break;
                    case 5:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[i]);
                        break;
                    case 6:
                        speakOut();
                        imageViewer.setBackgroundResource(imageArray[i]);
                        i = 0;
                        playBtn.setVisibility(View.VISIBLE);
                        break;
                }
                }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyActivity.this, FamilyActivityGame.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                FamilyActivity.this.finish();

            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (FamilyActivity.this, MenuEasyActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            FamilyActivity.this.finish();
            tts.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

}
