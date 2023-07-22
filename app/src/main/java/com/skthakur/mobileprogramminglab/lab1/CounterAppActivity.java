package com.skthakur.mobileprogramminglab.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skthakur.mobileprogramminglab.R;

public class CounterAppActivity extends AppCompatActivity {
    FloatingActionButton btnAdd, btnSubtract, btnReset;
    TextView tvCount;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_app);
        btnAdd = findViewById(R.id.btn_add);
        btnSubtract = findViewById(R.id.btn_subtract);
        tvCount = findViewById(R.id.tv_count_value);
        btnReset = findViewById(R.id.btn_reset);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                tvCount.setText(String.valueOf(count));
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                tvCount.setText(String.valueOf(count));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                tvCount.setText(String.valueOf(count));
            }
        });
    }
}