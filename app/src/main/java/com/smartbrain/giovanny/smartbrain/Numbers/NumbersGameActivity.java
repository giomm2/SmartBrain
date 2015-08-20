package com.smartbrain.giovanny.smartbrain.Numbers;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.ComnunityWinActivity;
import com.smartbrain.giovanny.smartbrain.R;
import java.util.Locale;

public class NumbersGameActivity extends Activity implements View.OnClickListener {
    private TextView txtcont;
    private String[] images = {"numbers_zero", "numbers_one", "numbers_two", "numbers_three", "numbers_four", "numbers_five", "numbers_six"
            , "numbers_seven", "numbers_eight", "numbers_nine"};
    private String[] voice = {"Choose number zero", "Choose number one", "Choose number two", "Choose number three", "Choose number four",
            "Choose number five", "Choose number six", "Choose number seven", "Choose number eight", "Choose number nine"};
    private int guiaImg0, guiaImg1, guiaImg2, guiaImg3, guiaImg4, guiaImg5, guiaImg6, guiaImg7, guiaImg8, guiaImg9, pos = 0, posId = 0;
    private ImageView downImg0, downImg1, downImg2, downImg3, downImg4, downImg5, downImg6, downImg7, downImg8, downImg9;
    private ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, imageHeart1, imageHeart2, imageHeart3;
    private Button btnstar,btnrepeat;
    private TextToSpeech tts;
    private String text, text2;
    private int[] guia = {2130837666,2130837661,2130837665,2130837664,2130837659,2130837658,2130837663,2130837662,2130837657,2130837660};

