package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.parsers.SAXParserFactory;


public class SplashActivity extends Activity {

    private Thread mSplashThread;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        final SplashActivity sPlashScreen = this;
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(8000);
                    }
                }
                catch(InterruptedException ex){
                }
                finish();
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, MainActivity.class);
                startActivity(intent);
            }
        };
        mSplashThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if(evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
