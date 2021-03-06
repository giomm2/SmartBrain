package com.smartbrain.giovanny.smartbrain.body;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.MenuEasyActivity;
import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;
import com.smartbrain.giovanny.smartbrain.WinActivity;

import java.util.Locale;

public class BodyGameActivity2 extends Activity implements View.OnClickListener{

    private TextView txtcont;
    private String[] images = {"body_face", "body_arm","body_hand","body_finger","body_leg","body_foot"};
    private String[] voice = {"Choose the face", "Choose the arm","Choose the hand","Choose the finger","Choose the leg","Choose the foot"};
    private int[] guia={2130837583,2130837580,2130837586,2130837584,2130837588,2130837585};
    private ImageView img1, img2,img3,img4,img5,img6, imgHeart1,imgHeart2,imgHeart3;
    private Button btnnext,btnrepeat;
    private TextToSpeech tts;
    private String text,text2;
    private  int pos=0, guiaImg1,guiaImg2, guiaImg3,guiaImg4,guiaImg5,guiaImg6,posId=0;
    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    private int points=0;
    private String paymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_game_activity2);

        img1=(ImageView)findViewById(R.id.image1);
        img2=(ImageView)findViewById(R.id.image2);
        img3=(ImageView)findViewById(R.id.image3);
        img4=(ImageView)findViewById(R.id.image4);
        img5=(ImageView)findViewById(R.id.image5);
        img6=(ImageView)findViewById(R.id.image6);
        btnnext=(Button)findViewById(R.id.btn_next);
        imgHeart1=(ImageView)findViewById(R.id.heart1);
        imgHeart2=(ImageView)findViewById(R.id.heart2);
        imgHeart3=(ImageView)findViewById(R.id.heart3);
        btnrepeat=(Button)findViewById(R.id.btn_repeat);
        txtcont=(TextView)findViewById(R.id.txt_cont);
        btnnext.setOnClickListener(this);
        btnrepeat.setOnClickListener(this);
        btnrepeat.setEnabled(false);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);

        PutImages();

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");
        points=extras.getInt("POINTS2");
        tts = new TextToSpeech(BodyGameActivity2.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Hello, Let's play. To play please press the blue button.");

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );
    }

    //Poner imagenes Random en cada ImageView sin que se repita.
    public void PutImages() {

        int num1=(int)(Math.random()*images.length);
        int num=(int)(Math.random()*images.length);
        int num2=(int)(Math.random()*images.length);
        int num3=(int)(Math.random()*images.length);
        int num4=(int)(Math.random()*images.length);
        int num5=(int)(Math.random()*images.length);

        while(num==num1||num==num2||num==num3||num==num4||num==num5||num1==num2||num1==num3||num1==num4||
                num1==num5||num2==num3||num2==num4||num2==num5||num3==num4||num3==num5||num4==num5){

            num=(int)(Math.random()*images.length);
            num1=(int)(Math.random()*images.length);
            num2=(int)(Math.random()*images.length);
            num3=(int)(Math.random()*images.length);
            num4=(int)(Math.random()*images.length);
            num5=(int)(Math.random()*images.length);

        }

        int resId = getResources().getIdentifier(images[num], "drawable", getPackageName());
        img1.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg1=resId;

        resId = getResources().getIdentifier(images[num1], "drawable", getPackageName());
        img2.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg2=resId;
        resId = getResources().getIdentifier(images[num2], "drawable", getPackageName());
        img3.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg3=resId;
        resId = getResources().getIdentifier(images[num3], "drawable", getPackageName());
        img4.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg4=resId;

        resId = getResources().getIdentifier(images[num4], "drawable", getPackageName());
        img5.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg5=resId;

        resId = getResources().getIdentifier(images[num5], "drawable", getPackageName());
        img6.setImageResource(resId);
        //Se toma el ID de cada  imagen para luego usarla para la comparacion.
        guiaImg6=resId;

    }
    private void ConvertTextToSpeech(String voice1) {
        // TODO Auto-generated method stub
        text=voice1;
        //text = et.getText().toString();
        if (text == null || "".equals(text)) {
            if(pos>0){
                text = voice[pos-1];}
            else{
                text=voice[pos];
            }
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //En el caso que sea seleccionado el boton next
            case R.id.btn_next:{

                //Se cambia el nombre del boton y se pone invisible para iniciar
                if (pos<6){

                    btnrepeat.setEnabled(true);
                    btnrepeat.setVisibility(View.VISIBLE);
                    btnnext.setVisibility(View.INVISIBLE);
                    contNumber.start();
                    //Las imagenes se pueden seleccionar.
                    img1.setEnabled(true);
                    img2.setEnabled(true);
                    img3.setEnabled(true);
                    img4.setEnabled(true);
                    img5.setEnabled(true);
                    img6.setEnabled(true);
                    //Llama el metodo de las imagenes Random
                    PutImages();
                    //Llama el metodo de la voz del juego
                    VoiceGame();



                }else{

                    Intent intent=new Intent(BodyGameActivity2.this,WinActivity.class);
                    bundle.putString("NAME", name);
                    bundle.putInt("POINTS", points);
                    intent.putExtras(bundle);
                    BodyGameActivity2.this.finish();
                    startActivity(intent);

                }
                break;

            }

            case R.id.image1:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg1);
                break;

            }
            case R.id.image2:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg2);
                break;

            }
            case R.id.image3:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg3);
                break;

            }
            case R.id.image4:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg4);
                break;

            }
            case R.id.image5:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg5);
                break;

            }
            case R.id.image6:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg6);
                break;

            }

            case R.id.btn_repeat:{

                VoiceRepeat();
                break;
            }

        }


    }

    //Metodo que cambia las voces del juego cuando se da start
    private void VoiceGame (){

        String play = voice[pos];
        ConvertTextToSpeech(play);
        pos++;


    }

    private void VoiceRepeat(){

        text2=voice[pos-1];
        ConvertTextToSpeech(text2);

    }


    //   Metodo que compara la imagen seleccionada con el sonido emitido
    private void getImageToTouch(int numguia){


        if (numguia==guia[posId]) {
            int time =Integer.parseInt(txtcont.getText().toString());
            ConvertTextToSpeech("Good work!!!!,please press the blue button.");
            btnnext.setVisibility(View.VISIBLE);
            btnrepeat.setVisibility(View.INVISIBLE);
            img1.setEnabled(false);
            img2.setEnabled(false);
            img3.setEnabled(false);
            img4.setEnabled(false);
            img5.setEnabled(false);
            img6.setEnabled(false);
            contNumber.cancel();
            points=points+25;

            if(time>=20){

                points=points+15;
            }

            posId++;

        }else{
            if(imgHeart1.getVisibility()==View.VISIBLE){

                imgHeart1.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Sorry, try again");

                if(points==0){
                    points=0;

                }else{

                    points=points-25;
                }

            }else if(imgHeart2.getVisibility()==View.VISIBLE){

                imgHeart2.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Sorry, try again");
                if(points==0){
                    points=0;

                }else{

                    points=points-25;
                }
            }else if(imgHeart3.getVisibility()==View.VISIBLE){

                imgHeart3.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Last chance");
                if(points==0){
                    points=0;

                }else{

                    points=points-25;
                }
            }
            else {

                contNumber.cancel();
                BodyGameActivity2.this.recreate();
            }

        }


    }

    //Contador del juego al acabar quita corazones o vuelve a iniciar la aplicacion.
    CountDownTimer contNumber= new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtcont.setText("" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {

            if (imgHeart1.getVisibility() == View.VISIBLE) {
                imgHeart1.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(text2);
                contNumber.start();

            } else if (imgHeart2.getVisibility() == View.VISIBLE) {
                imgHeart2.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(text2);
                contNumber.start();

            } else if (imgHeart3.getVisibility() == View.VISIBLE) {
                imgHeart3.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech(text2);
                contNumber.start();

            } else {

                contNumber.cancel();
                BodyGameActivity2.this.recreate();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
        contNumber.cancel();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (BodyGameActivity2.this, MenuEasyActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            BodyGameActivity2.this.finish();
            tts.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
