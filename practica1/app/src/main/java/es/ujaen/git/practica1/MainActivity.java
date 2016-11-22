package es.ujaen.git.practica1;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

//Para la barra de acción
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


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


        listview();

    }

    public void listview(){


        //String para listview
        final String[] opciones = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, opciones);
        //Adaptador para listview

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id){
                //Acciones necesarias al hacer click
                String texto= String.valueOf(a.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();

            }
        });
    }


}


