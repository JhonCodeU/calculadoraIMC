package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAcept;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAcept = findViewById(R.id.btnAcept);
        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtEmail = findViewById(R.id.txtEmail);
        btnAcept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAcept){
            String name = txtName.getText().toString();
            String surname = txtSurname.getText().toString();
            String email = txtEmail.getText().toString();

            if (name.isEmpty()){
                CustomToastView.makeErrorToast(this,"Error al validar el nombre",R.layout.custom_toast).show();
                return;
            }
            if (surname.isEmpty()){
                CustomToastView.makeInfoToast(this,"Error al validar el apellido",R.layout.custom_toast).show();
                return;
            }
            if (email.isEmpty()){
                CustomToastView.makeSuccessToast(this,"Error al validar el email",R.layout.custom_toast).show();
                return;
            }
            //pasar a otra vista y enviar por parametros
            Intent myIntent = new Intent(this, CalculadoraImc.class);
            myIntent.putExtra("nameCalculator", name);
            myIntent.putExtra("surnameCalculator", surname);
            myIntent.putExtra("emailCalculator", email);

            startActivity(myIntent);

        }
    }

    private Boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}