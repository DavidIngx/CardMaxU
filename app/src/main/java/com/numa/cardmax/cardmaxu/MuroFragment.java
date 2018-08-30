package com.numa.cardmax.cardmaxu;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuroFragment extends Fragment {

    public MuroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_muro, container, false);
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
