package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

public class CalculadoraImc extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInformation;
    private TextView tvResult;
    private EditText textHeight;
    private EditText textWeight;
    private Button btnCalculator;
    private ImageView imageState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_imc);

        Intent intent = getIntent();
        String name = intent.getStringExtra("nameCalculator");
        String surname = intent.getStringExtra("surnameCalculator");
        String email = intent.getStringExtra("emailCalculator");
        String message = "Hola " +name+" es un gusto tenerte aquí, su email para su el informe es:"+email;

        tvInformation = findViewById(R.id.tvInformation);
        tvResult = findViewById(R.id.tvResult);
        textHeight = findViewById(R.id.textHeight);
        textWeight = findViewById(R.id.textWeight);
        imageState = findViewById(R.id.imageState);
        btnCalculator = findViewById(R.id.btnCalculator);
        tvInformation.setText(message);

        btnCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String height = textHeight.getText().toString();
        String weight = textWeight.getText().toString();

        if (v.getId() == R.id.btnCalculator){
            if (height.isEmpty()){
                CustomToastView.makeInfoToast(this,"La altura no debe estar vacia",R.layout.custom_toast).show();
                return;
            }

            if (weight.isEmpty()){
                CustomToastView.makeInfoToast(this,"La peso no debe estar vacio",R.layout.custom_toast).show();
                return;
            }
            Double result = null;

            Double heightNumber = Double.parseDouble(height);
            Double weightNumber = Double.parseDouble(weight);

            Double formula = (weightNumber/Math.pow(heightNumber,2));
            result = formula;

            imageState = (ImageView) findViewById(R.id.imageState);

            if (result != null)
                tvResult.setText(String.valueOf(result.doubleValue()));
                if (result < 18.5){
                    imageState.setImageResource(R.drawable.thin);
                }else if(result >= 18.5 && result <= 24.9){
                    imageState.setImageResource(R.drawable.normal);
                }else{
                    imageState.setImageResource(R.drawable.fat);
                }
        }
    }
}