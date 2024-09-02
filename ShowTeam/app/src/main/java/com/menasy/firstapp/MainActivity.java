package com.menasy.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    List<Integer>  images = List.of(R.drawable.imagefb, R.drawable.livakovic, R.drawable.osayi, R.drawable.becao,
            R.drawable.djiku, R.drawable.ferdi, R.drawable.ismail, R.drawable.fred,
            R.drawable.sebastian_symanzski, R.drawable.irfan, R.drawable.tadic,
            R.drawable.dzeko, R.drawable.ilk11);
    Integer index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
    public  void changeImage(View view)
    {
        ImageView fbView = findViewById(R.id.imageFb);
        fbView.setImageResource(images.get(index));
        index = (index + 1) % images.size();
    }
}