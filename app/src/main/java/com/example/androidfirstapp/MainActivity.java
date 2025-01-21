package com.example.androidfirstapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.media.MediaPlayer;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements OnTouchListener {

    private AnimationDrawable anim;
    private ImageView iv1,iv2,iv3,iv4,iv5,iv6;
    private MediaPlayer mp1,mp2,mp3,mp4,mp5,mp6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        iv1=(ImageView)findViewById(R.id.iv1);
        iv1.setBackgroundResource(R.drawable.rope1);
        iv2=(ImageView)findViewById(R.id.iv2);
        iv2.setBackgroundResource(R.drawable.rope1);
        iv3=(ImageView)findViewById(R.id.iv3);
        iv3.setBackgroundResource(R.drawable.rope1);
        iv4=(ImageView)findViewById(R.id.iv4);
        iv4.setBackgroundResource(R.drawable.rope1);
        iv5=(ImageView)findViewById(R.id.iv5);
        iv5.setBackgroundResource(R.drawable.rope1);
        iv6=(ImageView)findViewById(R.id.iv6);
        iv6.setBackgroundResource(R.drawable.rope1);

        iv1.setOnTouchListener(this);
        iv2.setOnTouchListener(this);
        iv3.setOnTouchListener(this);
        iv4.setOnTouchListener(this);
        iv5.setOnTouchListener(this);
        iv6.setOnTouchListener(this);


        mp2=MediaPlayer.create(this, R.raw.electric2);
        mp3=MediaPlayer.create(this, R.raw.electric3);
        mp4=MediaPlayer.create(this, R.raw.electric4);
        mp5=MediaPlayer.create(this, R.raw.electric5);
        mp6=MediaPlayer.create(this, R.raw.electric6);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LinearLayout1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onTouch(View v, MotionEvent m){

        if (v==iv1){
            if (m.getAction()==MotionEvent.ACTION_DOWN){
                iv1.setBackgroundResource(R.drawable.anim);
                anim = (AnimationDrawable) iv1.getBackground();
                anim.start();

                mp1=MediaPlayer.create(this, R.raw.electric1);
                mp1.start();
            }
            if (m.getAction()==MotionEvent.ACTION_UP){
                anim.stop();
                mp1.release();
                iv1.setBackgroundResource(R.drawable.rope1);
            }
        }
        if (v==iv2){
            if (m.getAction()==MotionEvent.ACTION_DOWN){
                createAnim(iv2,mp2);
            }
        }
        if (v==iv3){
            if (m.getAction()==MotionEvent.ACTION_DOWN){
                createAnim(iv3,mp3);
            }
        }
        if (v==iv4){
            if (m.getAction()==MotionEvent.ACTION_DOWN){
                createAnim(iv4,mp4);
            }
        }
        if (v==iv5){
            if (m.getAction()==MotionEvent.ACTION_DOWN){
                createAnim(iv5,mp5);
            }
        }
        if (v==iv6){
            if (m.getAction()==MotionEvent.ACTION_DOWN){
                createAnim(iv6,mp6);
            }
        }
        return true;
    }

    public void createAnim(ImageView iv, MediaPlayer mp){
        iv.setBackgroundResource(R.drawable.anim);
        anim = (AnimationDrawable) iv.getBackground();
        anim.stop();
        anim.start();
        mp.start();
    }
}