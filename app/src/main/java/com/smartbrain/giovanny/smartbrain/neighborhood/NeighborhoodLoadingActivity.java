package com.smartbrain.giovanny.smartbrain.neighborhood;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbrain.giovanny.smartbrain.ComnunityWinActivity;
import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class NeighborhoodLoadingActivity extends Activity {

    private TextToSpeech tts;
    private String text;
    private TextView txtcont,  txtadvice;
    private ImageView imgcontent;
    private int[] images={R.drawable.zcomunidadone,R.drawable.zcomunidadtwo,R.drawable.zcomunidadthree};
    private int pos=0;
    private String[] advice={"Pay close attention to the instructions.","Touch the character, and then drag it.","If you loose all of yours hearts, you loose the game."};
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

        contNumber.start();
        extras = getIntent().getExtras();
        name = extras.getString("NAME");


        tts = new TextToSpeech(NeighborhoodLoadingActivity.this, new TextToSpeech.OnInitListener() {

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

            if(cbskip.isChecked()){

                Intent intent= new Intent(NeighborhoodLoadingActivity.this, ConmunityActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                NeighborhoodLoadingActivity.this.finish();

            }else{
            Intent intent= new Intent(NeighborhoodLoadingActivity.this, NeighborhoodLearnActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            NeighborhoodLoadingActivity.this.finish();
        }}
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contNumber.cancel();
        NeighborhoodLoadingActivity.this.finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        contNumber.cancel();
        NeighborhoodLoadingActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contNumber.cancel();
        NeighborhoodLoadingActivity.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (NeighborhoodLoadingActivity.this, MenuMediumActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            NeighborhoodLoadingActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
