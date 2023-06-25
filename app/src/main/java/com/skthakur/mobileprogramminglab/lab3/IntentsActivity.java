package com.skthakur.mobileprogramminglab.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skthakur.mobileprogramminglab.R;

public class IntentsActivity extends AppCompatActivity {
    Button btnNextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        btnNextActivity= findViewById(R.id.btn_next_activity);
        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntentsActivity.this, IntentsActivity2.class));
            }
        });


    }
}