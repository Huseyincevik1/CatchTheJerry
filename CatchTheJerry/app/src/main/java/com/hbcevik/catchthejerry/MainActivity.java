package com.hbcevik.catchthejerry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView TextScore;
    TextView TextTime;
    int score;
    ImageView ImageView;
    ImageView ImageView2;
    ImageView ImageView3;
    ImageView ImageView4;
    ImageView ImageView5;
    ImageView ImageView6;
    ImageView ImageView7;
    ImageView ImageView8;
    ImageView ImageView9;
    ImageView ImageView11;
    ImageView ImageView13;
    ImageView ImageView15;

    ImageView[] ImageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextScore = (TextView) findViewById(R.id.TextScore);
        TextTime =(TextView) findViewById(R.id.TextTime);
        ImageView = findViewById(R.id.imageView);
        ImageView2 = findViewById(R.id.imageView2);
        ImageView3 = findViewById(R.id.imageView3);
        ImageView4 = findViewById(R.id.imageView4);
        ImageView5 = findViewById(R.id.imageView5);
        ImageView6 = findViewById(R.id.imageView6);
        ImageView7 = findViewById(R.id.imageView7);
        ImageView8 = findViewById(R.id.imageView8);
        ImageView9 = findViewById(R.id.imageView9);
        ImageView11=  findViewById(R.id.imageView11);
        ImageView13 = findViewById(R.id.imageView13);
        ImageView15 = findViewById(R.id.imageView15);

        ImageArray = new ImageView[]{ImageView,ImageView2, ImageView3, ImageView4, ImageView5, ImageView6, ImageView7, ImageView8, ImageView9, ImageView11, ImageView13, ImageView15};
        HideImages();
        score = 0;

        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
             TextTime.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
            TextTime.setText("Time Off");
            handler.removeCallbacks(runnable);
                for(ImageView image : ImageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("would you like to try again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }

        }.start();
    }

    public void increaseScore(View view){
        score = score + 1;
        TextScore.setText("score: "+ score);
    }

    public void reduceScore(View view){
        score = score - 1;
        TextScore.setText("score: "+ score);
    }

    public void HideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : ImageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random rnd = new Random();
                int i = rnd.nextInt(12);
                ImageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,800);
            }
        };

        handler.post(runnable);


    }
}