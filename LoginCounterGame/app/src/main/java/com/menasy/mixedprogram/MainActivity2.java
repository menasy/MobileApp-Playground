package com.menasy.mixedprogram;

import android.content.Intent;
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

public class MainActivity2 extends AppCompatActivity {

    String userName;
    TextView counter;
    TextView msg;
    TextView tit;
    Button butStart, butStop;
    Handler handler;
    Runnable runAble;
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        userName = intent.getStringExtra("inputUser");
        counter = findViewById(R.id.textView);
        msg = findViewById(R.id.textView3);
        tit = findViewById(R.id.textView2);
        butStart = findViewById(R.id.button3);
        butStop = findViewById(R.id.button4);
        butStop.setEnabled(false);
    }
    public void button3Handler(View view)
    {
        butStop.setEnabled(true);
        handler = new Handler();
        runAble = new Runnable(){
            @Override
            public void run()
            {
                counter.setText(String.valueOf(num));
                num++;
                handler.postDelayed(runAble, 1000);
            }
        };
        handler.post(runAble);
        butStart.setEnabled(false);
    }
    public void button4Handler(View view)
    {

        handler.removeCallbacks(runAble);
        if (counter.getText().toString().equals("5"))
        {
            this.tit.setText("TEBRİKLER !");
            this.msg.setText(userName + " oyunu kazandın !");
            butStart.setVisibility(android.view.View.VISIBLE);

        }
        else
        {
            this.tit.setText("OYUNU KAYBETTİN");
            this.msg.setText(userName + " tekrar deneyebilirsin !");
        }
    }
    public void button5Handler(View view)
    {
        Intent intentBack = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intentBack);
    }


}
