package com.numa.cardmax.cardmaxu;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuroFragment extends Fragment {

    private DatabaseReference mDatabase;
    private RecyclerView contenedorx;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  ArrayList<ObjetoMuro> Lista;
    private adaptador xx;

    public MuroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_muro, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();



        Lista  = new ArrayList<ObjetoMuro>();


        xx = new adaptador(Lista);


        contenedorx =(RecyclerView) view.findViewById(R.id.recycler_muro);
        contenedorx.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedorx.setAdapter(xx);
        contenedorx.setLayoutManager(layout);




        ValueEventListener firebaselistener  = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {






                    DataSnapshot muro = dataSnapshot.child("muro_publicaciones");

                    Iterable<DataSnapshot> muroChildren = muro.getChildren();


                    for (DataSnapshot murox : muroChildren) {
                        ObjetoMuro p = murox.getValue(ObjetoMuro.class);
                        Lista.add(p);
                    }


              
                xx.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(firebaselistener);


        FloatingActionButton agregar ;
        agregar =  (FloatingActionButton) view.findViewById(R.id.agregar_muro);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getActivity(), CrearPublicacion.class);
                startActivity(intento);
            }
        });

        return view;
    }


}
