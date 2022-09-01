package com.volkanunlu.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize ediyoruz,başlatıyoruz.
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        score=0;
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9}; //resimleri array içine aldık.
        hideImages();

        new CountDownTimer(20000,1000){ //zaman milisaniye cinsinde
            @Override
            public void onTick(long l) {   //başladığında ne yapayım
                timeText.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() { //bittiğinde ne yapayım
                timeText.setText("Time Off!");
                handler.removeCallbacks(runnable); //handler durdurduk süre bitince.

                for(ImageView image: imageArray){ //süre bitince ekranda görseli gizledik
                    image.setVisibility(View.INVISIBLE); //süre bitince ekranda görseli gizledik
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart ?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() { //pozitif button ve içeriği
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //restart atacağımız bölüm

                        Intent intent=new Intent();  //bu yöntem çok kullandığımız bi yöntem değil ama aklında bulunsun.
                        finish();  //mevcut aktiviteyi bitir.
                        startActivity(intent); //aktiviteyi yeniden başlat.

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() { //negatif button burada yer alacak
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_LONG).show();

                    }
                });

                alert.show(); //alert çalışması adına show ile gösterim

            }
        }.start();
}
        public void increaseScore(View view){ //image üzerine tıklandığı anda devreye giren metot. score artıyor.
            score++;
             scoreText.setText("Score: "+score);

    }

    public void hideImages(){

        handler= new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                for(ImageView image: imageArray){ //image array üzerinde döngü kurduk.

                    image.setVisibility(View.INVISIBLE); //görünmez yaptık.
                }
                Random random=new Random();
                int i=random.nextInt(9); //oyunda grid üzerinde 9 elemanım var id 0-8 arası dönecek 9'a kadar.
                imageArray[i].setVisibility(View.VISIBLE); //random sayıya göre görünür kıldım array içindeki image'i

                handler.postDelayed(this,500); //normalde runnable veririz ama üst sınıfı metot olmayıp main bağlı olduğu için this verdik.
            }
        };

        handler.post(runnable); //çalıştırmak için şart.



    }
}