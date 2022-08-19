package com.example.dejtcc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dejtcc.MainActivity;
import com.example.dejtcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView Rbtn;
    private EditText email, senha;
    Button Lbtn;
    CheckBox check_box;
    ProgressBar loding_login;
    String [] mensagem= {"Preenchar os todos os campos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        IniciarComponetes();
        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Cadastro.class);
                startActivity(i);
            }
        });
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        Lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String s = senha.getText().toString();
                if (e.isEmpty() || s.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view ,mensagem[0],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    AutenticarUser(view);
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser userAtual = FirebaseAuth.getInstance().getCurrentUser();
        if(userAtual != null){
           TelaPrincipal();
        }
    }

    private void AutenticarUser(View view) {
        String e = email.getText().toString();
        String s = senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(e, s).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    loding_login.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    }, 1500);

                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }
                    catch(Exception e){
                        erro ="Erro ao fazer Login";
                    }
                    Snackbar snackbar = Snackbar.make(view ,erro,Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void TelaPrincipal() {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
        finish();
    }


    private void IniciarComponetes() {
        email = findViewById(R.id.edit_email);
        senha = findViewById(R.id.edit_password);
        Lbtn = findViewById(R.id.btn_login);
        Rbtn = findViewById(R.id.btn_registrar);
        loding_login= findViewById(R.id.loding_login);
        check_box = findViewById(R.id.check_password);
    }
}

