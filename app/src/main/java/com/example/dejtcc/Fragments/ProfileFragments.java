package com.example.dejtcc.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dejtcc.Activity.Login;
import com.example.dejtcc.R;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragments extends Fragment {

    Button btSair;
    View view;
    private FirebaseAuth mAuth;

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
    private void IniciarComponents(){
        mAuth = FirebaseAuth.getInstance();
    }
}


