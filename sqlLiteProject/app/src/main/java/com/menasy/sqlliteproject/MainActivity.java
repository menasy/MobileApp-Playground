package com.menasy.sqlliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        try
        {
            //Database oluşturduk
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //Tablo oluşturduk. musiciansTable nin içinde de bu tabloların stunlarını girdik
            // -> id yi tanımlarken INT kullanılmaz INTEGER Kullanılır.
            database.execSQL("CREATE TABLE IF NOT EXISTS musiciansTable(id INTEGER PRIMARY KEY,name VARCHAR, age INT)");


            //Tablonun içine değer ekledik
//           database.execSQL("INSERT INTO musiciansTable(name,age) VALUES ('Sagopa', 40)");
//           database.execSQL("INSERT INTO musiciansTable(name,age) VALUES ('Ceza', 41)");
//           database.execSQL("INSERT INTO musiciansTable(name,age) VALUES ('Eminem', 42)");

            // Update işlemleri:
             database.execSQL("UPDATE musiciansTable SET age = 73 WHERE name = 'Ceza'");

             // Delete işlemleri:
            database.execSQL("DELETE FROM musiciansTable WHERE id = 3");

            //Verileri alma musiciansTable tablodan al diyor * her şey demek
            //2. argumanı da filtreleme yapmaya yarıyor
//            Cursor cursor = database.rawQuery("SELECT * FROM musiciansTable WHERE name = 'Sagopa'",null);
            // WHERE ile sorgular filtreleniyor.
//            Cursor cursor = database.rawQuery("SELECT * FROM musiciansTable",null);
            Cursor cursor = database.rawQuery("SELECT * FROM musiciansTable WHERE name LIKE 's%'",null);
            // %a yapınca sonu a ile bitenler oluyor. a% yapınca da başında a harfi olanları getiriyor

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIX = cursor.getColumnIndex("id");

            // Cursor databasede geziyor
            while (cursor.moveToNext())
            {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIX));
            }
            cursor.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}