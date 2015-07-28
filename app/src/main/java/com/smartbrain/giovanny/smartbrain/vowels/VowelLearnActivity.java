package com.smartbrain.giovanny.smartbrain.vowels;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class VowelLearnActivity extends Activity implements  View.OnClickListener {

    private String[] images={"a","e","i","o","u"};
    private String[] images2={"apple","elephant","ice","orange","umbrella"};
    private String[] speak={"A like an apple","E like an elephant","I like an ice cream","O like an orange","U like an umbrella"};
    private int num1;

    private Button btnnext;
    private ImageView image;
    private ImageView image2;
    private int num;
    private int cont=-1;
    private TextView txtVowel;
    private MediaPlayer player;
    private TextToSpeech tts;
    private String text;
    private Button btnPlay;
    private Button btnpasar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel_learn2);


        btnnext=(Button)findViewById(R.id.btn_next);
        image=(ImageView)findViewById(R.id.img_content);
        image2 = (ImageView)findViewById(R.id.imgApple);
        btnpasar=(Button)findViewById(R.id.btn_previous);
        btnPlay=(Button)findViewById(R.id.btn_play);


        txtVowel = (TextView)findViewById(R.id.textView);

        btnpasar.setOnClickListener(this);
        btnnext.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        num = OrderImage();
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

    private int OrderImage1() {
        if (cont != images.length - 1 || cont != -1) {
            cont--;
        } else {
            cont = 0;
        }
        return cont;

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

                Voice();


                break;

            }
            case R.id.btn_previous:{
                num1 = OrderImage1();
                PutImages(num1);
                VoiceRepeat();
                break;
            }
            case R.id.btn_play:{

                Intent intent = new Intent(VowelLearnActivity.this,ActivityPuzzle.class);
                startActivity(intent);
                VowelLearnActivity.this.finish();
                break;

            }


        }
    }

    @Override

    public void onStart(){

        super.onStart();
        letras();
        btnpasar.setVisibility(View.INVISIBLE);


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

    public void Voice(){
        if(cont==0){
            ConvertTextToSpeech(speak[cont]);
            btnpasar.setVisibility(View.INVISIBLE);
            btnPlay.setVisibility(View.VISIBLE);
        }
        else {
            ConvertTextToSpeech(speak[cont]);
            btnpasar.setVisibility(View.VISIBLE);
        }

    }
    public void VoiceRepeat(){

        if(cont==0){
            ConvertTextToSpeech(speak[cont]);
            btnpasar.setVisibility(View.INVISIBLE);
        }
        else {
            ConvertTextToSpeech(speak[cont]);
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
