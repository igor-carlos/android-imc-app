package com.example.calculo_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
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

    protected int getWeightValue() {
        String strWeight = editTextNumberWeight.getText().toString();
        if (strWeight.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Entrada inválida")
                    .setMessage("O valor do peso está errado, digite novamente !")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            editTextNumberWeight.requestFocus();
            throw new IllegalArgumentException("weight can not be a empty string");
        }
        return Integer.parseInt(strWeight);
    }

    protected float getHeightValue() {
        String strHeight = editTextNumberHeight.getText().toString();
        if (strHeight.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Entrada inválida")
                    .setMessage("O valor da altura está errado, digite novamente !")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            editTextNumberHeight.requestFocus();
            throw new IllegalArgumentException("height can not be a empty string");
        }
        return Float.parseFloat(strHeight);
    }

    protected void bindFunctionalityToButtons() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int weight = getWeightValue();
                    float height = getHeightValue();
                    float imc = calculateIMC(weight, height);
                    String result = findResultByIMC(imc);
                    showResult(result);
                    clearEntries();
                } catch (Exception e) {
                    Log.e("[ERROR]:", e.toString());
                }
            }
        });
    }

    protected Float calculateIMC(int weight, float height) {
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

    protected void clearEntries() {
        editTextNumberWeight.setText("");
        editTextNumberHeight.setText("");
    }
}
