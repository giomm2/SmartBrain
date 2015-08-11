package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class ColorsWinActivity extends Activity {

    private ImageView image3, image4, image5, image6, image7, image8;
    private Button button, button1, button2, button3, image9;
    private TextView username;
    // variable que necesito para el id
    private String id;
    // bundle nuevo y viejo
    Bundle bundle = new Bundle();
    Bundle extras;
    private int points;
    // variables para el web service
    private String user;
    private int pointsForWS;
    private String deviceID;
    private MediaPlayer player;
    private String paymentStatus;


    // getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPointsForWS(int pointsForWS) {
        this.pointsForWS = pointsForWS;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // set back sound
        player = MediaPlayer.create(ColorsWinActivity.this, R.raw.fireflies);
        player.setLooping(true);
        player.start();

        button = (Button) findViewById(R.id.imageView);
        button1 = (Button) findViewById(R.id.imageButton);
        button2 = (Button) findViewById(R.id.imageView2);
        button3 = (Button) findViewById(R.id.imageView1);
        image3 = (ImageView) findViewById(R.id.imageView3);
        image4 = (ImageView) findViewById(R.id.imageView4);
        image5 = (ImageView) findViewById(R.id.imageView5);
        image6 = (ImageView) findViewById(R.id.imageView6);
        image7 = (ImageView) findViewById(R.id.imageView7);
        image8 = (ImageView) findViewById(R.id.imageView8);
        image9 = (Button) findViewById(R.id.imageView9);
        username = (TextView) findViewById(R.id.userName);
        //bundle viejo
        extras = getIntent().getExtras();
        username.setText(extras.getString("NAME"));
        setUser(extras.getString("NAME"));
        points = extras.getInt("POINTS");
        button1.setText("" + points);

        // llamada al web Service que me hace el update de puntos.
        deviceID = getUniqueDevice();
        RequestParams params = new RequestParams();
        params.put("device", deviceID);
        params.put("name", getUser());
        params.put("points", "" + points);
        invokeWS(params);


        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ColorsWinActivity.this, MenuMediumActivity.class);
                bundle.putString("NAME", getUser());
                bundle.putInt("POINTS", pointsForWS);
                bundle.putString("PAYMENT", paymentStatus);
                intent.putExtras(bundle);
                startActivity(intent);
                player.stop();
                ColorsWinActivity.this.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ballons();
        firework();
        ViewDialog alert = new ViewDialog();
        alert.showDialog(ColorsWinActivity.this, "Are you sure to exit?");

    }

    private void ballons() {

        Animation mov;
        mov = AnimationUtils.loadAnimation(this, R.anim.upanim);
        mov.reset();
        image4.startAnimation(mov);
        image5.startAnimation(mov);
        image6.startAnimation(mov);
    }

    private void firework() {
        Animation mov;
        mov = AnimationUtils.loadAnimation(this, R.anim.explosive_animation);
        mov.reset();
        image7.startAnimation(mov);
        image8.startAnimation(mov);
    }

    public String getUniqueDevice() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String idDevice = telephonyManager.getDeviceId().toString();
        setId(idDevice);
        return idDevice;
    }

    public void invokeWS(RequestParams params) {
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://162.219.247.87/updater/doupdate", params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        // Set Default Values for Edit View controls
                        setUser(obj.getString("user"));
                        setPointsForWS(obj.getInt("points"));
                        paymentStatus = obj.getString("paymentStatus");
                        button2.setText("" + obj.getInt("points"));
                        // Display successfully registered message using Toast
                        //Toast.makeText(getApplicationContext(), "Hi " + getUser() + ", your points have been updated!", Toast.LENGTH_LONG).show();
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


}
