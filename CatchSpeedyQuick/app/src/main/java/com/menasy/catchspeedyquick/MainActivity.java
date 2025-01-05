package com.menasy.catchspeedyquick;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable runnable;
    Random rnd = new Random();
    TextView timer;
    TextView score;
    ImageView img;
    int timeCount = 10;
    int scoreCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        timer = findViewById(R.id.textView);
        score = findViewById(R.id.textView2);
        img = findViewById(R.id.imageView);
        startGame();

    }
    private void checkStatus()
    {
        if (timeCount == 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Oyun Bitti");
            alert.setMessage("Tekrar oynamak ister misin ?");
            alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            });
            alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Oyunu Kapatıldı", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            alert.show();
        }
    }
    private void setScore()
    {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (timeCount > 0)
                {
                    scoreCount++;
                    score.setText("Score: " + String.valueOf(scoreCount));
                }
            }
        });
    }
    private void startGame()
    {
        timeCount = 10;
        scoreCount = 0;
        score.setText("Score: 0");
        runnable = new Runnable() {
            @Override
            public void run()
            {
                timer.setText("Time: " + String.valueOf(timeCount));
                if (timeCount > 0)
                {
                    timeCount--;
                    setScore();
                    runnable = new Runnable() {
                        @Override
                        public void run()
                        {
                            if (timeCount > 0)
                            {
                                int x = 150 + rnd.nextInt(300) ;  // 10 ile 200 arasında
                                int y = 650 + rnd.nextInt(500) ; // 200 ile 450 arasında
                                img.setX(x);
                                img.setY(y);
                                handler.postDelayed(this, 50);
                            }
                            else
                                handler.removeCallbacks(this);
                        }
                    };
                    handler.post(runnable);// Hareket için
                    handler.postDelayed(this, 1000);
                }
                else
                {
                    handler.removeCallbacks(this);
                    checkStatus();
                }
            }
        };
        handler.post(runnable);

    }
}