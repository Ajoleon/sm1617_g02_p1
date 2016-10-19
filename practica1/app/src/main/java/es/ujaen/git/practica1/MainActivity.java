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

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AuthFragment au = AuthFragment.newInstance("pepe","12345");
        ft.add(R.id.main_frame,au);
        // ft.disallowAddToBackStack(null);//añade a la pila y se puede volver al fragmento anterior, si hay pila
        ft.addToBackStack(null);
        ft.commit();
    }

        /**
         * Este metodo sirve para mostrar una tostada en funcion del boton que pulses
         *
         * @param view la vista que ha generado el evento
         */


/* prueba
    public void onSend(View view) {
        switch (view.getId()) {
            case R.id.auth_button_send:
                Toast.makeText(MainActivity.this, "Pulsado el 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.auth_button_send2:
                Toast.makeText(MainActivity.this, "Pulsado el 2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
*/
}


