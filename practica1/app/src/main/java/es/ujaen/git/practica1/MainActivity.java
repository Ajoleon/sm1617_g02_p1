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
    Boolean autenticado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Establecemos la vista del activity_main
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentById(R.id.main_frame);

        if(!autenticado){//Si no está ya autenticado

            //Añadimos el fragmento al main_frame
            if(f==null){
                //No hay ningun fragmento
                AuthFragment au = AuthFragment.newInstance("pepe", "12345");
                ft.add(R.id.main_frame, au);//Si no había ninguno antes

            }/*else{
                //Si había algún fragmento antes, se elimina y se añade (se da en el recreado)
                ft.remove(f);
                ft.add(R.id.main_frame, au);
            }*/

            ft.addToBackStack(null);

            ft.commit();//Al recrear la actividad hace falta, ya que deja lo que habíamos escrito.

            autenticado = true;
        }

        listview();

    }

    public void listview(){
        //String para listview
        final String[] opciones = { "Explicación", "Autenticación"};

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, opciones);
        //Adaptador para listview

        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id){
                //Acciones necesarias al hacer click
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment f = fm.findFragmentById(R.id.main_frame);
                Explanation e;

                switch (position){

                    case 0:
                        e = Explanation.newInstance();

                        if(f!=null){
                            //Si había algún fragmento antes, se elimina y se añade
                            ft.remove(f);
                            ft.add(R.id.main_frame, e);
                        }else{
                            ft.add(R.id.main_frame, e);//Si no había ninguno antes
                        }
                        ft.addToBackStack(null);
                        ft.commit();
                        autenticado = false;//hace que se reinicie la actividad de autenticar
                        break;
                    case 1:

                        if(!autenticado){//Si no es false es que había un fragmento de autenticacion antes
                            //Añadimos el fragmento al main_frame

                            //Si había entrDO ANTERIORMENTE EN LA EXPLICACION
                            AuthFragment au = AuthFragment.newInstance("pepe", "12345");
                            ft.replace(R.id.main_frame, au);//Si no había ninguno antes

                            ft.addToBackStack(null);

                            ft.commit();//Al recrear la actividad hace falta

                            autenticado = true;
                        }
                        break;
                }

                String texto= String.valueOf(a.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, texto +", con posicion: "+ position, Toast.LENGTH_SHORT).show();

            }
        });
    }


}


