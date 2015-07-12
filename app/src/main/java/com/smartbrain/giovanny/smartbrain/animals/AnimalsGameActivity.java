package com.smartbrain.giovanny.smartbrain.animals;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;

public class AnimalsGameActivity extends Activity implements View.OnClickListener{

    private String[] voice={"Dog","Cat","Rabbit","Cow","Horse","Chicken","Pig","sheep","Lion","Bear"};
    private int pos=0;
    private ImageView imgcontent;
    private Button btnnext,btnrepeat;
    private TextToSpeech tts;
    private String text;
    private String[] images={"animalsdog","animalscat","animalsrabbit","animalscow","animalshorse","animalschicken","animalspig","animalssheep","animalslion","animalsbear"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_game);

        imgcontent=(ImageView)findViewById(R.id.img_content);
        btnnext=(Button)findViewById(R.id.btn_next);
        btnrepeat=(Button)findViewById(R.id.btn_repeat);
        btnnext.setOnClickListener(this);
        btnrepeat.setOnClickListener(this);


        tts = new TextToSpeech(AnimalsGameActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Hello, lets play");

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );



    }
    private void ConvertTextToSpeech(String voice1) {
        // TODO Auto-generated method stub
        text = voice1;
        //text = et.getText().toString();
        if (text == null || "".equals(text)) {
            if (pos > 0) {
                text = voice[pos - 1];
            } else {
                text = voice[pos];
            }
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }



    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btn_next:{

                if(pos==10){

                    pos=0;
                }else {

                    int resId = getResources().getIdentifier(images[pos], "drawable", getPackageName());
                    imgcontent.setImageResource(resId);
                    ConvertTextToSpeech(voice[pos]);
                    pos++;

                }
                break;
            }

            case R.id.btn_repeat:{

                ConvertTextToSpeech(voice[pos-1]);
                break;
            }

        }
    }

}
