package com.example.dejtcc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dejtcc.MainActivity;
import com.example.dejtcc.R;

public class Termo extends AppCompatActivity {
    Button btback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo);
        btback = findViewById(R.id.bbbvoltar);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Termo.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}