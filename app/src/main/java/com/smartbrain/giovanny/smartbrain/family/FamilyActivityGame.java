package com.smartbrain.giovanny.smartbrain.family;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smartbrain.giovanny.smartbrain.MenuEasyActivity;
import com.smartbrain.giovanny.smartbrain.R;


public class FamilyActivityGame extends Activity {


    LinearLayout layoutLeft;
    LinearLayout layoutLeft2;
    LinearLayout layoutRight;
    LinearLayout layoutRight2;
    Button endGame;

    protected void viewSetter(){
        // seteo el touch listener para cada imagen en el layout y llamo a la clase MyTouchListener

        findViewById(R.id.dad).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dad2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dad3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dad4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dad1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.mom).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.mom1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.mom2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.mom3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.mom4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.sister).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.sister1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.sister2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.sister3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.sister4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.brother).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.brother1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.brother2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.brother3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.brother4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.aunt).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.uncle).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.aunt1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.uncle1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.aunt2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.uncle2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.aunt3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.uncle3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.aunt4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.uncle4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.cat).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dog).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandma).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandpa).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.cat).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dog).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandma1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandpa1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.cat2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dog2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandma2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandpa2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.cat3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dog3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandma3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandpa3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.cat4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.dog4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandma4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.grandpa4).setOnTouchListener(new MyTouchListener());

        // seteo el DragListener que va a llamar a la clase MyDragListener
        findViewById(R.id.familyLayout).setOnDragListener(new MyDragListener());
        findViewById(R.id.howFamLayout).setOnDragListener(new MyDragListener());
        findViewById(R.id.familyLayout2).setOnDragListener(new MyDragListener());
        findViewById(R.id.howFamLayout2).setOnDragListener(new MyDragListener());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_activity_game);

        viewSetter();
        layoutLeft =(LinearLayout)findViewById(R.id.familyLayout);
        layoutLeft2= (LinearLayout)findViewById(R.id.familyLayout2);
        layoutRight=(LinearLayout)findViewById(R.id.howFamLayout);
        layoutRight2=(LinearLayout)findViewById(R.id.howFamLayout2);
        endGame= (Button) findViewById(R.id.endGame);

        //boton que termina la actividad cuando sea que el usuario lo desee.
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyActivityGame.this, MenuEasyActivity.class);
                FamilyActivityGame.this.finish();
                startActivity(intent);

            }
        });

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
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DROP:
                    if (layoutLeft.getChildCount() ==5){
                        Toast.makeText(FamilyActivityGame.this, "Cant drop anymore family members here ", Toast.LENGTH_SHORT).show();
                    }else{
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if(layoutLeft2.getChildCount() ==5){
                        Toast.makeText(FamilyActivityGame.this, "Cant drop anymore family members here ", Toast.LENGTH_SHORT).show();

                    }else{
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if (layoutRight.getChildCount() ==5){
                        Toast.makeText(FamilyActivityGame.this, "Cant drop anymore family members here ", Toast.LENGTH_SHORT).show();
                    }else{
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    }
                    if(layoutRight2.getChildCount() ==5){
                        Toast.makeText(FamilyActivityGame.this, "Cant drop anymore family members here ", Toast.LENGTH_SHORT).show();

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
}
