package es.ujaen.git.practica1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


/**Actividad principal donde montaremos los fragmentos y del que parte un menú
 *
 * @author Pablo Castillo Segura - Antonio José León Sánchez
 * @version Práctica 1. Servicio Móviles. Grado en Ingeniería telemática. Grado en Ingeniería Telemática
 *
 */
public class MainActivity extends AppCompatActivity {
    //Boolean que indica si se ha entrado en el fragmento de autenticación
    Boolean autenticado = true;

    /**Método que se inicia al crear la actividad principal.
     *
     * @param savedInstanceState Instancia donde se guardan los atributos tras un recreado de la actividad
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Establecemos la vista del activity_main, que es un Relative-Layout con el ListView que permite cambiar de fragmento
        setContentView(R.layout.activity_main);


        //Llamamos al Gestor de fragmentos
        FragmentManager fm = getSupportFragmentManager();
        //Comenzamos la transacción de fragmentos
        FragmentTransaction ft = fm.beginTransaction();
        //Encontramos el fragmento principal de la aplicación
        Fragment f = fm.findFragmentById(R.id.main_frame);


        //Si antes no había ningún fragmento
        if(f==null){
            /**Creamos una nueva instancia del fragmento de autenticación, donde se inician los parámetros
             *
             * @see AuthFragment.newInstance() Método donde se crea la nueva instancia del fragmento de autenticación
             */
            AuthFragment au = AuthFragment.newInstance("pepe", "12345");
            //Añadimos el fragmento al main_frame
            ft.add(R.id.main_frame, au);

        }/*else{
            //Si había algún fragmento antes, se elimina y se añade (se da en el recreado)
            ft.remove(f);
            ft.add(R.id.main_frame, au);
        }*/
        //Añadimos null a la pila hacia atrás
        ft.addToBackStack(null);
        //Ejecuta la transacción de fragmentos
        ft.commit();

        //Cambiamos autenticado a true, ya que ha entrado en el fragmento de autenticación
        //autenticado = true;

        //Método para el menú de ListView
        listview();

    }

    /**Método para añadir adaptador a un Listview, donde elegiremos los fragmentos
     *
     */
    public void listview(){
        //String para listview con los títulos de los fragmentos
        final String[] opciones = { "Explicación", "Autenticación"};

        //Creamos el adaptador
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, opciones);

        //Encontramos ListView del fragmento
        final ListView listView = (ListView) findViewById(R.id.list_view);
        //Establecemos adaptador para ese ListView
        listView.setAdapter(adaptador);

        //Establecemos un escuchador de click en los items
        listView.setOnItemClickListener(new OnItemClickListener() {

            /**Método al hacer click en un item de la lista
             *
             * @param a     Adaptador del ListView
             * @param v     Vista
             * @param position  Posición del item clickado
             * @param id    id del item
             */
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id){
                //Llamamos al Gestor de fragmentos
                FragmentManager fm = getSupportFragmentManager();
                //Comenzamos la transacción de fragmentos
                FragmentTransaction ft = fm.beginTransaction();
                //Encontramos el fragmento principal de la aplicación
                Fragment f = fm.findFragmentById(R.id.main_frame);

                //Switch para los distintos casos de los items del ListView. La entrada es la posición del item clickado
                switch (position) {

                    //Caso de posición 0, que es del fragmento de explicación
                    case 0:
                        if(autenticado) {
                            //Se inicializa una nueva instancia del fragmento de explicación
                            Explanation e = Explanation.newInstance();

                            //Se añade fragmento de explicación
                            ft.replace(R.id.main_frame, e);

                            //Añadimos null a la pila hacia atrás
                            ft.addToBackStack(null);
                            //Ejecuta la transacción de fragmentos
                            ft.commit();

                            //Establecemos autenticado a false
                            autenticado = false;
                        }

                        break;

                    //Caso de posición 1,
                    case 1:
                        //Si no se ha accedido al fragmento de autenticado, para que no haya
                        //un bug en el recreado del fragmento el cambio a la otra vista lo realice
                        if(!autenticado){
                            //Creamos una nueva instancia del fragmento de autenticación, donde se inician los parámetros
                            AuthFragment au = AuthFragment.newInstance("pepe", "12345");
                            //Reemplazamos el fragmento ya existente por el de autenticación
                            ft.replace(R.id.main_frame, au);

                            //Añadimos null a la pila hacia atrás
                            ft.addToBackStack(null);
                            //Ejecuta la transacción de fragmentos
                            ft.commit();

                            //Establecemos autenticado a true porque ha entrado en el fragmento de autenticación
                            autenticado = true;
                        }

                        break;
                }

                //Obtenemos texto del item en la posición clickada
                //String texto = String.valueOf(a.getItemAtPosition(position));

                //Mostramos tostada con el texto y la posición
                //Toast.makeText(MainActivity.this, texto +", con posicion: "+ position, Toast.LENGTH_SHORT).show();

            }
        });
    }


}


