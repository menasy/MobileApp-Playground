package com.menasy.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
{
    EditText t1, t2;
    TextView res;
    String errText = "Enter Number !";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        res = findViewById(R.id.res);

    }
    public boolean isNull()
    {
        if (t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty())
        {
            res.setText(errText);
            return true;
        }
        return false;
    }
    public int[] getNum()
    {
        int[] arry = new int[2];
        arry[0] = Integer.parseInt(t1.getText().toString());
        arry[1] = Integer.parseInt(t2.getText().toString());
        return arry;
    }
    public void plusNum(View view)
    {
        int result;
        int[] arry;
        String newText;
        if (isNull())
            return;
        arry = getNum();
        result = arry[0] + arry[1];
        newText = "Result: " + result;
       res.setText(newText);
    }

    public void minusNum(View view)
    {
        int result;
        int[] arry;
        String newText;

        if (isNull())
            return;
        arry = getNum();
        result = arry[0] - arry[1];
        newText = "Result: " + result;
        res.setText(newText);
    }
    public void multipleNum(View view)
    {
        int result;
        int[] arry;
        String newText;

        if (isNull())
            return;
        arry = getNum();
        result = arry[0] * arry[1];
        newText = "Result: " + result;
        res.setText(newText);
    }
    public void divideNum(View view)
    {
        int result;
        int[] arry;
        String newText;

        if (isNull())
            return;
        arry = getNum();
        result = 0;
        if (arry[1] != 0)
            result = arry[0] / arry[1];
        newText = "Result: " + result;
        res.setText(newText);
    }
}