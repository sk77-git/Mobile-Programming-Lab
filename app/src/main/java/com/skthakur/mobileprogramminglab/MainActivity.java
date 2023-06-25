package com.skthakur.mobileprogramminglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skthakur.mobileprogramminglab.lab1.CounterAppActivity;
import com.skthakur.mobileprogramminglab.lab2.BMICalculatorActivity;
import com.skthakur.mobileprogramminglab.lab3.IntentsActivity;
import com.skthakur.mobileprogramminglab.lab4.CrudSqliteActivity;
import com.skthakur.mobileprogramminglab.lab5.VolleyPhpActivity;
import com.skthakur.mobileprogramminglab.lab6.VolleyApiActivity;

public class MainActivity extends AppCompatActivity {
    Button lab1,lab2,lab3,lab4,lab5,lab6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lab1= findViewById(R.id.btn_lab1);
        lab2= findViewById(R.id.btn_lab2);
        lab3= findViewById(R.id.btn_lab3);
        lab4= findViewById(R.id.btn_lab4);
        lab5= findViewById(R.id.btn_lab5);
        lab6= findViewById(R.id.btn_lab6);

        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CounterAppActivity.class));
            }
        });
        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BMICalculatorActivity.class));
            }
        });
        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IntentsActivity.class));
            }
        });
        lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CrudSqliteActivity.class));
            }
        });
        lab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VolleyPhpActivity.class));
            }
        });
        lab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VolleyApiActivity.class));
            }
        });
    }
}