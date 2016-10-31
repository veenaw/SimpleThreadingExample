package com.example.android.simplethreadingexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SimpleThreadingExample extends Activity {
    private static final String TAG = "SimpleThreadingExample";

    private Bitmap mBitmap;
    private ImageView mIView;
    private int mDelay = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_threading_example);
        mIView = (ImageView) findViewById(R.id.imageView);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIcon();
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleThreadingExample.this, "I'm Working",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadIcon() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(mDelay);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.toString());
                }
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.painter);
                SimpleThreadingExample.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mIView.setImageBitmap(mBitmap);
                    }
                });
                // This doesn't work in Android
                //mIView.setImageBitmap(mBitmap);
            }
        }).start();
    }
}
