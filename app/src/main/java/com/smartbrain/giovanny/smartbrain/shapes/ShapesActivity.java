package com.smartbrain.giovanny.smartbrain.shapes;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.smartbrain.giovanny.smartbrain.HardMenuActivity;
import com.smartbrain.giovanny.smartbrain.R;

import java.util.Locale;


public class ShapesActivity extends Activity {
    private ImageView circle;
    private ImageView triangle;
    private ImageView rectangle;
    private ImageView square;
    private  ImageView container;
    private  ImageView container1;
    private  ImageView container2;
    private  ImageView container3;
    private ImageView heart;
    private ImageView heart1;
    private ImageView heart2;

    private boolean circleCheck;
    private  boolean squareCheck;
    private boolean rectangleCheck;
    private boolean triangleCheck;


    private TextToSpeech tts;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        circle = (ImageView) findViewById(R.id.imageCircle);
        triangle = (ImageView) findViewById(R.id.imageTriangle);
        square = (ImageView) findViewById(R.id.imageSquart);
        rectangle = (ImageView)findViewById(R.id.imageRectangle);
        container = (ImageView) findViewById(R.id.shapeContainer);
        heart = (ImageView)findViewById(R.id.heart1);
        heart1 = (ImageView)findViewById(R.id.heart2);
        heart2 = (ImageView)findViewById(R.id.heart3);

        container1 = (ImageView) findViewById(R.id.shapeContainer1);
        container2= (ImageView) findViewById(R.id.shapeContainer2);
        container3 = (ImageView) findViewById(R.id.shapeContainer3);

        final Drawable yellow = getResources().getDrawable(R.drawable.yellow_market);
        final Drawable red = getResources().getDrawable(R.drawable.red_market);
        final Drawable blue = getResources().getDrawable(R.drawable.blue_mark);
        final Drawable green = getResources().getDrawable(R.drawable.green_market);

        tts = new TextToSpeech(ShapesActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                //TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Hello let's play, put the shapes in the correct way");
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });

        container.setBackgroundDrawable(yellow);
        container1.setBackgroundDrawable(red);
        container2.setBackgroundDrawable(green);
        container3.setBackgroundDrawable(blue);

        circle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Circle");
                circleCheck = true;
                return circleCheck;
            }
        });
        triangle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Triangle");
                triangleCheck = true;
                return triangleCheck;
            }
        });

        square.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Square");
                squareCheck = true;
                return squareCheck;
            }
        });

        rectangle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                ConvertTextToSpeech("Rectangle");
                rectangleCheck = true;
                return rectangleCheck;
            }
        });
        //va el circle
        //listo
        container1.setOnDragListener(new View.OnDragListener() {
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
                        if (circleCheck == true && squareCheck==false && triangleCheck==false && rectangleCheck==false) {
                            container1.setBackgroundResource(R.drawable.cir);
                            ConvertTextToSpeech("Goog Job!");
                            v.setVisibility(View.VISIBLE);
                            circleCheck = false;
                        } else {
                            if(heart.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck = false;
                                squareCheck = false;
                                rectangleCheck=false;
                                heart.setVisibility(View.INVISIBLE);
                            }
                            else if(heart1.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck = false;
                                squareCheck = false;
                                rectangleCheck=false;
                                heart1.setVisibility(View.INVISIBLE);
                            }else if(heart2.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck = false;
                                squareCheck = false;
                                rectangleCheck=false;
                                heart2.setVisibility(View.INVISIBLE);
                            }
                            else{
                                ConvertTextToSpeech("Sorry try again later");
                                Intent intent = new Intent (ShapesActivity.this, HardMenuActivity.class);
                                startActivity(intent);
                                ShapesActivity.this.finish();
                            }

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
//va el square
        container.setOnDragListener(new View.OnDragListener() {
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

                        if (circleCheck == false && triangleCheck==false && squareCheck==true && rectangleCheck ==false) {
                            container.setBackgroundResource(R.drawable.cu);
                            ConvertTextToSpeech("Good job!");
                            v.setVisibility(View.VISIBLE);
                            squareCheck=false;
                        } else {
                            if (heart.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                squareCheck= false;
                                circleCheck=false;
                                triangleCheck=false;
                                rectangleCheck= false;
                                heart.setVisibility(View.INVISIBLE);
                            }else if(heart1.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                squareCheck= false;
                                circleCheck=false;
                                triangleCheck=false;
                                rectangleCheck= false;
                                heart1.setVisibility(View.INVISIBLE);
                            }else if (heart2.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                squareCheck= false;
                                circleCheck=false;
                                triangleCheck=false;
                                rectangleCheck= false;
                                heart2.setVisibility(View.INVISIBLE);

                            }else{
                                ConvertTextToSpeech("Sorry try again later");
                                Intent intent = new Intent (ShapesActivity.this, HardMenuActivity.class);
                                startActivity(intent);
                                ShapesActivity.this.finish();
                            }

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

        //va el triangle
        container2.setOnDragListener(new View.OnDragListener() {
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
                        if (circleCheck == false && squareCheck==false && triangleCheck == true && rectangleCheck == false) {
                            container2.setBackgroundResource(R.drawable.tr);
                            ConvertTextToSpeech("Good Job!");
                            v.setVisibility(View.VISIBLE);
                            triangleCheck=false;
                        } else {
                            if(heart.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck=false;
                                squareCheck=false;
                                rectangleCheck=false;
                                heart.setVisibility(View.INVISIBLE);
                            }else if (heart1.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck=false;
                                squareCheck=false;
                                rectangleCheck=false;
                                heart1.setVisibility(View.INVISIBLE);
                            }else if(heart2.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck=false;
                                squareCheck=false;
                                rectangleCheck=false;
                                heart2.setVisibility(View.INVISIBLE);
                            }
                            else{
                                ConvertTextToSpeech("Sorry try again later");
                                Intent intent = new Intent (ShapesActivity.this, HardMenuActivity.class);
                                startActivity(intent);
                                ShapesActivity.this.finish();
                            }
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

        //aqui va el rectangle
        container3.setOnDragListener(new View.OnDragListener() {
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
                        if (circleCheck == false && squareCheck==false && triangleCheck == false && rectangleCheck == true) {
                            container3.setBackgroundResource(R.drawable.rec);
                            ConvertTextToSpeech("Good Job!");
                            v.setVisibility(View.VISIBLE);
                            rectangleCheck=false;
                        } else {
                            if(heart.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck=false;
                                squareCheck=false;
                                rectangleCheck=false;

                                heart.setVisibility(View.INVISIBLE);

                            }else if(heart1.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck=false;
                                squareCheck=false;
                                rectangleCheck=false;

                                heart1.setVisibility(View.INVISIBLE);
                            }else if(heart2.getVisibility()==View.VISIBLE){
                                ConvertTextToSpeech("Sorry try again");
                                putShapes();
                                circleCheck = false;
                                triangleCheck=false;
                                squareCheck=false;
                                rectangleCheck=false;

                                heart2.setVisibility(View.INVISIBLE);
                            }else {
                                ConvertTextToSpeech("Sorry try again later");
                                Intent intent = new Intent (ShapesActivity.this, HardMenuActivity.class);
                                startActivity(intent);
                                ShapesActivity.this.finish();
                            }
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shapes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // poner las formas en su lugar
    private void putShapes(){
        ImageView[] imageViews = {circle,triangle,square,rectangle};

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setVisibility(View.VISIBLE);

        }
    }

    private void ConvertTextToSpeech(String voice) {
        // TODO Auto-generated method stub
        text=voice;
        //text = et.getText().toString();
        if (text == null || "".equals(text)) {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
