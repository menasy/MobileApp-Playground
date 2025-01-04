package com.menasy.mixedprogram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    SharedPreferences shrdPrf;
    String userName;
    String pass;
    EditText txtUser;
    EditText txtPass;
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
       shrdPrf = this.getSharedPreferences("com.menasy.mixedprogram", Context.MODE_PRIVATE);
       txtUser = findViewById(R.id.editTextText);
       txtPass = findViewById(R.id.editTextTextPassword);
    }

    public void button1Handler(View view)
    {
        String strPass = txtPass.getText().toString();
        String strUsr = txtUser.getText().toString();
        if (!strUsr.matches("") && !strPass.matches(""))
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Islem");
            alert.setMessage("Emin Misiniz");
            alert.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(MainActivity.this, "Kayıt Yapılmadı",Toast.LENGTH_LONG).show();
                    txtUser.setText("");
                    txtPass.setText("");
                }
            });
            alert.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(MainActivity.this, "Kayıt Basarılı",Toast.LENGTH_LONG).show();
                    MainActivity.this.shrdPrf.edit().putString("saveUsr",strUsr).apply();
                    MainActivity.this.shrdPrf.edit().putString("savePass",strPass).apply();
                    txtUser.setText("");
                    txtPass.setText("");
                }
            });
            alert.show();
        }

    }
    public void button2Handler(View view)
    {
        String strPass = txtPass.getText().toString();
        String strUsr = txtUser.getText().toString();
        if (!strUsr.matches("") && !strPass.matches(""))
        {
            if (strUsr.equals(this.shrdPrf.getString("saveUsr","")))
            {
                Toast.makeText(this,"Giriş Başarlı",Toast.LENGTH_LONG).show();
                // Bulunduğum kısım , Gitmek istediğim kısım
                // intent (Amaç)
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("inputUser",strUsr);
                startActivity(intent);
            }
            else
            {
                txtUser.setText("");
                txtPass.setText("");
                Toast.makeText(this,"Hatalı şifre ya da kullanıcı adı",Toast.LENGTH_LONG).show();
            }

        }
    }
}
