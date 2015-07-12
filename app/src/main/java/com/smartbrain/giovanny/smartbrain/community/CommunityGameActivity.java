package com.smartbrain.giovanny.smartbrain.community;


import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbrain.giovanny.smartbrain.R;

/**
 * Created by Cryven on 11/07/2015.
 */
public class CommunityGameActivity extends Activity implements DialogInterface.OnClickListener {

    LinearLayout layoutRight;
    LinearLayout layoutRight2;
    LinearLayout layoutLeft;
    LinearLayout layoutLeft2;
    TextView txtContent;
    MediaPlayer ambulance;
    MediaPlayer fireTruck;
    MediaPlayer school;
    MediaPlayer police;
    ImageView fireStation;
    ImageView policeStation;
    ImageView school1;
    ImageView hospital;


    protected void viewSetter() {

        findViewById(R.id.policeman).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.policewoman).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.firewoman).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.fireman).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.doctorM).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.doctorF).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.policeman).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.teacherF).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.teacherM).setOnTouchListener(new MyTouchListener());

    }



    public void onClick(View v, DragEvent event) {
        switch (v.getId()) {
            case R.id.fireStation: {

                fireTruck.start();
                break;
            }

            case R.id.school: {

                school.start();
                break;
            }

            case R.id.policestation: {

                police.start();
                break;
            }
            case R.id.hospital: {

                ambulance.start();
                break;
            }
        }
    }

    /**
     * This method will be invoked when a button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     * @param which  The button that was clicked (e.g.
     *               {@link DialogInterface#BUTTON1}) or the position
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    // Esta clase se encarga de buscar la accion construir una sombra de lo que hacemos drag y darle un movimiento mediante
    // MotionEvent a la imagen

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunity_members);
        viewSetter();
        layoutLeft = (LinearLayout) findViewById(R.id.layoutLeft);
        layoutLeft2 = (LinearLayout) findViewById(R.id.layoutLeft2);
        layoutRight = (LinearLayout) findViewById(R.id.layoutRight);
        layoutRight2 = (LinearLayout) findViewById(R.id.layoutRight2);
        hospital = (ImageView) findViewById(R.id.hospital);
        school1 = (ImageView) findViewById(R.id.school);
        fireStation = (ImageView) findViewById(R.id.fireStation);
        policeStation = (ImageView) findViewById(R.id.policestation);

        police = MediaPlayer.create(this, R.raw.police);
        fireTruck = MediaPlayer.create(this, R.raw.firetruck);
        ambulance = MediaPlayer.create(this, R.raw.ambulance);
        school = MediaPlayer.create(this, R.raw.school);

    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DROP:
                    if (layoutLeft.getChildCount() == 1) {
                        Toast.makeText(CommunityGameActivity.this, "Cant drop anymore community members here ", Toast.LENGTH_SHORT).show();
                    } else {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if (layoutLeft2.getChildCount() == 1) {
                        Toast.makeText(CommunityGameActivity.this, "Cant drop anymore community members here ", Toast.LENGTH_SHORT).show();

                    } else {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if (layoutRight.getChildCount() == 1) {
                        Toast.makeText(CommunityGameActivity.this, "Cant drop anymore community members here ", Toast.LENGTH_SHORT).show();
                    } else {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if (layoutRight2.getChildCount() == 1) {
                        Toast.makeText(CommunityGameActivity.this, "Cant drop anymore community members here ", Toast.LENGTH_SHORT).show();

                    } else {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //do nothing
                default:
                    break;
            }
            return true;
        }


    }

}




