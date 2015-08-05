package com.smartbrain.giovanny.smartbrain;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Giovanny on 5/8/2015.
 */
public class ToastExitActivity extends Activity{


    public void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_toast);
        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        Button dialogButton1 = (Button) dialog.findViewById(R.id.btn_dialog1);
        Button exit=(Button)dialog.findViewById(R.id.btn_no);
        ImageView img=(ImageView) dialog.findViewById(R.id.a);
        img.setImageResource(R.drawable.door);
        exit.setVisibility(View.VISIBLE);
        dialog.show();
        dialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);

            }
        });

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

    }
}
