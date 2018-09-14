package com.numa.cardmax.cardmaxu;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BusquedaMuro extends AppCompatActivity {


    private SearchView searchView;

    private DatabaseReference mDatabase;
    private RecyclerView contenedorx;
    private ArrayList<ObjetoMuro> Lista;
    private adaptador xx;
    private Toolbar barraact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_muro);


        searchView=(SearchView)findViewById(R.id.searchv);
        searchView.onActionViewExpanded();
        contenedorx =(RecyclerView) findViewById(R.id.recycler_busqueda);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Lista  = new ArrayList<ObjetoMuro>();
        xx = new adaptador(Lista);
        contenedorx.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedorx.setAdapter(xx);
        contenedorx.setLayoutManager(layout);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                if (newText != null && newText.equals("")){
                    System.out.println("este es el dato ("+newText);

                }else {
                    Query q = mDatabase.child("muro_publicaciones")
                            .orderByChild("titulo_publicacion")
                            .startAt(newText)
                            .endAt(newText + "\uf8ff");


                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Lista.removeAll(Lista);
                            Iterable<DataSnapshot> muroChildren = dataSnapshot.getChildren();

                            int count = 0;
                            for (DataSnapshot murox : muroChildren) {
                                ObjetoMuro p = murox.getValue(ObjetoMuro.class);
                                Lista.add(0, p);
                                count += 1;


                            }
                            Toast.makeText(getApplicationContext(), "Encontrados :" + count, Toast.LENGTH_LONG).show();

                            xx.notifyDataSetChanged();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                return false;
            }
        });











    }


    public void cerrar(View v){
        Intent intento;
        intento = new Intent(BusquedaMuro.this, MainActivity.class);
        startActivity(intento);
        overridePendingTransition(R.anim.push_right_in,R.anim.hold);
        finish();

    }


}
