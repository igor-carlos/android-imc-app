package com.example.calculo_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumberWeight;
    TextView textViewWeight;
    EditText editTextNumberHeight;
    TextView textViewHeight;
    Button buttonCalculate;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
        bindFunctionalityToButtons();
    }

    protected void loadComponents() {
        editTextNumberWeight = findViewById(R.id.editTextNumberWeight);
        textViewWeight = findViewById(R.id.textViewWeight);
        editTextNumberHeight = findViewById(R.id.editTextNumberHeight);
        textViewHeight = findViewById(R.id.textViewHeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
    }

    protected void bindFunctionalityToButtons() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float imc = calculateIMC();
                String result = findResultByIMC(imc);
                showResult(result);
            }
        });
    }

    protected Float calculateIMC() {
        int weight = Integer.parseInt(editTextNumberWeight.getText().toString());
        float height = Float.parseFloat(editTextNumberHeight.getText().toString());
        float heightInMeters = height / 100;
        float imc = weight / (heightInMeters * heightInMeters);
        return imc;
    }

    protected String findResultByIMC(Float imc) {
        if (imc < 16) {
            return "Magreza grave";
        }
        if (imc >= 16 && imc < 17) {
            return "Magreza moderada";
        }
        if (imc >= 17 && imc < 18.5) {
            return "Magreza leve";
        }
        if (imc >= 18.5 && imc < 25) {
            return "Saudável";
        }
        if (imc >= 25 && imc < 30) {
            return "Sobrepeso";
        }
        if (imc >= 30 && imc < 35) {
            return "Obesidade Grau I";
        }
        if (imc >= 35 && imc < 40) {
            return "Obesidade Grau II";
        }
        if (imc >= 40) {
            return "Obesidade Grau IIII (mórbida)";
        }
        return "Não foi possível achar o resultado do imc: " + imc.toString();
    }

    protected void showResult(String result) {
        textViewResult.setText(result);
    }
}