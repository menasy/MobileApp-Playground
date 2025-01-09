package com.menasy.recviewlandbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.menasy.recviewlandbook.databinding.ActivityDetailsActivtyBinding;

public class DetailsActivty extends AppCompatActivity {

    private ActivityDetailsActivtyBinding bnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityDetailsActivtyBinding.inflate(getLayoutInflater());
        View view = bnd.getRoot();
        setContentView(view);

        Land selectedLand = Singelton.getInstance().getLand();
        bnd.textView.setText(selectedLand.name);
        bnd.textView2.setText(selectedLand.country);
        bnd.imageView.setImageResource(selectedLand.imgNo);
    }
}