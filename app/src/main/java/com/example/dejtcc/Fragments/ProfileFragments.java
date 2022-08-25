package com.example.dejtcc.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dejtcc.Activity.Login;
import com.example.dejtcc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ProfileFragments extends Fragment {

    Button btSair;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    View view;
    private FirebaseAuth mAuth;
    TextView nameUser;


   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       IniciarComponents();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_fragments, container, false);
        btSair  = (Button) view.findViewById(R.id.btSair);
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i = new Intent(getActivity(), Login.class);
                Toast.makeText(getContext(), "Obrigado por usar o Sistema", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        RecuperaInfo();
    }

    private void IniciarComponents(){

       mAuth = FirebaseAuth.getInstance();
    }
    public void RecuperaInfo(){

        String UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(UserID != null){
            nameUser =(TextView) getActivity().findViewById(R.id.nameUser);
            DocumentReference documentReference = db.collection("usuarios").document(UserID);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(value != null){
                    nameUser.setText(value.getString("nomes").toString());
                    }
                }
            });
        }
    }
}


