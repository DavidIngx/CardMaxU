package com.numa.cardmax.cardmaxu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.numa.cardmax.cardmaxu.Fragmentos.CarnetFragment;
import com.numa.cardmax.cardmaxu.Fragmentos.MuroFragment;
import com.numa.cardmax.cardmaxu.Fragmentos.PerfilFragment;

public class MuroMainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout mainFrame;
    private MuroFragment murofrag;
    private PerfilFragment notifrag;
    private CarnetFragment perfilfrag;
    private Toolbar mTopToolbar;
    private EditText searchbar;
    private BottomNavigationView navigation;
    private FloatingActionButton agregar ;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    agregar.setVisibility(View.INVISIBLE);
                    setFrame(perfilfrag);
                    return true;
                case R.id.navigation_dashboard:
                    agregar.setVisibility(View.VISIBLE);
                    setFrame(murofrag);
                    return true;
                case R.id.navigation_notifications:
                    agregar.setVisibility(View.INVISIBLE);
                    setFrame(notifrag);
                    return true;


            }
            return false;
        }

        private void setFrame(Fragment fragment) {
            FragmentTransaction transcicion = getSupportFragmentManager().beginTransaction();
            transcicion.replace(R.id.mainfragment, fragment);
            transcicion.commit();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.muro_navigation, menu);



        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muro_activity_main);






        searchbar = (EditText)findViewById(R.id.editSearch);
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        murofrag = new MuroFragment();
        perfilfrag = new CarnetFragment();
        notifrag = new PerfilFragment();
        mainFrame = (FrameLayout) findViewById(R.id.mainfragment);
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        agregar =  (FloatingActionButton) findViewById(R.id.floatingActionButton3);



        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento;
                intento = new Intent(MuroMainActivity.this, CrearPublicacion.class);
                startActivity(intento);
                overridePendingTransition(R.anim.goup, R.anim.hold);
            }
        });


        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento;
                intento = new Intent(MuroMainActivity.this, BusquedaMuro.class);
                startActivity(intento);
                overridePendingTransition(R.anim.goup, R.anim.hold);

            }
        });





    }




    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.actualizar) {
            Toast.makeText(getApplicationContext(),"Actualizado",Toast.LENGTH_SHORT).show();
            FragmentTransaction transcicion = getSupportFragmentManager().beginTransaction();
            transcicion.detach(murofrag);
            transcicion.attach(murofrag);
            transcicion.commit();

            return true;
        }



        return super.onOptionsItemSelected(item);


    }





    public void baroculta(View v){
        agregar.setVisibility(View.INVISIBLE);
        navigation.setVisibility(View.INVISIBLE);

    }

    public void barmuestra(View v){
        agregar.setVisibility(View.VISIBLE);
        navigation.setVisibility(View.VISIBLE);

    }
}
