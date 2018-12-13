package com.numa.cardmax.cardmaxu.Fragmentos;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.numa.cardmax.cardmaxu.MuroMainActivity;
import com.numa.cardmax.cardmaxu.ObjetoMuro;
import com.numa.cardmax.cardmaxu.R;
import com.numa.cardmax.cardmaxu.adaptador;

import java.util.ArrayList;


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
    private MuroMainActivity esconder;
    private   View view;


    public MuroFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.muro_fragment_muro, container, false);
        setHasOptionsMenu(true);
        esconder = (MuroMainActivity)getActivity() ;
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


                    for (DataSnapshot murox : muroChildren) {
                        ObjetoMuro p = murox.getValue(ObjetoMuro.class);
                        Lista.add(0, p);


                    }


                Lista.subList(10,Lista.size()).clear();
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

    */






}
