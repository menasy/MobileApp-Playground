package com.menasy.landmarkbookjava;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.progressindicator.BaseProgressIndicator;
import com.menasy.landmarkbookjava.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<Landmark> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        crateLst();
        // Listeyi ekranda bastırmak için adapter kullandık.
        ArrayAdapter aryAdp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                lst.stream().map(landmark -> landmark.name).collect(Collectors.toList()));
        binding.listView.setAdapter(aryAdp); //Adapteri setledi

        // Tıklandığında işlem yapmak için
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("landmark",lst.get(position));
                startActivity(intent);
            }
        });

    }
    private void crateLst()
    {
        Landmark eyfel = new Landmark("Eyfel Kulesi","Fransa - Paris",R.drawable.eyfel);
        Landmark fenerStad = new Landmark("Ülker Şükrü Saraçoğlu Stadı","Türkiye - İstanbul", R.drawable.fenerbahcestadi);
        Landmark kasimiye = new Landmark("Kasımiye Medresesi","Türkiye - Mardin",R.drawable.kasimiye);
        Landmark piramit = new Landmark("Mısır Piramitleri","Mısır - Gize",R.drawable.piramit);
        Landmark pissa = new Landmark("Pizza Kulesi","İtalya - Pisa",R.drawable.pissa);
        Landmark dream = new Landmark("Menasy Kuleleri","Türkiye - İstanbul",R.drawable.skyland);

        lst = new ArrayList<>();
        lst.add(eyfel);
        lst.add(fenerStad);
        lst.add(kasimiye);
        lst.add(piramit);
        lst.add(pissa);
        lst.add(dream);
    }
}