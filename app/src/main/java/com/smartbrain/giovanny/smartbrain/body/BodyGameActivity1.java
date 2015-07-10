package com.smartbrain.giovanny.smartbrain.body;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;

public class BodyGameActivity1 extends Activity implements View.OnClickListener {

    private TextView txtcont;
    private String[] images = {"body_ear", "body_eyes","body_nose","body_mouth"};
    private String[] voice = {"Choose the Ear", "Choose the Eyes","Choose the Nose","Choose the Mouth"};
    private int[] guia={2130837570,2130837571,2130837581,2130837580};
    private ImageView img1, img2,img3,img4, imgHeart1,imgHeart2,imgHeart3;
    private Button btnnext,btnrepeat;
    private TextToSpeech tts;
    private String text,text2;
    private  int pos=0, guiaImg1,guiaImg2, guiaImg3,guiaImg4,posId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_game_activity1);
        img1=(ImageView)findViewById(R.id.image1);
        img2=(ImageView)findViewById(R.id.image2);
        img3=(ImageView)findViewById(R.id.image5);
        img4=(ImageView)findViewById(R.id.image6);
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
        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        PutImages();
        tts = new TextToSpeech(BodyGameActivity1.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Hello, Let's play. Please touch start.");

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

        while(num==num1||num==num2||num==num3||num1==num||num1==num2||num1==num3||
                num2==num||num2==num1||num2==num3||num3==num||num3==num1
                ||num3==num2){

            num=(int)(Math.random()*images.length);
            num1=(int)(Math.random()*images.length);
            num2=(int)(Math.random()*images.length);
            num3=(int)(Math.random()*images.length);

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
                if (btnnext.getText().equals("Start")){

                    btnrepeat.setEnabled(true);
                    btnnext.setText("Next");
                    btnnext.setVisibility(View.INVISIBLE);
                    contNumber.start();
                    //Las imagenes se pueden seleccionar.
                    img1.setEnabled(true);
                    img2.setEnabled(true);
                    img3.setEnabled(true);
                    img4.setEnabled(true);
                    //Llama el metodo de las imagenes Random
                    PutImages();
                    //Llama el metodo de la voz del juego
                    VoiceGame();


                }else if(btnnext.getText().equals("Next")&&pos!=4){

                    btnnext.setVisibility(View.INVISIBLE);
                    btnrepeat.setVisibility(View.VISIBLE);
                    contNumber.start();
                    //Las imagenes se pueden seleccionar.
                    img1.setEnabled(true);
                    img2.setEnabled(true);
                    img3.setEnabled(true);
                    img4.setEnabled(true);
                    //Llama el metodo de las imagenes Random
                    PutImages();
                    //Llama el metodo de la voz del juego
                    VoiceGame();
                }else{
                    Intent intent=new Intent(BodyGameActivity1.this,BodyTechActivity2.class);
                    BodyGameActivity1.this.finish();
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
            case R.id.image5:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg3);
                break;

            }
            case R.id.image6:{

                //Se envia el Id de la imagen que esta seleccionando para ser comparado
                getImageToTouch(guiaImg4);
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
            ConvertTextToSpeech("Good work!!!!, please touch next");
            btnnext.setVisibility(View.VISIBLE);
            btnrepeat.setVisibility(View.INVISIBLE);
            img1.setEnabled(false);
            img2.setEnabled(false);
            img3.setEnabled(false);
            img4.setEnabled(false);
            contNumber.cancel();
            posId++;

        }else{
            if(imgHeart1.getVisibility()==View.VISIBLE){

                imgHeart1.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Sorry, try again");

            }else if(imgHeart2.getVisibility()==View.VISIBLE){

                imgHeart2.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Sorry, try again");
            }else if(imgHeart3.getVisibility()==View.VISIBLE){

                imgHeart3.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Last chance");
            }else {

                Intent intent= new Intent(BodyGameActivity1.this,BodyGameActivity1.class);
                startActivity(intent);
                BodyGameActivity1.this.finish();

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

                Intent intent = new Intent(BodyGameActivity1.this, BodyGameActivity1.class);
                startActivity(intent);
                BodyGameActivity1.this.finish();
            }
        }
    };
}
