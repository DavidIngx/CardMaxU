package com.numa.cardmax.cardmaxu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout mainFrame;
    private MuroFragment murofrag;
    private NotificacionFragment notifrag;
    private PerfilFragment perfilfrag;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFrame(perfilfrag);
                    return true;
                case R.id.navigation_dashboard:
                    setFrame(murofrag);
                    return true;
                case R.id.navigation_notifications:
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        murofrag = new MuroFragment();
        perfilfrag = new PerfilFragment();
        notifrag = new NotificacionFragment();
        mainFrame = (FrameLayout) findViewById(R.id.mainfragment);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
