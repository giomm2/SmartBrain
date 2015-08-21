package com.smartbrain.giovanny.smartbrain.vowels;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbrain.giovanny.smartbrain.ComnunityWinActivity;
import com.smartbrain.giovanny.smartbrain.MainActivity;
import com.smartbrain.giovanny.smartbrain.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class ActivityPuzzle extends Activity {

    protected static final int MENU_SCRAMBLE = 0;
    protected static final int MENU_SELECT_IMAGE = 1;
    protected static final int MENU_TAKE_PHOTO = 2;

    protected static final int RESULT_SELECT_IMAGE = 0;
    protected static final int RESULT_TAKE_PHOTO = 1;

    protected static final String KEY_SHOW_NUMBERS = "showNumbers";
    protected static final String KEY_IMAGE_URI = "imageUri";
    protected static final String KEY_PUZZLE = "slidePuzzle";
    protected static final String KEY_PUZZLE_SIZE = "puzzleSize";

    protected static final String FILENAME_DIR = "dolby.digital.plus";
    protected static final String FILENAME_PHOTO_DIR = FILENAME_DIR + "/photo";
    protected static final String FILENAME_PHOTO = "photo.jpg";

    protected static final int DEFAULT_SIZE = 3;

    private PuzzleView view;
    private PuzzleSlide slidePuzzle;
    private BitmapFactory.Options bitmapOptions;
    private int puzzleWidth = 1;
    private int puzzleHeight = 1;
    private Uri imageUri;
    private boolean portrait;
    private boolean expert;
    private final java.util.List<String> mActList = new java.util.ArrayList<String>();
    private TextView txtcont;
    private ImageView imgHeart1;
    private ImageView imgHeart2;
    private ImageView imgHeart3;
    private TextToSpeech tts;
    private String text,text2;


    //trae el nombre del usuario
    Bundle bundle = new Bundle();
    Bundle extras;
    private String name;
    private int gamePoints=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_puzzle);


        timer.start();
        txtcont=(TextView)findViewById(R.id.txtTimer);
        //////////////////////


        bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;

        slidePuzzle = new PuzzleSlide();

        view = new PuzzleView(this, slidePuzzle);
        setContentView(view);

        shuffle();

        if(!loadPreferences())
        {
            setPuzzleSize(DEFAULT_SIZE, true);
        }
//ruta de la imagen para el puzzle
        Uri path = Uri.parse("android.resource://com.smartbrain.giovanny.smartbrain/" + R.drawable.vocales);
//le seteo la ruta al metodo loadBitmap()
        loadBitmap(path);

