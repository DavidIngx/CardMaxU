package com.numa.cardmax.cardmaxu.Fragmentos;


import android.annotation.SuppressLint;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.numa.cardmax.cardmaxu.MainActivity;
import com.numa.cardmax.cardmaxu.ObjetoMuro;
import com.numa.cardmax.cardmaxu.R;
import com.numa.cardmax.cardmaxu.adaptador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuroFragment extends Fragment implements RecyclerView.OnTouchListener{

    private DatabaseReference mDatabase;
    private RecyclerView contenedorx;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  ArrayList<ObjetoMuro> Lista;
    private adaptador xx;
    private long startClickTime;
    private float y;
    private float y1;
    private MainActivity esconder;
    private   View view;


    public MuroFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_muro, container, false);
        setHasOptionsMenu(true);
        esconder = (MainActivity)getActivity() ;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Lista  = new ArrayList<ObjetoMuro>();
        xx = new adaptador(Lista);
        contenedorx =(RecyclerView) view.findViewById(R.id.recycler_muro);
        contenedorx.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedorx.setAdapter(xx);
        contenedorx.setLayoutManager(layout);

        contenedorx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    y1= event.getY();


                }

                if(event.getAction() == MotionEvent.ACTION_UP){



                    y = event.getY();

                    float scroll;

                    scroll = y1 -y;


                    if(scroll > -1){
                        System.out.println(scroll);
                        esconder.baroculta(v);

                    }else{
                        esconder.barmuestra(v);
                        Toast.makeText(getActivity(),"HALASTE HACIA ABAJO",Toast.LENGTH_SHORT);
                    }

                }





                return false;
            }
        });

/*
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

*/


        ValueEventListener firebaselistener  = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    DataSnapshot muro = dataSnapshot.child("muro_publicaciones");
                    Iterable<DataSnapshot> muroChildren = muro.getChildren();

                    int count=0;
                    for (DataSnapshot murox : muroChildren) {
                        ObjetoMuro p = murox.getValue(ObjetoMuro.class);
                        Lista.add(0, p);
                        count+=1;
                        if (count == 10){
                            break;
                        }
                    }
                xx.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(firebaselistener);






        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }



    /*
        Query q = mDatabase.child("muro_publicaciones")
                .orderByChild("titulo_publicacion")
                .startAt(qx)
                .endAt(qx+"\uf8ff");





                q.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            Lista.removeAll(Lista);
            Iterable<DataSnapshot> muroChildren = dataSnapshot.getChildren();

            int count=0;
            for (DataSnapshot murox : muroChildren) {
                ObjetoMuro p = murox.getValue(ObjetoMuro.class);
                Lista.add(0, p);
                count+=1;
                Toast.makeText(getContext(),"Encontrados .. "+count,Toast.LENGTH_LONG).show();


            }
            xx.notifyDataSetChanged();


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    */






}