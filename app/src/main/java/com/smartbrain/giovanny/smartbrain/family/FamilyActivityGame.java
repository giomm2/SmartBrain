package com.smartbrain.giovanny.smartbrain.family;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
        int imageArray[] = {R.id.dad,R.id.dad1,R.id.dad2,R.id.dad3,R.id.dad4,R.id.mom,R.id.mom1,R.id.mom2,R.id.mom3,R.id.mom4
                ,R.id.sister,R.id.sister2,R.id.sister3,R.id.sister4,R.id.sister1,R.id.brother,R.id.brother1,R.id.brother2
                ,R.id.brother3,R.id.brother4,R.id.aunt,R.id.aunt1,R.id.aunt2,R.id.aunt3,R.id.aunt4,R.id.uncle,R.id.uncle1
                ,R.id.uncle2,R.id.uncle3,R.id.uncle4,R.id.cat,R.id.cat1,R.id.cat2,R.id.cat3,R.id.cat4,R.id.dog,R.id.dog1
                ,R.id.dog2,R.id.dog3,R.id.dog4,R.id.grandma,R.id.grandma1,R.id.grandma2,R.id.grandma3,R.id.grandma4,R.id.grandpa
                ,R.id.grandpa1,R.id.grandpa2,R.id.grandpa3,R.id.grandpa4};
        for(int i=0;i<imageArray.length;i++)
            findViewById(imageArray[i]).setOnTouchListener(new MyTouchListener());
        // seteo el DragListener que va a llamar a la clase MyDragListener
        int layoutArray[]={R.id.familyLayout,R.id.familyLayout2,R.id.howFamLayout,R.id.howFamLayout2};
        for(int i=0; i<layoutArray.length; i++)
            findViewById(layoutArray[i]).setOnDragListener(new MyDragListener());
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
        Drawable redMarkedArea = getResources().getDrawable(R.drawable.red_marked_area);
        layoutLeft.setBackgroundDrawable(redMarkedArea);
        layoutLeft2.setBackgroundDrawable(redMarkedArea);
        layoutRight.setBackgroundDrawable(redMarkedArea);
        layoutRight2.setBackgroundDrawable(redMarkedArea);

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
