package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class PayActivity extends Activity {

    private String name;
    private String idDevice;
    private EditText cardNumber;
    private EditText month;
    private EditText year;
    private EditText ccv;
    Bundle bundle = new Bundle();
    Bundle extras;

    private Button btnpay,btnexit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        extras = getIntent().getExtras();
        btnexit=(Button)findViewById(R.id.btn_exit);
        btnpay=(Button)findViewById(R.id.btn_pay);
        cardNumber = (EditText) findViewById(R.id.editText);
        month = (EditText) findViewById(R.id.editText2);
        year = (EditText) findViewById(R.id.editText3);
        ccv = (EditText) findViewById(R.id.editText4);
        idDevice = getUniqueDevice();
        name = extras.getString("NAME");

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.finish();
            }
        });

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardNumber.getText().toString().equals("") || year.getText().toString().equals("") || month.getText().toString().equals("") || ccv.getText().toString().equals("")) {
                    Toast.makeText(PayActivity.this, "Cant leave blank spaces", Toast.LENGTH_SHORT).show();
                } else {
                    RequestParams params = new RequestParams();
                    params.put("name", name);
                    params.put("device", idDevice);
                    invokeWS(params);
                }
            }
        });


    }

    public String getUniqueDevice() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String idDevice = telephonyManager.getDeviceId().toString();
        return idDevice;
    }

    public void invokeWS(RequestParams params) {
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://162.219.247.87/status/doupdatestatus", params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        bundle.putString("PAYMENT", obj.getString("paymentStatus"));
                        bundle.putString("NAME", obj.getString("user"));
                        bundle.putInt("POINTS", obj.getInt("points"));
                        Intent intent = new Intent(PayActivity.this, MenuEasyActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        PayActivity.this.finish();

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
