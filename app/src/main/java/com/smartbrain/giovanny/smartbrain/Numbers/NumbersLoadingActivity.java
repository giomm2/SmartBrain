package com.smartbrain.giovanny.smartbrain.Numbers;

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


public class NumbersLoadingActivity extends Activity {

    private TextToSpeech tts;
    private String text;
    private TextView txtcont,  txtadvice;
    private ImageView imgcontent;
    private int[] images={R.drawable.znumbersone,R.drawable.znumberstwo,R.drawable.znumbersthree};
    private int pos=0;
    private String[] advice={"Select the correct image quickly.","Use the repeat button.","Don't loose your hearts."};
    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
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

        contNumber.start();
        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");


        tts = new TextToSpeech(NumbersLoadingActivity.this, new TextToSpeech.OnInitListener() {

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

            Intent intent= new Intent(NumbersLoadingActivity.this, NumbersTeachActivity.class);
            // le asigno al nuevo bundle name que es nombre del usuario
            bundle.putString("NAME", name);
            intent.putExtras(bundle);
            startActivity(intent);
            NumbersLoadingActivity.this.finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contNumber.cancel();
        NumbersLoadingActivity.this.finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        contNumber.cancel();
        NumbersLoadingActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contNumber.cancel();
        NumbersLoadingActivity.this.finish();
    }
}
