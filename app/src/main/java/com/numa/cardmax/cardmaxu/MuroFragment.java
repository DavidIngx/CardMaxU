package com.numa.cardmax.cardmaxu;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuroFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public MuroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_muro, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_muro);




        ArrayList<ObjetoMuro> Lista  = new ArrayList<ObjetoMuro>();
        Lista.add(new ObjetoMuro("Nier: La Automata","contenido", "02/05/18", "https://images.g2a.com/newlayout/1080x1080/1x1x0/5b5a9c5e75d6/598da7f4ae653a0974657054","sin video",2,10,0));

        Lista.add(new ObjetoMuro("Nier","Nier: Automata (ニーア オートマタ Nīa Ōtomata?) es un videojuego de rol de acción desarrollado por PlatinumGames y publicado por Square Enix para PlayStation 4, Microsoft Windows y Xbox One.", "08/05/18", "https://amaterasu.com.ar/wp-content/uploads/2018/02/nier-automata-2.jpg","sin video",2,100,0));





        RecyclerView contenedorx =(RecyclerView) view.findViewById(R.id.recycler_muro);
        contenedorx.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedorx.setAdapter(new adaptador(Lista) );
        contenedorx.setLayoutManager(layout);




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
