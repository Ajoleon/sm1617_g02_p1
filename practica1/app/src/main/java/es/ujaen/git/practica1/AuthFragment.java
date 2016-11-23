package es.ujaen.git.practica1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AuthFragment} interface
 * to handle interaction events.
 * Use the {@link AuthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthFragment extends Fragment {

    //Estas son las variables iniciales con las que se inicializa el fragmento
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Estas dos variables las utilizaremos para establecer los valores con los que se inicializa el fragmento
    //Se pueden ver las instrucciones que hacemos con ellas en el método OnCreate
    private String mUser = "";
    private String mPass = "";
    //Objeto de la clase autentication donde iremos guardando los valores de los atributos que vamos escribiendo en las casillas
    private Autentication mAutentica = new Autentication("","","",0);

    //Inicializamos las variables que utilizamos para cada casilla
    private EditText mEditUser = null;
    private EditText mEditPass = null;
    private EditText mEditIp = null;
    private EditText mEditPort = null;

    public AuthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Usuario inicial
     * @param pass Contraseña inicial
     * @return Una instancia del fragmento AuthFragment, actualizada con los valores inicializados
     */

    public static AuthFragment newInstance(String user, String pass) {
        //Cuando se crea una instanca nueva, colocamos los valores iniciales de usuario y password
        AuthFragment fragment = new AuthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);
        fragment.setArguments(args);
        return fragment;
    }

    /**En este método se establecen los valores iniciales si todavía
     * no se ha creado ninguna instancia
     *
     * @param savedInstanceState Estado de la instancia guardada
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Para que solo lo haga cuando no hay ninguna instancia anterior
        if(savedInstanceState==null) {
            if (getArguments() != null) {
                mUser = getArguments().getString(ARG_PARAM1);
                mPass = getArguments().getString(ARG_PARAM2);
                mAutentica.setmUser(mUser);
                mAutentica.setmPass(mPass);
            }
        }
    }

    /**En este método establecemos las instrucciones cuando se crea la vista
     *
     * @param inflater
     * @param container
     * @param savedInstanceState Estado de la instancia guardada
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Si ya se había creado una instancia
        if(savedInstanceState!=null){
            //Mensaje de cambio de configuración
            Toast.makeText(getActivity(), "onCreateView. ", Toast.LENGTH_SHORT).show();
            //Se queda toda actualizado en el objeto de la clase Autentication
            mAutentica.setmUser(savedInstanceState.getString(ARG_PARAM1));
            mAutentica.setmPass(savedInstanceState.getString(ARG_PARAM2));
        }

        // Crea el fragmento fragment_auth
        final View fragmento = inflater.inflate(R.layout.fragment_auth, container, false);

        redibuja(fragmento);

        //Establecemos el botón según el id de este
        Button boton = (Button) fragmento.findViewById(R.id.auth_button_send);
        //TODO al pulsar botón se coge todas las faviarbles sin tener que hacer changefocus
        //Al clickar en el botón
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Para evitar problemas de cambios en configuracion, se almacenan aquí directamente
                String usuario = mEditUser.getText().toString();
                String password = mEditPass.getText().toString();
                String ip = mEditIp.getText().toString();
                Integer puerto = Integer.parseInt(mEditPort.getText().toString());
                */
                //Toast.makeText(fragmento.this,nombre,Toast.LENGTH_SHORT).show();

                //Autentication datos = new Autentication(usuario, password, ip, puerto);
                //mAutentica = new Autentication(mUser,mPass,null,0);
                mEditUser = (EditText) fragmento.findViewById(R.id.auth_edit_user);
                mEditPass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
                mEditIp = (EditText) fragmento.findViewById(R.id.auth_edit_ip);
                mEditPort = (EditText) fragmento.findViewById(R.id.auth_edit_port);

                mAutentica.setmUser(mEditUser.getEditableText().toString());
                mAutentica.setmPass(mEditPass.getEditableText().toString());
                mAutentica.setmIP(mEditIp.getEditableText().toString());
                int myNum=0;
                try {
                    myNum = Integer.parseInt(mEditPort.getEditableText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                mAutentica.setmPort(myNum);


                //Tostadas indicando los valores de cada atributo del objeto mAutentica
                Toast.makeText(getActivity(), "Usuario: " + mAutentica.getmUser(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Password: " + mAutentica.getmPass(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Ip: " + mAutentica.getmIP(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Puerto: " + mAutentica.getmPort(), Toast.LENGTH_SHORT).show();

            }
        });

        //Devolvemos el fragmento redibujado
        return fragmento;


    }


    //Método que redibuja las vistas
    private void redibuja(View fragmento){

        //El texto de cada casilla
        mEditUser = (EditText) fragmento.findViewById(R.id.auth_edit_user);
        mEditPass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
        mEditIp = (EditText) fragmento.findViewById(R.id.auth_edit_ip);
        mEditPort = (EditText) fragmento.findViewById(R.id.auth_edit_port);
        //Cuando pierde el foco (se edita el texto), actualiza cada una de las variables
        //Me he dado cuenta que solo guarda la variable cuando quitas el cursor de esa casilla
        //Si no has quitado el sursor de una casilla, no lo guarda
        mEditUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setmUser(mEditUser.getEditableText().toString());
            }
        });
        mEditPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setmPass(mEditPass.getEditableText().toString());
            }
        });
        mEditIp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAutentica.setmIP(mEditIp.getEditableText().toString());
            }
        });
        //Hace falta un try porque si no se pone, la aplicación no inicia
        mEditPort.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int myNum=0;
                try {
                    myNum = Integer.parseInt(mEditPort.getEditableText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                mAutentica.setmPort(myNum);
            }
        });

        //Se establecen las casillas usuario y contraseña con los atributos de la clase mAutentica
        mEditUser.setText(mAutentica.getmUser());
        mEditPass.setText(mAutentica.getmPass());

    }

    /**Método para mantener los datos al rotar la pantalla
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Guardamos el usuario y la contraseña en las variables iniciales
        //Aunque permanezcan los textos en la dirección y el puerto, no se guardan
        //Para guardarlas, hay que editar los textos
        outState.putString(ARG_PARAM1, mAutentica.getmUser());
        outState.putString(ARG_PARAM2, mAutentica.getmPass());
    }
}
