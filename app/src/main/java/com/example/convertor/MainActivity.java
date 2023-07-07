package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.button1)
        {
            Intent it1 = new Intent(this, Infixtopostfix.class);
            startActivity(it1);
        }
        else if(id==R.id.button2)
        {
            Intent it2 = new Intent(this, Infixtoprefix.class);
            startActivity(it2);
        }
        else if(id==R.id.button3)
        {
            Intent it3 = new Intent(this, Postfixtoinfix.class);
            startActivity(it3);
        }
        else if(id==R.id.button4)
        {
            Intent it4 = new Intent(this, Prefixtoinfix.class);
            startActivity(it4);
        }
    }
}