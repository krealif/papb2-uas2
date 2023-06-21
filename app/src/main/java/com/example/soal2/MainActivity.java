package com.example.soal2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImgView;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int mColorBackground;
    private Paint mCirclePaint = new Paint();
    private Paint mHeadPaint = new Paint();

    int hWidth;
    int hHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mImgView.setVisibility(View.INVISIBLE);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        mImgView.setOnClickListener(view -> {
            animate();
        });
    }

    private void animate() {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mImgView, "alpha", 1);
        fadeIn.setDuration(500);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mImgView, "alpha", 0);
        fadeOut.setDuration(1000);

        ObjectAnimator flip = ObjectAnimator.ofFloat(mImgView, "rotationY", 180);
        flip.setDuration(500);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(fadeIn, flip, fadeOut);
        animSet.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hWidth = mImgView.getWidth()/2;
        hHeight = mImgView.getHeight()/2;

        mBitmap = Bitmap.createBitmap(mImgView.getWidth(), mImgView.getHeight(),
                Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead();
        drawRightEye();
        drawLeftEye();
        drawEyeConnector();
    }

    private void drawEyeConnector() {
        Rect connectorRect = new Rect(hWidth-170, hHeight, hWidth+178, hHeight+28);
        mCanvas.drawRect(connectorRect, mCirclePaint);
    }

    private void drawLeftEye() {
        mCanvas.drawCircle(hWidth-170, hHeight, 60, mCirclePaint);
    }

    private void drawRightEye() {
        mCanvas.drawCircle(hWidth+170, hHeight, 60, mCirclePaint);
    }

    private void drawHead() {
        RectF headRect = new RectF(hWidth-350, hHeight-235,
                hWidth+350, hHeight+235);
        mCanvas.drawOval(headRect, mHeadPaint);
    }
}