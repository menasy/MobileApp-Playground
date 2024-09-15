package com.menasy.runnablehandler;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    TextView textView;
    int num = 0;
    Handler handler;
    Runnable runnable;
    Button butStart;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.textView = findViewById(R.id.textView);
        butStart = findViewById(R.id.button);
    }

    public void start(View view)
    {
        //handler.postDelayed(runnable, 1000); Bu satır, Runnable'ı 1000 milisaniye (yani 1 saniye) sonra tekrar çalıştırılmak üzere sıraya koyuyor.
        // Bu, işlemi her 1 saniyede bir tekrar etmesini sağlar. Bu sayede num değeri sürekli artar ve her saniye güncellenir. yani handler dayesinde runnable tekrarlanır.
        //1 saniye gecikmeyle (postDelayed) tekrar çalıştırılması için sıraya koyulur. handler.post() da direkt çalıştırır.
        //Her 1 saniye sonra Runnable tekrar çalışır, yine num değeri ekrana yazdırılır ve artırılır, sonra yine 1 saniyelik gecikmeyle tekrar çalıştırılır. Bu döngü devam eder.
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run()
            {
                textView.setText("Time: " + num);
                num++;
                textView.setText("Time: " + num);
                handler.postDelayed(runnable, 1000); // Bu runnable yani belirlenen işlevi işleme sırasına koyar.
            }
        };
        handler.post(runnable); // bu belirlenen kodun direkt çalıştırılması için
        butStart.setEnabled(false);
    }
    public void stop(View view)
    {
        butStart.setEnabled(true);
        handler.removeCallbacks(runnable);
        num = 0;
        textView.setText("Time: " + num);
    }
}