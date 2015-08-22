package com.smartbrain.giovanny.smartbrain.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.HardMenuActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class PracticeLoadingActivity extends Activity {

    private TextToSpeech tts;
    private String text;
    private TextView txtcont,  txtadvice;
    private ImageView imgcontent;
    private int[] images={R.drawable.zpracticeone,R.drawable.zpracticetwo,R.drawable.zpracticethree};
    private int pos=0;
    private String[] advice={"You have sixty seconds to record yourself, hurry up!.","Touch the blue button when you are ready to talk.","Check your pronunciation before."};
    //trae el nombre del usuario
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    private CheckBox cbskip;
    private String paymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        txtcont=(TextView)findViewById(R.id.txt_cont);
        txtadvice=(TextView)findViewById(R.id.txt_advice);
        imgcontent=(ImageView)findViewById(R.id.img_cont);
        cbskip=(CheckBox)findViewById(R.id.cb_exit);

        cbskip.setVisibility(View.INVISIBLE);

        contNumber.start();

        //seteo en name el nombre que viene en extras que es un bundle
        extras = getIntent().getExtras();
        name = extras.getString("NAME");



        tts = new TextToSpeech(PracticeLoadingActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("");

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );
    }

    private void ConvertTextToSpeech(String voice1) {
        // TODO Auto-generated method stub
        text=voice1;
        //text = et.getText().toString();
        if (text == null || "".equals(text)) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    //Contador del juego al acabar quita corazones o vuelve a iniciar la aplicacion.
    CountDownTimer contNumber= new CountDownTimer(15000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            txtcont.setText("" + millisUntilFinished / 1000);

            if (txtcont.getText().equals("14")){

                imgcontent.setImageResource(images[pos]);
                txtadvice.setText(advice[pos]);
                pos++;
            }else if (txtcont.getText().equals("10")){

                imgcontent.setImageResource(images[pos]);
                txtadvice.setText(advice[pos]);
                pos++;

            }else if (txtcont.getText().equals("5")){

                imgcontent.setImageResource(images[pos]);
                txtadvice.setText(advice[pos]);
                pos++;

            }
        }

        @Override
        public void onFinish() {

            Intent intent= new Intent(PracticeLoadingActivity.this, PracticeActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            PracticeLoadingActivity.this.finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contNumber.cancel();
        PracticeLoadingActivity.this.finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        contNumber.cancel();
        PracticeLoadingActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contNumber.cancel();
        PracticeLoadingActivity.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (PracticeLoadingActivity.this, HardMenuActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            PracticeLoadingActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
