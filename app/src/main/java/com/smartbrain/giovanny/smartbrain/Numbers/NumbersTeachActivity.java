package com.smartbrain.giovanny.smartbrain.Numbers;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.HardMenuActivity;
import com.smartbrain.giovanny.smartbrain.MenuMediumActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;

public class NumbersTeachActivity extends Activity implements View.OnClickListener{

    private Button btnnext,btnplay,btnrepeat;
    private ImageView imgconten;
    private String[] images={"numbers_zero","numbers_one","numbers_two","numbers_three","numbers_four","numbers_five","numbers_six"
            ,"numbers_seven" ,"numbers_eight","numbers_nine"};
    private TextToSpeech tts;
    private String text;
    private int pos,cont=-1;
    private String[] voice={"This is number zero","This is number one","This is number two","This is number three","This is number four",
            "This is number five", "This is number six", "This is number seven","This is number eight","This is number nine"};



    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    private String paymentStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_teach);
        btnnext=(Button)findViewById(R.id.btn_next);
        btnplay=(Button)findViewById(R.id.btn_play);
        btnrepeat=(Button)findViewById(R.id.btn_repeat);
        imgconten=(ImageView)findViewById(R.id.img_content);


        btnnext.setOnClickListener(this);
        btnplay.setOnClickListener(this);
        btnrepeat.setOnClickListener(this);

        pos=OrderImage();
        PutImages(pos);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");

        tts = new TextToSpeech(NumbersTeachActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech(voice[0]);

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );


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


    //Ordena las imagenes deacuerdo al arreglo.
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
        imgconten.setImageResource(resId);

    }

    //Metodo que coloca la voz deacuerdo a la imagen.
    public void Voice(){
        if(pos!=0){
            if(text.equals(voice[pos-1])){

                ConvertTextToSpeech(voice[pos]);
            }}else{

            ConvertTextToSpeech(voice[pos]);
            btnplay.setVisibility(View.VISIBLE);
        }

    }
    //Metodo que permite la repeticion de la voz
    public void VoiceRepeat(){

        if(text.equals(voice[pos])){

            ConvertTextToSpeech(voice[pos]);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:{

                pos=OrderImage();
                PutImages(pos);
                Voice();

                break;
            }
            case R.id.btn_repeat:{

                VoiceRepeat();
                break;
            }

            case R.id.btn_play:{
                Intent intent=new Intent(NumbersTeachActivity.this,NumbersGameActivity.class);
                bundle.putString("NAME", extras.getString("NAME"));
                bundle.putInt("POINTS", extras.getInt("POINTS"));
                bundle.putString("PAYMENT", extras.getString("PAYMENT"));
                intent.putExtras(bundle);
                startActivity(intent);
                NumbersTeachActivity.this.finish();

                break;
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent =new Intent (NumbersTeachActivity.this, HardMenuActivity.class);
            bundle.putString("NAME", extras.getString("NAME"));
            bundle.putInt("POINTS", extras.getInt("POINTS"));
            bundle.putString("PAYMENT", extras.getString("PAYMENT"));
            intent.putExtras(bundle);
            startActivity(intent);
            NumbersTeachActivity.this.finish();
            tts.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

}
