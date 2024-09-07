package com.menasy.takeage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    EditText takeAge;
    TextView showAge;
    SharedPreferences shPrf;
    String text = "Your age:    ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        shPrf = this.getSharedPreferences("package com.menasy.takeage", Context.MODE_PRIVATE);
        this.takeAge = findViewById(R.id.editTextNumberDecimal);
        this.showAge = findViewById(R.id.textView);
        if (shPrf.getInt("savedAge",0) != 0)
            this.showAge.setText(text + shPrf.getInt("savedAge",0));
        else
            this.showAge.setText(text);
    }
    public void saveAge(View view)
    {
        String ageString = "";
        String  msg;
        int age;

        if (this.takeAge.getText().toString().isEmpty())
            return ;
        ageString = this.takeAge.getText().toString();
        age = Integer.parseInt(ageString);
        msg = this.text + age;
        this.showAge.setText(msg);
        this.shPrf.edit().putInt("savedAge", age).apply();
    }
    public void deleteAge(View view)
    {
        int delAge;
        delAge = shPrf.getInt("savedAge",0);
        if (delAge != 0)
            shPrf.edit().remove("savedAge").apply();
        showAge.setText(text);
    }

}