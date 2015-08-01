package com.smartbrain.giovanny.smartbrain.shapes;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class ShapesActivityMain extends Activity implements  View.OnClickListener{
    private String[] images={"cu","cir","tr","rec"};
    private String[] speak={"Square.","Circle.","Triangle.","Rectangle."};
    private ImageView image;
    private int num;
    private int num1;
    private int cont=-1;
    private TextToSpeech tts;
    private String text;
    private Button btnnext;
    private Button btnPrevious;
    private Button btnPlay;

    //trae el nombre del usuario
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_activity_main);


        image=(ImageView)findViewById(R.id.image);

        btnnext=(Button)findViewById(R.id.btn_next);
        btnPrevious=(Button)findViewById(R.id.btn_previous);
        btnPlay=(Button)findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnnext.setOnClickListener(this);

        num=OrderImage();
        PutImages(num);

        //seteo en name el nombre que viene en extras que es un bundle
        extras = getIntent().getExtras();
        name = extras.getString("NAME");


        tts = new TextToSpeech(ShapesActivityMain.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                //TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Square.");
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });
    }


    private int OrderImage1() {
        if (cont != images.length - 1 || cont != -1) {
            cont--;
        } else {
            cont = 0;
        }
        return cont;

    }
    private int OrderImage(){

        if (cont != images.length-1) {
            cont++;
        }else{
            cont=0;
        }
        return cont;
    }

    private void PutImages(int imgs){

        int resId=getResources().getIdentifier(images[imgs], "drawable", getPackageName());
        image.setImageResource(resId);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shapes_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next: {


                num = OrderImage();
                PutImages(num);

                Voice();
                break;

            }
            case R.id.btn_previous:{
                num1 = OrderImage1();
                PutImages(num1);
                VoiceRepeat();
                break;
            }
            case R.id.btn_play:{
                Intent intent = new Intent(ShapesActivityMain.this,ShapesActivity.class);
                startActivity(intent);
                ShapesActivityMain.this.finish();
                break;
            }
        }

    }

    public void Voice(){
        if(cont==0){
            ConvertTextToSpeech(speak[cont]);
            btnPrevious.setVisibility(View.INVISIBLE);
            btnPlay.setVisibility(View.VISIBLE);
        }
        else {
            ConvertTextToSpeech(speak[cont]);
            btnPrevious.setVisibility(View.VISIBLE);
        }

    }
    public void VoiceRepeat(){

        if(cont==0){
            ConvertTextToSpeech(speak[cont]);
            btnPrevious.setVisibility(View.INVISIBLE);
        }
        else {
            ConvertTextToSpeech(speak[cont]);
        }

    }
    public void onStart(){

        super.onStart();
        btnPrevious.setVisibility(View.INVISIBLE);


    }

}
