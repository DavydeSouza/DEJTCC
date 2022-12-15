package com.example.dejtcc.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dejtcc.MainActivity;
import com.example.dejtcc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Listagem extends AppCompatActivity {

    EditText edtbuscar;
    Button btBack;
    Button btuscar;
    RecyclerView recview;
    ArrayList<Model> datalist;
    FirebaseFirestore db;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        edtbuscar =(EditText) findViewById(R.id.buscar);
        btuscar =(Button)findViewById(R.id.btbuscar);
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
        adapter=new myadapter(datalist);
        recview.setAdapter(adapter);

        db=FirebaseFirestore.getInstance();
        db.collection("produtos").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            Model obj=d.toObject(Model.class);
                            datalist.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        btBack = findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Listagem.this, MainActivity.class);
                startActivity(i);
            }
        });
        btuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e= edtbuscar.getText().toString();

                buscarProdutos(e);
            }
        });
    }

    private void buscarProdutos(String produtoDigitado) {
        db.collection("produtos")
                .whereEqualTo("comida",produtoDigitado)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().isEmpty()){
                            for(QueryDocumentSnapshot document : task.getResult()){

                                Toast.makeText(getApplicationContext(),"Produto cadastrado", Toast.LENGTH_LONG).show();

                            }
                        } else{
                            Toast.makeText(getApplicationContext(),"Produto não cadastrado", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}