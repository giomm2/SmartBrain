package com.smartbrain.giovanny.smartbrain.animals;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.ArrayList;
import java.util.Locale;

public class AnimalsGameActivity extends Activity implements View.OnClickListener{

    protected static final int RESULT_SPEECH = 1;

    private ImageButton btnSpeak;
    private TextView txtText,txtcont;

    private String[] voice={"Dog","Cat","Rabbit","Cow","Horse","Hen","Sheep","Lion","Bear","Snake"};
    private int pos=0;
    private ImageView imgcontent,imgHeart1,imgHeart2,imgHeart3;
    private Button btnnext,btnrepeat;
    private TextToSpeech tts;
    private String text2;
    private String[] images={"animalsdog","animalscat","animalsrabbit","animalscow","animalshorse","animalschicken","animalssheep","animalslion","animalsbear","animalspig"};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_game);

        txtText = (TextView) findViewById(R.id.txtText);

        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        imgcontent=(ImageView)findViewById(R.id.img_content);
        btnnext=(Button)findViewById(R.id.btn_next);
        btnrepeat=(Button)findViewById(R.id.btn_repeat);
        imgHeart1=(ImageView)findViewById(R.id.heart1);
        imgHeart2=(ImageView)findViewById(R.id.heart2);
        imgHeart3=(ImageView)findViewById(R.id.heart3);
        txtcont=(TextView)findViewById(R.id.txt_cont);
        btnnext.setOnClickListener(this);
        btnrepeat.setOnClickListener(this);
        btnrepeat.setVisibility(View.INVISIBLE);
        btnSpeak.setEnabled(false);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

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
                        ConvertTextToSpeech("Hello, lets play, please touch next, then touch record");

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );

    }
    private void ConvertTextToSpeech(String voice1) {
        // TODO Auto-generated method stub
        text2 = voice1;
        //text = et.getText().toString();
        if (text2 == null || "".equals(text2)) {
            if (pos > 0) {
                text2 = voice[pos - 1];
            } else {
                text2 = voice[pos];
            }
            tts.speak(text2, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text2, TextToSpeech.QUEUE_FLUSH, null);
    }



    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btn_next:{

                if(pos==10){

                  Intent intent= new Intent(AnimalsGameActivity.this, MenuMediumActivity.class);
                    startActivity(intent);
                    AnimalsGameActivity.this.finish();
                }else {

                    int resId = getResources().getIdentifier(images[pos], "drawable", getPackageName());
                    imgcontent.setImageResource(resId);
                    ConvertTextToSpeech(voice[pos]);
                    contNumber.start();
                    pos++;
                    btnnext.setVisibility(View.INVISIBLE);
                    btnrepeat.setVisibility(View.VISIBLE);
                    btnSpeak.setEnabled(true);

                }
                break;
            }

            case R.id.btn_repeat:{

                ConvertTextToSpeech(voice[pos-1]);
                break;
            }

        }
    }

    public void CompVoice(){

        if(txtText.getText().toString().equalsIgnoreCase(voice[pos-1]) ){

            ConvertTextToSpeech("Great, please touch next");
            contNumber.cancel();
            btnnext.setVisibility(View.VISIBLE);
            btnrepeat.setVisibility(View.INVISIBLE);
            btnSpeak.setEnabled(false);

        }else{

            ConvertTextToSpeech("Sorry, try again");

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animals_game , menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));
                    CompVoice();
                }
                break;
            }

        }
    }


    //Contador del juego al acabar quita corazones o vuelve a iniciar la aplicacion.
    CountDownTimer contNumber= new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtcont.setText("" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {

            if (imgHeart1.getVisibility() == View.VISIBLE) {
                imgHeart1.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(voice[pos-1]);
                contNumber.start();

            } else if (imgHeart2.getVisibility() == View.VISIBLE) {
                imgHeart2.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(voice[pos-1]);
                contNumber.start();

            } else if (imgHeart3.getVisibility() == View.VISIBLE) {
                imgHeart3.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(voice[pos-1]);
                contNumber.start();

            } else {
                contNumber.cancel();
                AnimalsGameActivity.this.recreate();

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
    }
}
