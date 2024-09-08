package com.menasy.allertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    EditText username;
    EditText password;
    SharedPreferences shrPrf;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        shrPrf = this.getSharedPreferences("package com.menasy.allertdialog", Context.MODE_PRIVATE);
        this.username = findViewById(R.id.editTextText);
        this.password = findViewById(R.id.editTextTextPassword);
    }

    public void register(View view)
    {

        String user;
        String pass;

        user = username.getText().toString();
        pass = password.getText().toString();

        System.out.println("User1:" + user);
        System.out.println("paswd1:" + pass);
        if (user.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(this, "Username or Password is empty !", Toast.LENGTH_SHORT).show();
            return ;
        }
        shrPrf.edit().putString("newUser", user).apply();
        shrPrf.edit().putString("newPass", pass).apply();
        Toast.makeText(this, "Register Successfully !", Toast.LENGTH_SHORT).show();
    }

    public void login(View view)
    {
        String user;
        String pass;

        user = username.getText().toString();
        pass = password.getText().toString();
        if (user.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(this, "Username or Password is empty !", Toast.LENGTH_SHORT).show();
            return ;
        }
        if (user.equals(shrPrf.getString("newUser", "admin")) && pass.equals(shrPrf.getString("newPass", "123")))
            Toast.makeText(this, "Login Successfully !", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Login Failed !", Toast.LENGTH_SHORT).show();
    }

    public void reset(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Reset");
        alert.setMessage("Are you sure to reset user and password ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shrPrf.edit().putString("newUser", "admin").apply();
                shrPrf.edit().putString("newPass", "admin123").apply();
                username.setText("admin");
                password.setText("admin123");
                Toast.makeText(getApplicationContext(),
                        "Reset Successfully ! new password is 'admin123'\nplease press 'Register' button",
                        Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Reset Failed !", Toast.LENGTH_LONG).show();
            }
        });
        alert.show();
    }
}