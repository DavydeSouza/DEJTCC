package com.example.dejtcc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dejtcc.MainActivity;
import com.example.dejtcc.R;

public class AddProdutos extends AppCompatActivity {

    Button voltar, add;

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
    }

    public void iniciarComponetes(){
        voltar = findViewById(R.id.voltar);
        add = findViewById(R.id.addItem);
    }
}