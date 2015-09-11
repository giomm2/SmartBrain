package com.smartbrain.giovanny.smartbrain.animals;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;
import com.smartbrain.giovanny.smartbrain.body.LoadingActivity;

import java.util.Locale;


public class AnimalsLoadingActivity extends Activity {

    private TextToSpeech tts;
    private String text;
    private TextView txtcont,txtview2;
    private int pos=0;
    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private CheckBox cbskip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        txtcont=(TextView)findViewById(R.id.txt_cont);
        cbskip=(CheckBox)findViewById(R.id.cb_exit);
        txtview2=(TextView)findViewById(R.id.textView2);
        contNumber.start();
        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();



        tts = new TextToSpeech(AnimalsLoadingActivity.this, new TextToSpeech.OnInitListener() {

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
    CountDownTimer contNumber= new CountDownTimer(7000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtcont.setText("" + millisUntilFinished / 1000);
            if(txtcont.getText().toString().equals("5")) {
                txtview2.setTextColor(Color.parseColor("#FFFF1A28"));
            }else if (txtcont.getText().toString().equals("4")){

                txtview2.setTextColor(Color.parseColor("#FF8AFF23"));
            }else if (txtcont.getText().toString().equals("3")){

                txtview2.setTextColor(Color.parseColor("#FF3799FF"));
            }
            else if (txtcont.getText().toString().equals("2")){

                txtview2.setTextColor(Color.parseColor("#FFF94CFF"));
            }
        }

        @Override
        public void onFinish() {

            if(cbskip.isChecked()){

                Intent intent= new Intent(AnimalsLoadingActivity.this, AnimalsGameActivity.class);
                // le asigno al nuevo bundle name que es nombre del usuario
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                AnimalsLoadingActivity.this.finish();
            }else{

            Intent intent= new Intent(AnimalsLoadingActivity.this, AnimalsLearningActivity.class);
            // le asigno al nuevo bundle name que es nombre del usuario
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            AnimalsLoadingActivity.this.finish();}
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contNumber.cancel();
        AnimalsLoadingActivity.this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        contNumber.cancel();
        AnimalsLoadingActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contNumber.cancel();
        AnimalsLoadingActivity.this.finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
           Intent intent =new Intent (AnimalsLoadingActivity.this, MenuMediumActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            AnimalsLoadingActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
