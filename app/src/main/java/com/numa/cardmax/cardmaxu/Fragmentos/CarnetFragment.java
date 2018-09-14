package com.numa.cardmax.cardmaxu.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numa.cardmax.cardmaxu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarnetFragment extends Fragment {


    public CarnetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carnet, container, false);
    }

}
