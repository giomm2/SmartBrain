package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button playButton;
    private EditText username;
    ProgressDialog prgDialog;
    private ImageView imgg1;
    private ImageView imgg2;
    private ImageView imgg3;
    private ImageView imgg4;
    private ImageView nube1;
    private ImageView nube2;

    private Button bnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton= (Button)findViewById(R.id.btn_play);
        username = (EditText)findViewById(R.id.txt_name);
        imgg1=(ImageView)findViewById(R.id.imageg1);
        imgg2=(ImageView)findViewById(R.id.imageg2);
        imgg3=(ImageView)findViewById(R.id.imageg3);
        imgg4=(ImageView)findViewById(R.id.imageg4);
        nube1=(ImageView)findViewById(R.id.nub1);
        nube2=(ImageView)findViewById(R.id.nub2);


        bnext=(Button)findViewById(R.id.button);
        bnext.setOnClickListener(this);


        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String points = "0";
                String idMail = "1";
                RequestParams params = new RequestParams();
                if (username.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Can´t leave name in blank", Toast.LENGTH_SHORT).show();
                } else {
                    params.put("name", name);
                    params.put("points", points);
                    params.put("idmail", idMail);
                    // Invoke RESTful Web Service with Http parameters
                    prgDialog.show();
                    // Make RESTful webservice call using AsyncHttpClient object
                    invokeWS(params);


                }

            }

        });



    }

    public void invokeWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://162.219.247.87/register/doregister", params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                prgDialog.hide();
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        // Set Default Values for Edit View controls

                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                    }
                    // Else display error message
                    else {

                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                prgDialog.hide();
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


    @Override

    protected void onStart() {

        super.onStart();
        upBallons();
        rightCloud();
        leftCloud();

    }


    @Override

    protected void onResume() {

        super.onResume();

        upBallons();
        rightCloud();
        leftCloud();

    }

   private void rightCloud(){
       Animation movn;
       movn = AnimationUtils.loadAnimation(this, R.anim.rightanim);
       movn.reset();
       nube1.startAnimation(movn);

   }

    private void leftCloud(){

        Animation movn;
        movn = AnimationUtils.loadAnimation(this, R.anim.leftanim);
        movn.reset();
        nube2.startAnimation(movn);

    }

    private void upBallons(){

        Animation mov;
        mov = AnimationUtils.loadAnimation(this, R.anim.upanim);
        mov.reset();
        imgg1.startAnimation(mov);
        imgg2.startAnimation(mov);
        imgg3.startAnimation(mov);
        imgg4.startAnimation(mov);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button:{

                Intent intent= new Intent(this,MenuEasyActivity.class);
                startActivity(intent);


            }

        }
    }
}
