package com.smartbrain.giovanny.smartbrain;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class VowelLearnActivity extends Activity implements  View.OnClickListener {

    private String[] images={"a","e","i","o","u"};
    private String[] images2={"apple","","ice","orange","umbrella"};

    private Button btnnext;
    private Button btnprevious;
    private ImageView image;
    private ImageView image2;
    private int num;
    private int cont=-1;
    private TextView txtVowel;
    private MediaPlayer player;
    private TextToSpeech tts;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel_learn2);

        btnnext=(Button)findViewById(R.id.btn_next);
        image=(ImageView)findViewById(R.id.img_content);
        image2 = (ImageView)findViewById(R.id.imgApple);
        btnprevious=(Button)findViewById(R.id.btn_previous);

        txtVowel = (TextView)findViewById(R.id.textView);


        btnnext.setOnClickListener(this);
        btnprevious.setOnClickListener(this);
        num=OrderImage();
        PutImages(num);

        //set back sound
        player = MediaPlayer.create(VowelLearnActivity.this, R.raw.music);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();

        tts = new TextToSpeech(VowelLearnActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("A like an apple");
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });
    }


    private int OrderImage(){

        if (cont != images.length-1) {
            cont++;
        }else{


            cont=0;
        }


        return cont;


    }

    private void PutImages(int imgs){

        int resId=getResources().getIdentifier(images[imgs], "drawable", getPackageName());
        image.setImageResource(resId);

        int resId2=getResources().getIdentifier(images2[imgs], "drawable",getPackageName());
        image2.setImageResource(resId2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vowel_learn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next: {


                num = OrderImage();
                PutImages(num);

                voice();


                break;

            }
            case R.id.btn_previous:{
                voice();
            }

        }
    }

    @Override

    public void onStart(){

        super.onStart();
        letras();


    }
    private void letras(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.bubble);
        movn.reset();
        image.startAnimation(movn);



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

    public void voice(){

        if(text.equals("A like an apple")){

            ConvertTextToSpeech("E like an elephant");
        }else if (text.equals("E like an elephant")){

            ConvertTextToSpeech("I   like an ice cream");

        }else if (text.equals("I   like an ice cream")){

            ConvertTextToSpeech("O   like an orange");

        }else if (text.equals("O   like an orange")){

            ConvertTextToSpeech("U   like an umbrella");

        }else{
            ConvertTextToSpeech("A like an apple");
        }
    }
    private void ConvertTextToSpeech(String voice) {
        // TODO Auto-generated method stub
        text=voice;
        //text = et.getText().toString();
        if (text == null || "".equals(text)) {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
