package com.smartbrain.giovanny.smartbrain.colors;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class ColorsLoadingActivity extends Activity {

    private TextToSpeech tts;
    private String text;
    private TextView txtcont,  txtadvice;
    private ImageView imgcontent;
    private int[] images={R.drawable.zcolorsone,R.drawable.zcolorstwo,R.drawable.zcolorsthree};
    private int pos=0;
    private String[] advice={"You have six opportunities.","You have to answer five times to win.","You can touch or drag the colors."};
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        txtcont=(TextView)findViewById(R.id.txt_cont);
        txtadvice=(TextView)findViewById(R.id.txt_advice);
        imgcontent=(ImageView)findViewById(R.id.img_cont);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");



        tts = new TextToSpeech(ColorsLoadingActivity.this, new TextToSpeech.OnInitListener() {

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
        contNumber.start();
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

            Intent intent= new Intent(ColorsLoadingActivity.this, ColorLearningActivity.class);
            bundle.putString("NAME", name);
            intent.putExtras(bundle);
            startActivity(intent);
            ColorsLoadingActivity.this.finish();
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        contNumber.cancel();
        ColorsLoadingActivity.this.finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        contNumber.cancel();
        ColorsLoadingActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contNumber.cancel();
        ColorsLoadingActivity.this.finish();
    }
}
