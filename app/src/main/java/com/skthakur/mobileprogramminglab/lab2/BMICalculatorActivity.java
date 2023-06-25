package com.skthakur.mobileprogramminglab.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skthakur.mobileprogramminglab.R;

public class BMICalculatorActivity extends AppCompatActivity {
    EditText etAge,etHeight,etWeight;
    Button btnCalculate;
    TextView tvBmiResult;
    RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        etAge=findViewById(R.id.et_age);
        etHeight=findViewById(R.id.et_height);
        etWeight=findViewById(R.id.et_weight);
        btnCalculate=findViewById(R.id.btn_calculate);
        tvBmiResult=findViewById(R.id.tv_bmi_result);
        radioGroupGender=findViewById(R.id.radio_group_gender);
        tvBmiResult.setText("");

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ageText = etAge.getText().toString().trim();
                String heightText = etHeight.getText().toString().trim();
                String weightText = etWeight.getText().toString().trim();

                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                String gender;
                if (selectedGenderId == R.id.radio_male) {
                    gender = "Male";
                } else if (selectedGenderId == R.id.radio_female) {
                    gender = "Female";
                } else {
                    Toast.makeText(BMICalculatorActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ageText.isEmpty()) {
                    etAge.setError("Please enter your age");
                    return;
                }
                if (heightText.isEmpty()) {
                    etHeight.setError("Please enter your height");
                    return;
                }
                if (weightText.isEmpty()) {
                    etWeight.setError("Please enter your weight");
                    return;
                }
                double age = Double.parseDouble(ageText);
                double height = Double.parseDouble(heightText);
                double heightInM = height/100;
                double weight = Double.parseDouble(weightText);

                double bmi;
                bmi = weight/(heightInM*heightInM);
                tvBmiResult.setText("Your BMI is: " + String.format("%.2f", bmi));
            }
        });
    }
}