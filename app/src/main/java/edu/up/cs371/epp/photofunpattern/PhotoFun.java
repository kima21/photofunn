package edu.up.cs371.epp.photofunpattern;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
        import android.widget.Button;
        import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 *  class PhotoFun controls this photo manipulation app.
 *
 *  @author  Edward C. Epp
 *  @version November 2017
 *   https://github.com/edcepp/PhotoFunPattern
 *
 *   @edited Ashley and Meredith
 *   @version September 17 2018
 *
 */

public class PhotoFun extends AppCompatActivity {

    // Image resources
    private Bitmap myOriginalBmp;
    private ImageView myNewImageView;
    private int contentView;


    /*
     * onCreate This constructor lays out the user interface, initializes the
     * original image and links buttons to their actions.
     *
     * @param savedInstanceState Required by parent object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fun);

        ImageView originalImageView =
                (ImageView) findViewById(R.id.originalImage);
        BitmapDrawable originalDrawableBmp =
                (BitmapDrawable) originalImageView.getDrawable();
        myOriginalBmp = originalDrawableBmp.getBitmap();

        myNewImageView = (ImageView) findViewById(R.id.newImage);

        Button grayFilterButton =
                (Button) findViewById(R.id.grayFilterButton);
        grayFilterButton.setOnClickListener(new grayFilterButtonListener());
        Button brightnessFilterButton =
                (Button) findViewById(R.id.brightnessFilterButton);
        brightnessFilterButton.setOnClickListener
                (new brightnessFilterButtonListener());

        SeekBar seekBrightness =
                (SeekBar) findViewById(R.id.seekBar);
        seekBrightness.setOnSeekBarChangeListener
                (new seekBarBrightnessListener());

        //TextView newValue =
                //(TextView) findViewById(R.id.adjustValue);



    }

    public void setContentView(int contentView) {
        this.contentView = contentView;
    }

    /*
    * class grayFilterButtonListener this inner class defines the action for
    * the gray filter button.
    */
    private class grayFilterButtonListener implements View.OnClickListener {
        public void onClick(View button) {
            GrayFilter filter = new GrayFilter();
            myNewImageView.setImageBitmap(filter.apply(myOriginalBmp));
        }
    }

    /*
    * class grayFilterButtonListener this inner class defines the action for the
    * brightness filter
    * button.
    */
    private class brightnessFilterButtonListener
            implements View.OnClickListener {
        public void onClick(View button) {
            BrightnessFilter filter = new BrightnessFilter();
            myNewImageView.setImageBitmap(filter.apply(myOriginalBmp));

        }
    }

    private class seekBarBrightnessListener
            implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            progress = progress-128;
            SeekBarState.getInstance().setBrightnessDelta(progress);
            Log.e("value is -->", " " + SeekBarState.getInstance().getBrightnessDelta());

            TextView newValue =
                    (TextView) findViewById(R.id.adjustValue);
            newValue.setText(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    }

    //private class

}

/**
 * Checkpoint 11:
 * Pro: It ensures that the object is only initialized once throughout the application, avoiding incorrect app behavior and resource overuse.
 * Con: Can end up creating an object that never gets used, occupies unnecessary memory.
 */

