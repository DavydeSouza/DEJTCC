package com.example.dejtcc.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dejtcc.Activity.AddProdutos;
import com.example.dejtcc.Activity.Listagem;
import com.example.dejtcc.MainActivity;
import com.example.dejtcc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Button listButton;
    FloatingActionButton floatingActionButton;
  //  ArrayList<Produtos> arrayprodutos;
    View view;
    RecyclerView recyclerView;
   // MyAdapter myAdapter;
    ProgressDialog progressDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //progressDialog = new ProgressDialog(getActivity());
        //progressDialog.setCancelable(false);
        //progressDialog.setMessage("Pegando Dados");
        //progressDialog.show();

        /*recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        arrayprodutos = new ArrayList<Produtos>();
        produtoAdapter = new ProdutoAdapter(getActivity(), arrayprodutos);

        recyclerView.setAdapter(produtoAdapter);
*/

        listButton = view.findViewById(R.id.listItem);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Listagem.class);
                startActivity(i);
            }
        });

       floatingActionButton = view.findViewById(R.id.fbADD);
       floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddProdutos.class);
                startActivity(i);
            }
        });
       //Listagem();
        return view;
    }

    private void Listagem() {

        db.collection("produtos").orderBy("comida", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null){


                    Log.e("Firestore Error",error.getMessage());
                    return;

                }

                for (DocumentChange dc : value.getDocumentChanges()){

                    if (dc.getType() == DocumentChange.Type.ADDED){

                        //arrayprodutos.add(dc.getDocument().toObject(Produtos.class));
                    }

                  //  myAdapter.notifyDataSetChanged();

                }


            }
        });
    }
}