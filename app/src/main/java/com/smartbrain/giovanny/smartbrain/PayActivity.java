package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PayActivity extends Activity {

    private Button btnpay,btnexit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        btnexit=(Button)findViewById(R.id.btn_exit);
        btnpay=(Button)findViewById(R.id.btn_pay);

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               PayActivity.this.finish();
            }
        });


    }

}
