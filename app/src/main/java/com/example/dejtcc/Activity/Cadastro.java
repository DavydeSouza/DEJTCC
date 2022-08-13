package com.example.dejtcc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.dejtcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {
    private EditText nome, sobrenome,email, password,cpassword;
    private Button btRegistrar, btVoltar;
    private ProgressBar loding;
    String [] mensagens = {"Preencha todos os campos", "Cadastro Realizado"};
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        InciarComponentes();
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cadastro.this,Login.class);
                startActivity(i);
            }
        });
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =nome.getText().toString();
                String suser =sobrenome.getText().toString();
                String e = email.getText().toString();
                String p = password.getText().toString();
                String cp = cpassword.getText().toString();
                if(user.isEmpty() || suser.isEmpty()|| e.isEmpty()|| p.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,mensagens[0],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                    else{
                        CadastrarUsuarios(view);
                    }
                }
        });
    }
    private void CadastrarUsuarios(View v){
        String e = email.getText().toString();
        String p = password.getText().toString();
        String cp = cpassword.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SalvarUsuarios();
                    Snackbar snackbar = Snackbar.make(v ,mensagens[1],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    String erro;
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erro = "Digite uma senha maior que 6 digitos";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "O email digitado esta incorreto";
                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "O email ja foi cadastrado";
                    }
                    catch(Exception e){
                        erro ="Erro ao cadastrar";
                    }
                    Snackbar snackbar = Snackbar.make(v ,erro,Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }

            }
        });

    }
    private void SalvarUsuarios(){
        String user =nome.getText().toString();
        String suser =sobrenome.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios =new HashMap<>();
        usuarios.put("nomes",user);
        usuarios.put("sobrenome",suser);

        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("usuarios").document(UserID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao Cadastrar");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_erro","Falha ao Salvar" + e.toString());

            }
        });

    }

    private void InciarComponentes(){
        nome =  findViewById(R.id.nomeUsuario);
        sobrenome =  findViewById(R.id.sobrenomeUsuario);
        email =  findViewById(R.id.email);
        password =  findViewById(R.id.spassword);
        cpassword =  findViewById(R.id.cpassword);
        btRegistrar = findViewById(R.id.btResgistrar);
        btVoltar = findViewById(R.id.btVoltar);
        loding = findViewById(R.id.lodingR);
    }

}