    // points
    int points = 0;

    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_game);

        txtcont = (TextView) findViewById(R.id.txt_temp);
        btnstar = (Button) findViewById(R.id.btn_start);
        btnrepeat=(Button)findViewById(R.id.btn_repeat);
        downImg0 = (ImageView) findViewById(R.id.img_cont1);
        downImg1 = (ImageView) findViewById(R.id.img_cont2);
        downImg2 = (ImageView) findViewById(R.id.img_cont3);
        downImg3 = (ImageView) findViewById(R.id.img_cont4);
        downImg4 = (ImageView) findViewById(R.id.img_cont5);
        downImg5 = (ImageView) findViewById(R.id.img_cont6);
        downImg6 = (ImageView) findViewById(R.id.img_cont7);
        downImg7 = (ImageView) findViewById(R.id.img_cont8);
        downImg8 = (ImageView) findViewById(R.id.img_cont9);
        downImg9 = (ImageView) findViewById(R.id.img_cont10);
        imageHeart1=(ImageView)findViewById(R.id.img_heart1);
        imageHeart2=(ImageView)findViewById(R.id.img_heart2);
        imageHeart3=(ImageView)findViewById(R.id.img_heart3);
        img0 = (ImageView) findViewById(R.id.img_zero);
        img1 = (ImageView) findViewById(R.id.img_one);
        img2 = (ImageView) findViewById(R.id.img_two);
        img3 = (ImageView) findViewById(R.id.img_three);
        img4 = (ImageView) findViewById(R.id.img_four);
        img5 = (ImageView) findViewById(R.id.img_five);
        img6 = (ImageView) findViewById(R.id.img_six);
        img7 = (ImageView) findViewById(R.id.img_seven);
        img8 = (ImageView) findViewById(R.id.img_eight);
        img9 = (ImageView) findViewById(R.id.img_nine);
        btnstar.setOnClickListener(this);
        btnrepeat.setOnClickListener(this);
        btnrepeat.setEnabled(false);
        downImg0.setOnClickListener(this);
        downImg1.setOnClickListener(this);
        downImg2.setOnClickListener(this);
        downImg3.setOnClickListener(this);
        downImg4.setOnClickListener(this);
        downImg5.setOnClickListener(this);
        downImg6.setOnClickListener(this);
        downImg7.setOnClickListener(this);
        downImg8.setOnClickListener(this);
        downImg9.setOnClickListener(this);
        txtcont.setVisibility(View.INVISIBLE);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");


        tts = new TextToSpeech(NumbersGameActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Hello, Let's play. to play please press the blue button.");

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


    public void PutImages() {

        int num = (int) (Math.random() * images.length);
        int num1 = (int) (Math.random() * images.length);
        int num2 = (int) (Math.random() * images.length);
        int num3 = (int) (Math.random() * images.length);
        int num4 = (int) (Math.random() * images.length);
        int num5 = (int) (Math.random() * images.length);
        int num6 = (int) (Math.random() * images.length);
        int num7 = (int) (Math.random() * images.length);
        int num8 = (int) (Math.random() * images.length);
        int num9 = (int) (Math.random() * images.length);

        while (num == num1 || num == num2 || num == num3 || num == num4 || num == num5 || num == num6 ||
                num == num7 || num == num8 || num == num9 || num1 == num || num1 == num2 || num1 == num3 || num1 == num4 || num1 == num5 || num1 == num6 ||
                num1 == num7 || num1 == num8 || num1 == num9 || num2 == num || num2 == num1 || num2 == num3 || num2 == num4 || num2 == num5 || num2 == num6 ||
                num2 == num7 || num2 == num8 || num2 == num9 || num3 == num || num3 == num1 || num3 == num2 || num3 == num4 || num3 == num5 || num3 == num6 ||
                num3 == num7 || num3 == num8 || num3 == num9 || num4 == num || num4 == num1 || num4 == num2 || num4 == num3 || num4 == num5 || num4 == num6 ||
                num4 == num7 || num4 == num8 || num4 == num9 || num5 == num || num5 == num1 || num5 == num2 || num5 == num3 || num5 == num4 || num5 == num6 ||
                num5 == num7 || num5 == num8 || num5 == num9 || num6 == num || num6 == num1 || num6 == num2 || num6 == num3 || num6 == num4 || num6 == num5 ||
                num6 == num7 || num6 == num8 || num6 == num9 || num7 == num || num7 == num1 || num7 == num2 || num7 == num3 || num7 == num4 || num7 == num5 ||
                num7 == num6 || num7 == num8 || num7 == num9 || num8 == num || num8 == num1 || num8 == num2 || num8 == num3 || num8 == num4 || num8 == num5 ||
                num8 == num6 || num8 == num7 || num8 == num9 || num9 == num || num9 == num1 || num9 == num2 || num9 == num3 || num9 == num4 || num9 == num5 ||
                num9 == num6 || num9 == num7 || num9 == num8) {

            num = (int) (Math.random() * images.length);
            num1 = (int) (Math.random() * images.length);
            num2 = (int) (Math.random() * images.length);
            num3 = (int) (Math.random() * images.length);
            num4 = (int) (Math.random() * images.length);
            num5 = (int) (Math.random() * images.length);
            num6 = (int) (Math.random() * images.length);
            num7 = (int) (Math.random() * images.length);
            num8 = (int) (Math.random() * images.length);
            num9 = (int) (Math.random() * images.length);

        }

        int resId = getResources().getIdentifier(images[num], "drawable", getPackageName());
        downImg0.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg0 = resId;

        resId = getResources().getIdentifier(images[num1], "drawable", getPackageName());
        downImg1.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg1 = resId;

        resId = getResources().getIdentifier(images[num2], "drawable", getPackageName());
        downImg2.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg2 = resId;

        resId = getResources().getIdentifier(images[num3], "drawable", getPackageName());
        downImg3.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg3 = resId;

        resId = getResources().getIdentifier(images[num4], "drawable", getPackageName());
        downImg4.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg4 = resId;

        resId = getResources().getIdentifier(images[num5], "drawable", getPackageName());
        downImg5.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg5 = resId;
        resId = getResources().getIdentifier(images[num6], "drawable", getPackageName());
        downImg6.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg6 = resId;
        resId = getResources().getIdentifier(images[num7], "drawable", getPackageName());
        downImg7.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg7 = resId;

        resId = getResources().getIdentifier(images[num8], "drawable", getPackageName());
        downImg8.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg8 = resId;
        resId = getResources().getIdentifier(images[num9], "drawable", getPackageName());
        downImg9.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg9 = resId;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_start: {

                if (pos<10){
                    PutImages();
                downImg0.setVisibility(View.INVISIBLE);
                downImg1.setVisibility(View.INVISIBLE);
                downImg2.setVisibility(View.INVISIBLE);
                downImg3.setVisibility(View.INVISIBLE);
                downImg4.setVisibility(View.INVISIBLE);
                downImg5.setVisibility(View.INVISIBLE);
                downImg6.setVisibility(View.INVISIBLE);
                downImg7.setVisibility(View.INVISIBLE);
                downImg8.setVisibility(View.INVISIBLE);
                downImg9.setVisibility(View.INVISIBLE);
                contNumber.start();
                VoiceGame();
                btnrepeat.setEnabled(true);
                downImg1.setEnabled(true);
                downImg2.setEnabled(true);
                downImg3.setEnabled(true);
                downImg4.setEnabled(true);
                downImg5.setEnabled(true);
                downImg6.setEnabled(true);
                downImg7.setEnabled(true);
                downImg8.setEnabled(true);
                downImg9.setEnabled(true);
                downImg0.setEnabled(true);
                btnstar.setVisibility(View.INVISIBLE);
            }
                else{

                    Intent intent = new Intent(NumbersGameActivity.this, ComnunityWinActivity.class);
                    bundle.putString("NAME", name);
                    bundle.putInt("POINTS", points);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    NumbersGameActivity.this.finish();
                }
                break;
            }
            case R.id.btn_repeat:{
                ConvertTextToSpeech(voice[pos-1]);
                contNumber.start();
                break;

            }case R.id.img_cont1:{

                getImageToTouch(guiaImg0);
                break;
            }case R.id.img_cont2:{

                getImageToTouch(guiaImg1);
                break;
            }case R.id.img_cont3:{

                getImageToTouch(guiaImg2);
                break;
            }
            case R.id.img_cont4:{

                getImageToTouch(guiaImg3);
                break;
            }
            case R.id.img_cont5:{

                getImageToTouch(guiaImg4);
                break;
            }
            case R.id.img_cont6:{

                getImageToTouch(guiaImg5);
                break;
            }
            case R.id.img_cont7:{

                getImageToTouch(guiaImg6);
                break;
            }
            case R.id.img_cont8:{

                getImageToTouch(guiaImg7);
                break;
            }
            case R.id.img_cont9:{

                getImageToTouch(guiaImg8);
                break;
            }case R.id.img_cont10:{

                getImageToTouch(guiaImg9);
                break;
            }

        }
    }


    private void VoiceGame (){
        ConvertTextToSpeech(voice[pos]);
        pos++;
    }


    //   Metodo que compara la imagen seleccionada con el sonido emitido
    private void getImageToTouch(int numguia) {


        if (numguia == guia[pos-1]) {
            ConvertTextToSpeech("Good work!!!!, please press the blue button");

            downImg1.setEnabled(false);
            downImg2.setEnabled(false);
            downImg3.setEnabled(false);
            downImg4.setEnabled(false);
            downImg5.setEnabled(false);
            downImg6.setEnabled(false);
            downImg7.setEnabled(false);
            downImg8.setEnabled(false);
            downImg9.setEnabled(false);
            downImg0.setEnabled(false);
            btnrepeat.setEnabled(false);
            contNumber.cancel();
            points=points+35;
            btnstar.setVisibility(View.VISIBLE);


            if(img0.getVisibility()==View.INVISIBLE){

                img0.setVisibility(View.VISIBLE);

            }else if(img1.getVisibility()==View.INVISIBLE){

                img1.setVisibility(View.VISIBLE);

            }else if(img2.getVisibility()==View.INVISIBLE){

                img2.setVisibility(View.VISIBLE);

            }else if(img3.getVisibility()==View.INVISIBLE){

                img3.setVisibility(View.VISIBLE);

            }else if(img4.getVisibility()==View.INVISIBLE){

                img4.setVisibility(View.VISIBLE);

            }else if(img5.getVisibility()==View.INVISIBLE){

                img5.setVisibility(View.VISIBLE);

            }else if(img6.getVisibility()==View.INVISIBLE){

                img6.setVisibility(View.VISIBLE);

            }else if(img7.getVisibility()==View.INVISIBLE){

                img7.setVisibility(View.VISIBLE);

            }else if(img8.getVisibility()==View.INVISIBLE){

                img8.setVisibility(View.VISIBLE);

            }else{

                img9.setVisibility(View.VISIBLE);
            }


        } else {
            if (imageHeart1.getVisibility() == View.VISIBLE) {

                imageHeart1.setVisibility(View.INVISIBLE);
                contNumber.cancel();
                contNumber.start();
                if (points==0){

                    points=0;
                }else {
                    points = points - 25;
                }
                ConvertTextToSpeech("Sorry, try again");

            } else if (imageHeart2.getVisibility() == View.VISIBLE) {

                imageHeart2.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Sorry, try again");
                contNumber.cancel();
                contNumber.start();
                if (points==0){

                    points=0;
                }else {
                    points = points - 25;
                }
            } else if (imageHeart3.getVisibility() == View.VISIBLE) {
                imageHeart3.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Last chance");
                contNumber.cancel();
                contNumber.start();
                if (points==0){

                    points=0;
                }else {
                    points = points - 25;
                }
            }else{

               contNumber.cancel();
                NumbersGameActivity.this.recreate();
            }
        }
    }

    CountDownTimer contNumber = new CountDownTimer(11000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtcont.setText("" + millisUntilFinished / 1000);

            if(txtcont.getText().equals("10")){
                downImg0.setVisibility(View.INVISIBLE);
                downImg1.setVisibility(View.INVISIBLE);
                downImg2.setVisibility(View.INVISIBLE);
                downImg3.setVisibility(View.INVISIBLE);
                downImg4.setVisibility(View.INVISIBLE);
                downImg5.setVisibility(View.INVISIBLE);
                downImg6.setVisibility(View.INVISIBLE);
                downImg7.setVisibility(View.INVISIBLE);
                downImg8.setVisibility(View.INVISIBLE);
                downImg9.setVisibility(View.INVISIBLE);

                downImg0.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("9")){

                downImg0.setVisibility(View.INVISIBLE);
                downImg1.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("8")){

                downImg1.setVisibility(View.INVISIBLE);
                downImg2.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("7")){

                downImg2.setVisibility(View.INVISIBLE);
                downImg3.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("6")){

                downImg3.setVisibility(View.INVISIBLE);
                downImg4.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("5")){

                downImg4.setVisibility(View.INVISIBLE);
                downImg5.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("4")){

                downImg5.setVisibility(View.INVISIBLE);
                downImg6.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("3")){

                downImg6.setVisibility(View.INVISIBLE);
                downImg7.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("2")){

                downImg7.setVisibility(View.INVISIBLE);
                downImg8.setVisibility(View.VISIBLE);
            }else if(txtcont.getText().equals("1")){

                downImg8.setVisibility(View.INVISIBLE);
                downImg9.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFinish() {

            if (imageHeart1.getVisibility() == View.VISIBLE) {
                imageHeart1.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(text2);

                if(points!=0){
                    points=points-25;

                }
                contNumber.start();


            } else if (imageHeart2.getVisibility() == View.VISIBLE) {
                imageHeart2.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(text2);
                if(points!=0){
                    points=points-25;

                }
                contNumber.start();


            } else if (imageHeart3.getVisibility() == View.VISIBLE) {
                imageHeart3.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Last chance");
                if(points!=0){
                    points=points-25;

                }
                contNumber.start();

            }else{

                contNumber.cancel();
                NumbersGameActivity.this.finish();

            }
        }


    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
        contNumber.cancel();
    }
}
