package com.smartbrain.giovanny.smartbrain.neighborhood;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartbrain.giovanny.smartbrain.ColorsWinActivity;
import com.smartbrain.giovanny.smartbrain.R;


import java.util.Locale;

public class ConmunityActivity extends Activity implements TextToSpeech.OnInitListener{
    //imageViews necesarios
    private ImageView policeman;
    private ImageView policewoman;
    private ImageView teacher;
    private ImageView professor;
    private ImageView fireman;
    private ImageView firewoman;
    private ImageView doctorf;
    private ImageView doctorm;
    private ImageView heart;
    private ImageView heart1;
    private ImageView heart2;
    // layouts necesarios
    private LinearLayout layoutLeft;
    private LinearLayout layoutLeft2;
    private LinearLayout layoutRight;
    private LinearLayout layoutRight2;
    // botones
    private ImageView repeat;
    private ImageView next;
    // variables booleanas necesarias para el juego
    private boolean policeFlag,policewomanFlag,doctorFlag,doctorfFlag,firemanFlag,firewomanFlag,teacherFlag,professorFlag = false;
    // tts
    private TextToSpeech tts;
    // array de palabras para preguntar
    private String [] questionArray = {"Hi, lets play. pay attention to my instructions, in order to win. please touch next to start playing"
            ,"Drag the police-woman to the police station","drag the fireman to the fire station","drag the female doctor to the hospital"
            ,"drag the teacher to the school","drag the male doctor to the hospital","drag the professor to the school", "drag the fire-woman to the fire station"
            ,"drag the policeman to the police station"};
    // contadores de voz y vidas
    private int i =0;
    private int liveCounter =0;
    // points
    private int points = 300;
    // bundle
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conmunity);

        //bundle
        extras = getIntent().getExtras();
        name = extras.getString("NAME");
        // drawable resource
        Drawable redMarkedArea = getResources().getDrawable(R.drawable.red_marked_area);
        // tts
        tts = new TextToSpeech(this,this);
        // seteo de imageviews
        policeman = (ImageView)findViewById(R.id.policeman);
        policewoman= (ImageView)findViewById(R.id.policewoman);
        teacher = (ImageView)findViewById(R.id.teacherF);
        professor= (ImageView)findViewById(R.id.teacherM);
        fireman= (ImageView)findViewById(R.id.fireman);
        firewoman= (ImageView)findViewById(R.id.firewoman);
        doctorf= (ImageView)findViewById(R.id.doctorF);
        doctorm = (ImageView)findViewById(R.id.doctorM);
        heart = (ImageView)findViewById(R.id.heart);
        heart1 = (ImageView)findViewById(R.id.heart1);
        heart2 = (ImageView)findViewById(R.id.heart2);
        // seteo de layouts y botones
        layoutLeft = (LinearLayout)findViewById(R.id.layoutLeft);
        layoutLeft2 = (LinearLayout)findViewById(R.id.layoutLeft2);
        layoutRight = (LinearLayout)findViewById(R.id.layoutRight);
        layoutRight2 = (LinearLayout)findViewById(R.id.layoutRight2);
        layoutLeft.setBackgroundDrawable(redMarkedArea);
        layoutLeft2.setBackgroundDrawable(redMarkedArea);
        layoutRight.setBackgroundDrawable(redMarkedArea);
        layoutRight2.setBackgroundDrawable(redMarkedArea);
        repeat =(ImageView)findViewById(R.id.repeat);
        next = (ImageView)findViewById(R.id.next);
        //botones
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setVisibility(View.INVISIBLE);
                repeat.setVisibility(View.VISIBLE);
                setTouchListeners();
                setFalse();
                i++;
                switch (i){
                    case 1:
                        speakOut();
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (policewomanFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 2:
                        speakOut();
                        layoutRight.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firewomanFlag || policewomanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (firemanFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 3:
                        speakOut();
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firewomanFlag || policewomanFlag || firemanFlag || doctorFlag || teacherFlag || professorFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (doctorfFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if (policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 4:
                        speakOut();
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firewomanFlag || policewomanFlag || doctorfFlag || doctorFlag ||firemanFlag || professorFlag) {
                                            if(liveCounter == 3){
                                                ConmunityActivity.this.recreate();
                                            }else{
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter ++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (teacherFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if (policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 5:
                        speakOut();
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firewomanFlag || policewomanFlag || doctorfFlag || firemanFlag || teacherFlag || professorFlag) {
                                            if(liveCounter == 3){
                                                ConmunityActivity.this.recreate();
                                            }else{
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter ++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (doctorFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });

                        layoutRight.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if (policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 6:
                        speakOut();
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firewomanFlag || policewomanFlag || doctorfFlag || doctorFlag || teacherFlag || firemanFlag) {
                                            if(liveCounter == 3){
                                                ConmunityActivity.this.recreate();
                                            }else{
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter ++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (professorFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if (policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 7:
                        speakOut();
                        layoutRight.setOnDragListener(new View.OnDragListener() {
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
                                        if (policeFlag || firemanFlag || policewomanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag) {
                                            if(liveCounter == 3){
                                                ConmunityActivity.this.recreate();
                                            }else{
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter ++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (firewomanFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, touch next to continue", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if (policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if (liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 8:
                        speakOut();
                        layoutLeft.setOnDragListener(new View.OnDragListener() {
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
                                        if (firemanFlag || firewomanFlag || policewomanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag) {
                                            if(liveCounter == 3){
                                                ConmunityActivity.this.recreate();
                                            }else{
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter ++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        } else if (policeFlag) {
                                            View view = (View) event.getLocalState();
                                            ViewGroup owner = (ViewGroup) view.getParent();
                                            owner.removeView(view);
                                            LinearLayout container = (LinearLayout) v;
                                            container.addView(view);
                                            view.setVisibility(View.VISIBLE);
                                            tts.speak("Well done, you won the game, touch next to gain your points", TextToSpeech.QUEUE_FLUSH, null);
                                            next.setVisibility(View.VISIBLE);
                                            setFalse();
                                        }
                                        break;
                                    case DragEvent.ACTION_DRAG_ENDED:
                                        //do nothing
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutLeft2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight2.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        layoutRight.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                int action = event.getAction();
                                switch (event.getAction()) {
                                    case DragEvent.ACTION_DROP:
                                        if(policeFlag || firewomanFlag || firemanFlag || doctorfFlag || doctorFlag || teacherFlag || professorFlag || policewomanFlag) {
                                            if(liveCounter == 3) {
                                                ConmunityActivity.this.recreate();
                                            } else {
                                                tts.speak("sorry try again", TextToSpeech.QUEUE_FLUSH, null);
                                                setLives()[liveCounter].setVisibility(View.INVISIBLE);
                                                liveCounter++;
                                                if (points == 0)
                                                    points = 0;
                                                else
                                                    points = points - 100;
                                                setFalse();
                                            }
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    case 9:
                        Intent intent = new Intent(ConmunityActivity.this, ColorsWinActivity.class);
                        bundle.putString("NAME", name);
                        bundle.putInt("POINTS", points);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        ConmunityActivity.this.finish();
                        break;
                }
            }
        });

    }
    public void speakOut(){
        tts.speak(questionArray[i],TextToSpeech.QUEUE_FLUSH,null);
    }

    // seteo un array de oportunidades
    private ImageView[] setLives(){
        ImageView[] lives = {heart,heart1,heart2};
        return lives;
    }

    // seteo siempre los touch listeners
    public void setTouchListeners(){
        policeman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                policeFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        policewoman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                policewomanFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        fireman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                firemanFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        firewoman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                firewomanFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        teacher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                teacherFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        professor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                professorFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        doctorf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                doctorfFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        doctorm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                doctorFlag = true;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    // seteo cada vez que toco algo en false las banderas
    private void setFalse(){
        policeFlag = false;
        policewomanFlag= false;
        doctorFlag= false;
        doctorfFlag= false;
        firemanFlag= false;
        firewomanFlag= false;
        teacherFlag= false;
        professorFlag = false;
    }

    // tts
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
                Log.e("TTS","TTS is a succes");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        liveCounter =0;
        if (tts == null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
