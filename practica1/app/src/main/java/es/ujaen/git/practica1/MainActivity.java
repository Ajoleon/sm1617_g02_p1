package es.ujaen.git.practica1;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
//Para la barra de acción
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    /**Método que se inicia al crear la actividad principal
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Establecemos la vista del activity_main
        setContentView(R.layout.activity_main);

        //Hace falta paquete v7.widget.toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //Fragmento que existe en el main_frame
        Fragment f = fm.findFragmentById(R.id.main_frame);

        if(f==null){//Si no es null es que había un fragmento antes
            //Iniciamos las variables de usuario y contraseña
            AuthFragment au = AuthFragment.newInstance("pepe", "12345");
            //Añadimos el fragmento al main_frame
            ft.add(R.id.main_frame, au);
            ft.addToBackStack(null);
        }

        ft.commit();
    }



}