//Lo que dira apenas se inicie la actividad
        tts = new TextToSpeech(ActivityPuzzle.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        ConvertTextToSpeech("Hello, Let's play. You have five minutes for complete the puzzle. Go Faster.");

                    }
                } else
                    Log.e("error", "Initilization Failed!");


            }
        }
        );
    }

    private void ConvertTextToSpeech(String voice1) {
        // TODO Auto-generated method stub
        text=voice1;
        if (text == null || "".equals(text)) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_puzzle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case MENU_SCRAMBLE:
                shuffle();
                return true;

            case MENU_SELECT_IMAGE:
                selectImage();
                return true;

            case MENU_TAKE_PHOTO:
                takePicture();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

        }

    //metodo que parte la cualquier imagen y la coloca de manera shuffle osea cualquiera "aleatorio"
    private void shuffle() {
        slidePuzzle.init(puzzleWidth, puzzleHeight);
        slidePuzzle.shuffle();
        view.invalidate();
        expert = view.getShowNumbers() == PuzzleView.ShowNumbers.NONE;
    }
    //metodo que carga la imagen para el puzzle
    protected void loadBitmap(Uri uri) {
        try
        {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            InputStream imageStream = getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(imageStream, null, o);
//ancho y largo de la pieza
            int targetWidth = view.getTargetWidth();
            int targetHeight = view.getTargetHeight();

            if(o.outWidth > o.outHeight && targetWidth < targetHeight)
            {
                int i = targetWidth;
                targetWidth = targetHeight;
                targetHeight = i;
            }

            if(targetWidth < o.outWidth || targetHeight < o.outHeight)
            {
                double widthRatio = (double) targetWidth / (double) o.outWidth;
                double heightRatio = (double) targetHeight / (double) o.outHeight;
                double ratio = Math.max(widthRatio, heightRatio);

                o.inSampleSize = (int) Math.pow(2, (int) Math.round(Math.log(ratio) / Math.log(0.5)));
            }
            else
            {
                o.inSampleSize = 1;
            }

            o.inScaled = false;
            o.inJustDecodeBounds = false;
//setea la imagen que le mandan por parametro por el
            imageStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream, null, o);
//si la image viene vacia
            if(bitmap == null)
            {
                Toast.makeText(this, getString(R.string.error_could_not_load_image), Toast.LENGTH_LONG).show();
                return;
            }

            int rotate = 0;

            Cursor cursor = getContentResolver().query(uri, new String[] {MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

            if(cursor != null)
            {
                try
                {
                    if(cursor.moveToFirst())
                    {
                        rotate = cursor.getInt(0);

                        if(rotate == -1)
                        {
                            rotate = 0;
                        }
                    }
                }
                finally
                {
                    cursor.close();
                }
            }

            if(rotate != 0)
            {
                Matrix matrix = new Matrix();
                matrix.postRotate(rotate);

                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
//se setea la imagen del path
            setBitmap(bitmap);
            imageUri = uri;
        }
        catch(FileNotFoundException ex)
        {
            //lanzamos una excepcion si no la encuentra
            Toast.makeText(this, MessageFormat.format(getString(R.string.error_could_not_load_image_error), ex.getMessage()), Toast.LENGTH_LONG).show();
            return;
        }
    }
    //caracteristicas de como seteo la imagen
    private void setBitmap(Bitmap bitmap) {
        portrait = bitmap.getWidth() < bitmap.getHeight();

        view.setBitmap(bitmap);
        setPuzzleSize(Math.min(puzzleWidth, puzzleHeight), true);

        setRequestedOrientation(portrait ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_SELECT_IMAGE);
    }
    //para tomar una foto pero no lo utilizamos
    private void takePicture()
    {
        File dir = getSaveDirectory();

        if(dir == null)
        {
            Toast.makeText(this, getString(R.string.error_could_not_create_directory_to_store_photo), Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(dir, FILENAME_PHOTO);
        Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(photoPickerIntent, RESULT_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
    {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode)
        {
            case RESULT_SELECT_IMAGE:
            {
                if(resultCode == RESULT_OK)
                {
                    Uri selectedImage = imageReturnedIntent.getData();
                    loadBitmap(selectedImage);
                }

                break;
            }

            case RESULT_TAKE_PHOTO:
            {
                if(resultCode == RESULT_OK)
                {
                    File file = new File(getSaveDirectory(), FILENAME_PHOTO);

                    if(file.exists())
                    {
                        Uri uri = Uri.fromFile(file);

                        if(uri != null)
                        {
                            loadBitmap(uri);
                        }
                    }
                }

                break;
            }
        }
    }
    //para guardar la foto en un directorio
    private File getSaveDirectory()
    {
        File root = new File(Environment.getExternalStorageDirectory().getPath());
        File dir = new File(root, FILENAME_PHOTO_DIR);

        if(!dir.exists())
        {
            if(!root.exists() || !dir.mkdirs())
            {
                return null;
            }
        }

        return dir;
    }
    private float getImageAspectRatio()
    {
        Bitmap bitmap = view.getBitmap();

        if(bitmap == null)
        {
            return 1;
        }

        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        return width / height;
    }
    //el cuadrito el tamanno del tablero
    protected void setPuzzleSize(int size, boolean scramble)
    {
        float ratio = getImageAspectRatio();

        if(ratio < 1)
        {
            ratio = 1f /ratio;
        }

        int newWidth;
        int newHeight;

        if(portrait)
        {
            newWidth = size;
            newHeight = (int) (size * ratio);
        }
        else
        {
            newWidth = (int) (size * ratio);
            newHeight = size;
        }

        if(scramble || newWidth != puzzleWidth || newHeight != puzzleHeight)
        {
            puzzleWidth = newWidth;
            puzzleHeight = newHeight;
            shuffle();//las piezas
        }
    }
    protected SharedPreferences getPreferences()
    {
        return getSharedPreferences(MainActivity.class.getName(), Activity.MODE_PRIVATE);
    }

    protected boolean loadPreferences()
    {
        SharedPreferences prefs = getPreferences();

        try
        {

            String s = prefs.getString(KEY_IMAGE_URI, null);

            if(s == null)
            {
                imageUri = null;
            }
            else
            {
                loadBitmap(Uri.parse(s));
            }

            int size = prefs.getInt(KEY_PUZZLE_SIZE, 0);
            s = prefs.getString(KEY_PUZZLE, null);

            if(size > 0 && s != null)
            {
                String[] tileStrings = s.split("\\;");

                if(tileStrings.length / size > 1)
                {
                    setPuzzleSize(size, false);
                    slidePuzzle.init(puzzleWidth, puzzleHeight);

                    int[] tiles = new int[tileStrings.length];

                    for(int i = 0; i < tiles.length; i++)
                    {
                        try
                        {
                            tiles[i] = Integer.parseInt(tileStrings[i]);
                        }
                        catch(NumberFormatException ex)
                        {
                        }
                    }

                    slidePuzzle.setTiles(tiles);
                }
            }

            return prefs.contains(KEY_SHOW_NUMBERS);
        }
        catch(ClassCastException ex)
        {
            // ignore broken settings
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    protected void onFinish() {
        tts.stop();
    }
    CountDownTimer timer = new CountDownTimer(300000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long ms = millisUntilFinished;
            String text = String.format("%02d\' %02d\"",
                    TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                    TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
            txtcont.setText(text);

        }

        @Override
        public void onFinish() {

            gamePoints=650;
            Intent intent = new Intent(ActivityPuzzle.this, ComnunityWinActivity.class);
            bundle.putString("NAME", name);
            bundle.putInt("POINTS", gamePoints);
            intent.putExtras(bundle);
            ActivityPuzzle.this.finish();
            startActivity(intent);

        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
        tts.shutdown();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //seteo en name el nombre que viene en extras que es un bundle
        extras = getIntent().getExtras();
        name = extras.getString("NAME");
        ConvertTextToSpeech("Hi" + name);
    }

}
