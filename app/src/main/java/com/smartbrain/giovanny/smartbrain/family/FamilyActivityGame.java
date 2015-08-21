package com.smartbrain.giovanny.smartbrain.family;

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
import android.widget.RelativeLayout;
import com.smartbrain.giovanny.smartbrain.R;
import com.smartbrain.giovanny.smartbrain.WinActivity;

import java.util.Locale;

public class FamilyActivityGame extends Activity implements TextToSpeech.OnInitListener {
    private LinearLayout layoutLeft;
    private LinearLayout layoutLeft2;
    private LinearLayout layoutRight;
    private LinearLayout layoutRight2;
    private RelativeLayout bigFamily;
    private  RelativeLayout bigHowFamily;
    private ImageView endGame;
    private ImageView refresh;
    private String message;
    // tts
    private TextToSpeech tts;
    // points
    int points = 0;
    // counters
    int counterFamily = 0;
    int counterHowFamily = 0;

    // bundle y extras para agarrar el nombre y el ponerlo en un bundle nuevo
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;


    protected void setDadSounds() {
        //set Sounds for al the imageViews
        int imageArray[] = {R.id.dad, R.id.dad1, R.id.dad2, R.id.dad3, R.id.dad4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Dad");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setMomSounds() {
        int imageArray[] = {R.id.mom, R.id.mom1, R.id.mom2, R.id.mom3, R.id.mom4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Mom");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setSisterSounds() {
        int imageArray[] = {R.id.sister, R.id.sister2, R.id.sister3, R.id.sister4, R.id.sister1};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Sister");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setBortherSounds() {
        int imageArray[] = {R.id.brother, R.id.brother1, R.id.brother2, R.id.brother3, R.id.brother4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Brother");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setGrandpaSounds() {
        int imageArray[] = {R.id.grandpa, R.id.grandpa1, R.id.grandpa2, R.id.grandpa3, R.id.grandpa4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("GrandFather");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setAuntSounds() {
        int imageArray[] = {R.id.aunt, R.id.aunt1, R.id.aunt2, R.id.aunt3, R.id.aunt4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Aunt");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setUncleSounds() {
        int imageArray[] = {R.id.uncle, R.id.uncle1, R.id.uncle2, R.id.uncle3, R.id.uncle4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Uncle");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setGrandmaSounds() {
        int imageArray[] = {R.id.grandma, R.id.grandma1, R.id.grandma2, R.id.grandma3, R.id.grandma4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("GrandMother");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setCatSounds() {
        int imageArray[] = {R.id.cat, R.id.cat1, R.id.cat2, R.id.cat3, R.id.cat4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Cat");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void setDogSounds() {
        int imageArray[] = {R.id.dog, R.id.dog1, R.id.dog2, R.id.dog3, R.id.dog4};
        for (int i = 0; i < imageArray.length; i++) {
            findViewById(imageArray[i]).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    speakOut("Dog");
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.VISIBLE);
                        return true;
                    } else
                        return false;
                }
            });
        }
    }

    protected void viewSetter(){
        // seteo el DragListener que va a llamar a la clase MyDragListener
        int layoutArray[]={R.id.familyLayout,R.id.familyLayout2,R.id.howFamLayout,R.id.howFamLayout2};
        for(int i=0; i<layoutArray.length; i++)
            findViewById(layoutArray[i]).setOnDragListener(new MyDragListener());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_activity_game);

        tts = new TextToSpeech(this,this);

        viewSetter();
        setDadSounds();
        setMomSounds();
        setSisterSounds();
        setBortherSounds();
        setAuntSounds();
        setUncleSounds();
        setGrandmaSounds();
        setGrandpaSounds();
        setCatSounds();
        setDogSounds();
        layoutLeft =(LinearLayout)findViewById(R.id.familyLayout);
        layoutLeft2= (LinearLayout)findViewById(R.id.familyLayout2);
        layoutRight=(LinearLayout)findViewById(R.id.howFamLayout);
        layoutRight2=(LinearLayout)findViewById(R.id.howFamLayout2);
        bigHowFamily= (RelativeLayout)findViewById(R.id.bigHowFamily);
        bigFamily=(RelativeLayout)findViewById(R.id.bigFamilyLayout);
        endGame= (ImageView) findViewById(R.id.endGame);
        Drawable redMarkedArea = getResources().getDrawable(R.drawable.red_marked_area);
        bigFamily.setBackgroundDrawable(redMarkedArea);
        bigHowFamily.setBackgroundDrawable(redMarkedArea);
        refresh= (ImageView)findViewById(R.id.refresh);

        // agarro el extra y se lo meto a name
        extras = getIntent().getExtras();
        name = extras.getString("NAME");

        // boton que reinicia la actividad
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FamilyActivityGame.this.recreate();
            }
        });

        //boton que termina la actividad cuando sea que el usuario lo desee.
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyActivityGame.this, WinActivity.class);
                bundle.putString("NAME", name);
                bundle.putInt("POINTS", points);
                intent.putExtras(bundle);
                startActivity(intent);
                FamilyActivityGame.this.finish();
            }
        });

        bigFamily.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if (counterFamily == 0)
                            speakOut("you can drag your family members here");
                        counterFamily++;
                        break;
                }
                return true;
            }
        });
        bigHowFamily.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if (counterHowFamily == 0)
                            speakOut("drag the members that you would like to have in your family here");
                        counterHowFamily++;
                        break;
                }
                return true;
            }
        });
    }

    private void speakOut(String words){
        tts.speak(words,TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("Hi lets play with the family members");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
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

    // Esta clase sirve para llamar los varios metodso de DragEvent hechos con un switch para contemplar cada caso


    class MyDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DROP:
                    points = points + 5;
                    if (layoutLeft.getChildCount() ==5){
                        message = "";
                    }else{
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if(layoutLeft2.getChildCount() ==5){
                        message = "";

                    }else{
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if (layoutRight.getChildCount() ==5){
                        message = "";
                    }else{
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if(layoutRight2.getChildCount() ==5){
                        message = "";

                    }else{
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
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
           tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
