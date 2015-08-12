package com.smartbrain.giovanny.smartbrain.practice;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
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
import com.smartbrain.giovanny.smartbrain.R;
import com.smartbrain.giovanny.smartbrain.ComnunityWinActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class PracticeActivity extends Activity implements View.OnClickListener{
    protected static final int RESULT_SPEECH = 1;

    private ImageButton btn_speak;
    private TextView txtVoice;

    private String[] voice={"A","Bear","Cat","Hen","Cow","Dog","Horse","Lion","Snake",
    "Rabbit","Sheep","Arm","Ear","Eyes","Face","Finger","Foot","Hand","Leg","Mouth","Nose",
    "Brother","Brown","Circle","Blue","Green","Orange","Purple","Red","Yellow","Square","Dad","Doctor","Doctor",
    "E","Fireman","Grandmother","Grandfather","grey","I","Mom","8","5","4","9","1","7","6","3","2","0","O",
    "Olive","Policeman","Rectangle","Sister","Teacher","Triangle","U","Wine"};
    private String[] image={"a","animalsbear","animalscat","animalschicken","animalscow","animalsdog",
            "animalshorse","animalslion","animalspig","animalsrabbit","animalssheep","body_arm","body_ear","body_eyes",
            "body_face","body_finger","body_foot","body_hand","body_leg","body_mouth","body_nose","brother",
            "brown","cir","colorblue","colorgreen","colororange","colorpurple","colorred","coloryellow",
            "cu","dad","doctorf","doctorm","e","fireman","grandma","grandpa","grey","i","mom","numbers_eight",
            "numbers_five","numbers_four","numbers_nine","numbers_one","numbers_seven","numbers_six","numbers_three",
            "numbers_two","numbers_zero","o","olive","policeman","rec","sister","teacherm","tr","u","wine"
    };

    private int cont=0;
    private int [] numbers = new int[60];

    private Button btnStart;
    private Button btnRepeat;
    private TextToSpeech tts;
    private String text;
    private ImageView container;
    private TextView txtcont;

    private ImageView life1;
    private ImageView life2;
    private ImageView life3;

    // trae el usuario
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    int gamePoints=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        btn_speak=(ImageButton)findViewById(R.id.btnSpeak);
        btnStart = (Button)findViewById(R.id.btn_start);
        container = (ImageView)findViewById(R.id.img_content);
        btnStart.setOnClickListener(this);
        btnRepeat=(Button)findViewById(R.id.btn_repeat);
        btnRepeat.setOnClickListener(this);
        life1 = (ImageView)findViewById(R.id.heart);
        life2 = (ImageView)findViewById(R.id.heart1);
        life3 = (ImageView)findViewById(R.id.heart2);

        txtVoice = (TextView) findViewById(R.id.txtText);


        random();
        txtcont=(TextView)findViewById(R.id.txtTimer);
        btnRepeat.setVisibility(View.INVISIBLE);

        //seteo en name el nombre que viene en extras que es un bundle
        extras = getIntent().getExtras();
        name = extras.getString("NAME");

        btn_speak.setEnabled(false);

        btn_speak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtVoice.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        tts = new TextToSpeech(PracticeActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Welcome to practice, for play please press the blue button.");

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practice, menu);
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
        switch (v.getId()){


            case R.id.btn_repeat:{
                ConvertTextToSpeech(voice[numbers[cont-1]]);
                break;
            }
            case R.id.btn_start:{

                if(cont==9){

                    Intent intent = new Intent(PracticeActivity.this, ComnunityWinActivity.class);
                    bundle.putString("NAME", name);
                    bundle.putInt("POINTS", gamePoints);
                    intent.putExtras(bundle);
                    PracticeActivity.this.finish();
                    startActivity(intent);
                }else {
                    timer.start();
                    int resId = getResources().getIdentifier(image[numbers[cont]], "drawable", getPackageName());
                    container.setImageResource(resId);
                    ConvertTextToSpeech(voice[numbers[cont]]);
                    btnStart.setVisibility(View.INVISIBLE);
                    btnStart.setEnabled(false);
                    btn_speak.setEnabled(true);
                    btnRepeat.setEnabled(true);
                    btnRepeat.setVisibility(View.VISIBLE);
                    txtVoice.setText("");

                    cont++;


                }
                break;
            }

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

    public void compare(){

        if(txtVoice.getText().toString().equalsIgnoreCase(voice[numbers[cont-1]]) ){

            ConvertTextToSpeech("Well done, for next please press the blue button.");
            btnStart.setEnabled(true);
            btn_speak.setEnabled(false);
            btnStart.setVisibility(View.VISIBLE);
            btnRepeat.setVisibility(View.INVISIBLE);
            timer.cancel();
            gamePoints=gamePoints+110;

        }else{

            ConvertTextToSpeech("Sorry, try again");

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtVoice.setText(text.get(0));
                    compare();
                }
                break;
            }

        }
    }

    /////////////////////////////////////////////////////////////
    public void random(){

        Random rand = new Random();
        int random;
        int j=0;
        int con=0;
        boolean flag=true;
        boolean flag0=true;
        for(int i=-1; i<=8; i++){
            random=rand.nextInt(60);
            con=0;
            j=0;
            flag=true;
            if(random!=0){
                while(numbers[j]!=random && flag==true ){
                    j++;
                    con++;
                    if(j==9){
                        flag=false;
                    }
                }
                if(con==9){
                    numbers[i+1]=random;
                }else{

                    i=i-1;
                }
            }else{
                if(flag0==true){
                    numbers[i+1]=0;
                    flag0=false;
                }
                else{
                    i=i-1;
                }
            }
        }
    }

    CountDownTimer timer = new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long ms = millisUntilFinished;
            String text = String.format("%02d",
                    TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
            txtcont.setText(text);

        }

        @Override
        public void onFinish() {
            if(life1.getVisibility()==View.VISIBLE){
                life1.setVisibility(View.INVISIBLE);
                timer.start();
                ConvertTextToSpeech("Try again." +voice[numbers[cont-1]]);
                if(gamePoints==0){
                    gamePoints=0;

                }
                else{
                    gamePoints=gamePoints+60;
                }
            }
            else if(life2.getVisibility()==View.VISIBLE){
                life2.setVisibility(View.INVISIBLE);
                timer.start();
                ConvertTextToSpeech("Try again."+voice[numbers[cont-1]]);
                if(gamePoints==0){
                    gamePoints=0;

                }
                else{
                    gamePoints=gamePoints-60;
                }
            }

            else if (life3.getVisibility()==View.VISIBLE){
                life3.setVisibility(View.INVISIBLE);
                timer.start();
                ConvertTextToSpeech("Last chance, try again." + voice[numbers[cont - 1]]);
                if(gamePoints==0){
                    gamePoints=0;

                }
                else{
                    gamePoints=gamePoints-60;
                }
            }
            else {

                PracticeActivity.this.recreate();
            }
        }


    };

    @Override
    protected void onStart() {
        super.onStart();
        btnRepeat.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
        timer.cancel();
    }
}
