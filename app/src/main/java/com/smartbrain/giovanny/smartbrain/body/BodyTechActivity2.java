package com.smartbrain.giovanny.smartbrain.body;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.MenuEasyActivity;
import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;

public class BodyTechActivity2 extends Activity implements View.OnClickListener {

    private String[] images = {"body_face", "body_arm","body_hand","body_finger","body_leg","body_foot"};
    private Button btnnext,btnagain,btnplay;
    private ImageView image;
    private int cont = -1,num;
    private String text;
    private TextToSpeech tts;

    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    int points;
    private String paymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_tech_activity2);


        btnnext = (Button) findViewById(R.id.btn_next);
        image = (ImageView) findViewById(R.id.img_content);
        btnagain=(Button)findViewById(R.id.btn_repeat);
        btnplay=(Button)findViewById(R.id.btn_play);
        btnnext.setOnClickListener(this);
        btnagain.setOnClickListener(this);
        btnplay.setOnClickListener(this);

        num = OrderImage();
        PutImages(num);
        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");
        points=extras.getInt("POINTS2");


        tts = new TextToSpeech(BodyTechActivity2.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Face");
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });


    }

    private int OrderImage() {

        if (cont != images.length - 1) {
            cont++;
        } else {


            cont = 0;
        }
        return cont;
    }

    //Coloca la imagen en el ImageView
    private void PutImages(int imgs) {

        int resId = getResources().getIdentifier(images[imgs], "drawable", getPackageName());
        image.setImageResource(resId);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //Al seleccionar le boton next, realiza el cambio de la imagen y la voz
            case R.id.btn_next: {
                num = OrderImage();
                PutImages(num);
                Voice();
                break;
            }
            case R.id.btn_repeat:{
                VoiceRepeat();
                break;
            }
            case R.id.btn_play:{

                Intent intent = new Intent(BodyTechActivity2.this,BodyGameActivity2.class);
                bundle.putInt("POINTS2",points);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                BodyTechActivity2.this.finish();
                startActivity(intent);
                break;

            }

        }}
    //Metodo que coloca la voz deacuerdo a la imagen.
    public void Voice(){

        if(text.equals("Face")){

            ConvertTextToSpeech("Arm");
        }else if (text.equals("Arm")){

            ConvertTextToSpeech("Hand");

        }else if (text.equals("Hand")){

            ConvertTextToSpeech("Finger");

        }else if (text.equals("Finger")){

            ConvertTextToSpeech("Leg");

        }else if (text.equals("Leg")){

            ConvertTextToSpeech("Foot");

        }
        else{
            ConvertTextToSpeech("Face");
            btnplay.setVisibility(View.VISIBLE);
        }}

    //Metodo que permite la repeticion de la voz
    public void VoiceRepeat(){

        if(text.equals("Face")){

            ConvertTextToSpeech("Face");
        }else if (text.equals("Arm")){

            ConvertTextToSpeech("Arm");

        }else if (text.equals("Hand")){

            ConvertTextToSpeech("Hand");

        }
        else if (text.equals("Finger")){

            ConvertTextToSpeech("Finger");

        }else if (text.equals("Leg")){

            ConvertTextToSpeech("Leg");

        }
        else{

            ConvertTextToSpeech("Foot");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (BodyTechActivity2.this, MenuEasyActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            BodyTechActivity2.this.finish();
            tts.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
