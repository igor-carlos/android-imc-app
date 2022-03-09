package com.example.calculo_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateIMC(View view) {
        TextView textViewResult = (TextView)findViewById(R.id.textViewResult);
        textViewResult.setText("teste");
    }
}