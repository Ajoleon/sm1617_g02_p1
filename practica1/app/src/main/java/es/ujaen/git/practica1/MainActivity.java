package es.ujaen.git.practica1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fs = getSupportFragmentManager();
        FragmentTransaction ft = fs.beginTransaction();
        AuthFragment au = AuthFragment.newInstance("pepe","123456");//todo esto para ir añadiendo de forma dinamica el fragment
        ft.add(R.id.main_frame,au);
       // ft.disallowAddToBackStack(null);//añade a la pila y se puede volver al fragmento anterior, si hay pila
        ft.commit();

    }




}
