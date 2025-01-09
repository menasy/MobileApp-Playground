package com.menasy.recviewlandbook;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.menasy.recviewlandbook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bnd;
    ArrayList<Land> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        crateList();
        bnd = ActivityMainBinding.inflate(getLayoutInflater());
        View view = bnd.getRoot();
        setContentView(view);

        bnd.recView.setLayoutManager(new LinearLayoutManager(this));
        // Bizim oluşturduğumuz class (LandAdapter) ı kullanarak Adapter oluşturuyoruz
        LandAdapter landAdp = new LandAdapter(lst);
        //recView = oluşturduğumuz RecyclerView nesnesi
        bnd.recView.setAdapter(landAdp);

    }

    private  void crateList()
    {
        Land eyfel = new Land("Eyfel Kulesi","Fransa - Paris",R.drawable.eyfel);
        Land fenerStad = new Land("Ülker Şükrü Saraçoğlu Stadı","Türkiye - İstanbul", R.drawable.fenerbahcestadi);
        Land kasimiye = new Land("Kasımiye Medresesi","Türkiye - Mardin",R.drawable.kasimiye);
        Land piramit = new Land("Mısır Piramitleri","Mısır - Gize",R.drawable.piramit);
        Land pissa = new Land("Pizza Kulesi","İtalya - Pisa",R.drawable.pissa);
        Land dream = new Land("Menasy Kuleleri","Türkiye - İstanbul",R.drawable.skyland);

        lst = new ArrayList<>();
        lst.add(eyfel);
        lst.add(fenerStad);
        lst.add(kasimiye);
        lst.add(piramit);
        lst.add(pissa);
        lst.add(dream);
    }
}