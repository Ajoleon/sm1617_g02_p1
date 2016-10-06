package es.ujaen.git.practica1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fs = getSupportFragmentManager();
        FragmentTransaction ft = fs.beginTransaction();
        AuthFragment au = AuthFragment.newInstance("pepe", "123456");
        //todo esto para ir añadiendo de forma dinamica el fragment
        ft.add(R.id.main_frame, au);
        // ft.disallowAddToBackStack(null);//añade a la pila y se puede volver al fragmento anterior, si hay pila
        ft.commit();
/*
        Button boton = (Button) findViewById(R.id.auth_button_send);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Toast.makeText(MainActivity.this, "Recopilando datos...", Toast.LENGTH_SHORT).show();
                String usuario = R.id.auth_edit_user;
                String clave = R.id.auth_edit_pass;
                String ip = R.id.auth_edit_ip;
                int puerto = R.id.auth_edit_port;
                Datos datos = new Datos(usuario, clave, ip, puerto);

            }
        });
*/
    }

        /**
         * Este metodo sirve para mostrar una tostada en funcion del boton que pulses
         *
         * @param view la vista que ha generado el evento
         */


/* prueba
>>>>>>> origin/master
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


