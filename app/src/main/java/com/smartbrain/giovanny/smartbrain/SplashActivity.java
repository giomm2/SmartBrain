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

import org.json.JSONException;
import org.json.JSONObject;


public class SplashActivity extends Activity {

    private Thread mSplashThread;
    private RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String uniqueDevice = "";
        params = new RequestParams();
        uniqueDevice = getUniqueDevice();
        params.put("uniquedevice",uniqueDevice);
        invokeWebService();



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

    public String getUniqueDevice(){
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String idDevice = telephonyManager.getDeviceId().toString();
        return idDevice;
    }

    public void invokeWebService(){
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://162.219.247.87/device/getiddevice", params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject();
                    // When the JSON response has status boolean value assigned with true
                    obj.getString(response);
                    Toast.makeText(SplashActivity.this, obj.toString(),Toast.LENGTH_SHORT);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                client.get("http://162.219.247.87/device/dodeviceregister", params, new AsyncHttpResponseHandler() {
                    // When the response returned by REST has Http response code '200'
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(SplashActivity.this, "inserte", Toast.LENGTH_SHORT);
                    }

                    // When the response returned by REST has Http response code other than '200'
                    @Override
                    public void onFailure(int statusCode, Throwable error, String content) {
                        // Hide Progress Dialog
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else {
                            Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}
