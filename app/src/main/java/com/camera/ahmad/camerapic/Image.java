package com.camera.ahmad.camerapic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Image extends AppCompatActivity {

    byte[] image_byte;
    private ImageView camera_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        camera_image = (ImageView) findViewById(R.id.camera_image);

        Intent stream = getIntent();
        Bundle extras = stream.getExtras();


        if (stream.hasExtra("byteArray")) {
            image_byte = getIntent().getByteArrayExtra("byteArray");
            Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte, 0, image_byte.length);
            camera_image.setImageBitmap(doInvert(bitmap));

        }
    }

    // swap blue and red channels
    public static Bitmap doInvert(Bitmap src) {
        int[] pixels = new int[src.getWidth() * src.getHeight()];
        src.getPixels(pixels, 0, src.getWidth(), 0, 0, src.getWidth(), src.getHeight());
        int width = src.getWidth();
        int height = src.getHeight();

        int[] finalArray = new int[width * height];

        for (int i = 0; i < finalArray.length; i++) {

            int red = Color.red(pixels[i]);
            int green = Color.green(pixels[i]);
            int blue = Color.blue(pixels[i]);
            finalArray[i] = Color.rgb(blue, green, red);//invert sequence here.

        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

// Set the pixels
        bitmap.setPixels(finalArray, 0, width, 0, 0, width, height);
        return bitmap;

    }

}
