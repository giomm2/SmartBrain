package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


public class MenuEasyActivity extends Activity {

    private ImageView fishblue;
    private ImageView fishorange;
    private ImageView bubble1;
    private ImageView bfamily;
    private ImageView bvowel;
    private ImageView bbody;
    MediaPlayer player;
    //todos los url
    private String  url =  "http://abcsoft.esy.es/grupoBurbujas.png";
    private String  url2="http://abcsoft.esy.es/pezAzul.png";
    private String  url3="http://abcsoft.esy.es/pez.png";
    private String  url4="http://abcsoft.esy.es/burbujaFamily.png";
    private String  url5="http://abcsoft.esy.es/bubbleVowel.png";
    private String  url6="http://abcsoft.esy.es/bubbleBody.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_easy);

        fishblue=(ImageView)findViewById(R.id.imgblue);
        fishorange=(ImageView)findViewById(R.id.imgorange);
        bubble1=(ImageView)findViewById(R.id.imgbubble);
        bfamily=(ImageView)findViewById(R.id.btnfamily);
        bvowel=(ImageView)findViewById(R.id.btnvowel);
        bbody=(ImageView)findViewById(R.id.btnbody);

//En cada   ImageDownloader()   descargamos una  imagen
        new ImageDownloader().execute(url);
        new ImageDownloader1().execute(url2);
        new ImageDownloader2().execute(url3);
        new ImageDownloader3().execute(url4);
        new ImageDownloader4().execute(url5);
        new ImageDownloader5().execute(url6);

        player = MediaPlayer.create(MenuEasyActivity.this, R.raw.underwater);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();

    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            bubble1.setImageBitmap(result);

        }
    }

    private class ImageDownloader1 extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            //Llamamos  el  metodo  downloadBitmap  que es  el  que se  encarga  de  descargar   la  imagen
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            fishblue.setImageBitmap(result);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    private class ImageDownloader2 extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            fishorange.setImageBitmap(result);


        }

    }
    private class ImageDownloader3 extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {

            return downloadBitmap(params[0]);
        }


        @Override
        protected void onPostExecute(Bitmap result) {

            bfamily.setImageBitmap(result);


        }

    }
    private class ImageDownloader4 extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bvowel.setImageBitmap(result);
        }

    }

    private class ImageDownloader5 extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {

            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bbody.setImageBitmap(result);

        }

    }
    //Este  metodo  es  el  que se  encarga  de  descargar   la  imagen
    public Bitmap downloadBitmap(String url) {
        final DefaultHttpClient client = new DefaultHttpClient();
        final HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();

                try {


                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    return bitmap;


                } finally {
                    if (inputStream != null) {

                        inputStream.close();
                    }

                    entity.consumeContent();
                }
            }

        } catch (Exception e) {

            getRequest.abort();
        }

        return null;
    }


  /*  @Override

    public void onStart(){

        super.onStart();
        leftFish();
        upBubble();
        bubble();

    }*/
    //Documente  este    metodo  para    que se  den  cuenta   pues    da  error   porque  apenas   se
    //inicia la  actividad  no   esta   las imagenes cargadas    en   su  totalidad.

    @Override

    public void onResume(){

        super.onResume();
        player = MediaPlayer.create(MenuEasyActivity.this, R.raw.underwater);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();
        leftFish();
        upBubble();
        bubble();
    }


    private void leftFish(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.leftanim);
        movn.reset();
        fishblue.startAnimation(movn);
        fishorange.startAnimation(movn);

    }

    private void upBubble(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.upanim);
        movn.reset();
        bubble1.startAnimation(movn);


    }

    private void bubble(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.bubble);
        movn.reset();
        bbody.startAnimation(movn);
        bvowel.startAnimation(movn);
        bfamily.startAnimation(movn);
    }


}

