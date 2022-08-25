package com.example.dejtcc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dejtcc.MainActivity;
import com.example.dejtcc.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddProdutos extends AppCompatActivity {


    EditText comida,tipo,descricao,preco,estoque;
    Button voltar, add;
    String produtoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produtos);
        iniciarComponetes();
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddProdutos.this, MainActivity.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarProdutos();
            }
        });
    }

    public void iniciarComponetes(){
        voltar = findViewById(R.id.voltar);
        add = findViewById(R.id.addItem);
        comida = findViewById(R.id.eats);
        tipo = findViewById(R.id.type);
        descricao = findViewById(R.id.description);
        preco = findViewById(R.id.price);
        estoque = findViewById(R.id.inventory);
    }
    private void salvarProdutos(){
        String eats  =comida.getText().toString();
        String type =tipo.getText().toString();
        String description =descricao.getText().toString();
        String price =preco.getText().toString();
        String inventory =estoque.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> produtos = new HashMap<>();
        produtos.put("comida",eats);
        produtos.put("tipo",type);
        produtos.put("descricao",description);
        produtos.put("preco",price);
        produtos.put("estoque",inventory);

        produtoID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("produtos").document(produtoID);

        documentReference.set(produtos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao Adicionar");
                Toast.makeText(AddProdutos.this,"Sucesso ao adicionar",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_erro","Falha ao Salvar" + e.toString());
                Toast.makeText(AddProdutos.this,"Falha ao Salvar" + e.toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}