package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;


public class WinActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
    private SeekBar sb;
    private ImageView image3,image4,image5,image6,image7,image8;
    private Button button,button1,button2,button3,image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        sb = (SeekBar) findViewById(R.id.seekBar);
        button= (Button) findViewById(R.id.imageView);
        button1= (Button) findViewById(R.id.imageButton);
        button2= (Button) findViewById(R.id.imageView2);
        button3= (Button) findViewById(R.id.imageView1);
        image3 = (ImageView) findViewById(R.id.imageView3);
        image4 = (ImageView) findViewById(R.id.imageView4);
        image5 = (ImageView) findViewById(R.id.imageView5);
        image6 = (ImageView) findViewById(R.id.imageView6);
        image7 = (ImageView) findViewById(R.id.imageView7);
        image8 = (ImageView) findViewById(R.id.imageView8);
        image9= (Button) findViewById(R.id.imageView9);
        sb.setMax(2600);
        sb.setOnSeekBarChangeListener(this);
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewDialog alert = new ViewDialog();
                alert.showDialog(WinActivity.this, "Are you sure to exit?");
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        sb.setEnabled(false);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        sb.setEnabled(false);

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        sb.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sb.setProgress(288);
        ballons();
        firework();
    }
    private void ballons(){

        Animation mov;
        mov = AnimationUtils.loadAnimation(this, R.anim.upanim);
        mov.reset();
        image4.startAnimation(mov);
        image5.startAnimation(mov);
        image6.startAnimation(mov);
    }

    private void firework()
    {
        Animation  mov;
        mov  = AnimationUtils.loadAnimation(this,R.anim.explosive_animation);
        mov.reset();
        image7.startAnimation(mov);
        image8.startAnimation(mov);
    }


}
