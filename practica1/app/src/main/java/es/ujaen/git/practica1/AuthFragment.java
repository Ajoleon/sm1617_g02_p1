package es.ujaen.git.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**Fragmento para autenticar
 *
 */
public class AuthFragment extends Fragment {

    //Estas son las variables iniciales para guardar parámetros tras un recreado de la actividad
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**Estas dos variables las utilizaremos para establecer los valores con los que se inicializa el fragmento
     * Mirar método OnCreate()
     */

    private String mUser = "";
    private String mPass = "";

    //Objeto de la clase autentication donde iremos guardando los valores de los atributos que vamos escribiendo en las casillas
    private Autentication mAutentica = new Autentication("","","",0);

    //Inicializamos las variables que utilizamos para cada EditText, encontrando por id
    private EditText mEditUser = null;
    private EditText mEditPass = null;
    private EditText mEditIp = null;
    private EditText mEditPort = null;


    /**Método para crear una instancia del fragmento para utenticar con los parámetros seleccionados
     *
     * @param user Usuario inicial
     * @param pass Contraseña inicial
     * @return Una instancia del fragmento AuthFragment, actualizada con los valores inicializados
     */
    public static AuthFragment newInstance(String user, String pass) {
        //Se llama al fragmento
        AuthFragment fragment = new AuthFragment();

        //Recogemos el bundle donde se han guardado los parámetros iniciales
        Bundle args = new Bundle();
        //Colocamos los valores iniciales de usuario y password del bundle en los parámetros iniciales
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);

        //Establecemos los parámetros en el fragmento
        fragment.setArguments(args);
        //Lo devolvemos
        return fragment;
    }

    /**En este método se establecen los valores iniciales si todavía
     * no se ha creado ninguna instancia
     *
     * @param savedInstanceState Instancia con los parámetros guardados tras un recreado del fragmento
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Si no hay ninguna instancia con parámetros anteriormente
        if(savedInstanceState==null) {
            //Si los parámetros no son null
            if (getArguments() != null) {
                //Recogemos los valores de los parámetros guardados
                mUser = getArguments().getString(ARG_PARAM1);
                mPass = getArguments().getString(ARG_PARAM2);
                //Los incluimos en la clase autentica
                mAutentica.setmUser(mUser);
                mAutentica.setmPass(mPass);
            }
        }
    }

    /**En este método establecemos las instrucciones cuando se crea la vista
     *
     * @param inflater Necesario para inflar el fragmento con la vista
     * @param container Contenedor de la vista
     * @param savedInstanceState Instancia de los parámetros guardados tras un recreado del fragmento
     * @return la vista del fragmento autentica
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Si ya se había creado una instancia con parámetros
        if(savedInstanceState!=null){
            //Mensaje de cambio de configuración
            //Toast.makeText(getActivity(), "onCreateView. ", Toast.LENGTH_SHORT).show();

            //Coge los parámetros guardados en la instancia y los establece en la clase Autentica
            mAutentica.setmUser(savedInstanceState.getString(ARG_PARAM1));
            mAutentica.setmPass(savedInstanceState.getString(ARG_PARAM2));
        }

        //Infla  el contenedor con el fragmento de autenticación
        final View fragmento = inflater.inflate(R.layout.fragment_auth, container, false);

        //Redibuja el fragmento
        redibuja(fragmento);

        //Buscamos el botón según su id
        Button boton = (Button) fragmento.findViewById(R.id.auth_button_send);

        //Al clickar en el botón
        boton.setOnClickListener(new View.OnClickListener() {
            /**Método al hacer click en el botón
             *
             * @param v Vista del fragmento
             */
            @Override
            public void onClick(View v) {

                //Volvemos a repetir lo realizado en el método Redibuja para que cuando se pulse el botón se
                //recojan los parámetros de los EditText sin solo hacerlo con cambio de focus
                ///////////////////////
                //Guarda lo contenido en los EditText en las variables creadas para ello
                mEditUser = (EditText) fragmento.findViewById(R.id.auth_edit_user);
                mEditPass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
                mEditIp = (EditText) fragmento.findViewById(R.id.auth_edit_ip);
                mEditPort = (EditText) fragmento.findViewById(R.id.auth_edit_port);

                //Cada variable anterior se guarda en la clase autentica
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
                ////////////////////////


                //Tostadas indicando los valores de cada atributo del objeto mAutentica
                Toast.makeText(getActivity(), "Usuario: " + mAutentica.getmUser(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Password: " + mAutentica.getmPass(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Ip: " + mAutentica.getmIP(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Puerto: " + mAutentica.getmPort(), Toast.LENGTH_SHORT).show();


                //Paso de parámetros
                Intent intent= new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("User", mAutentica.getmUser());
                intent.putExtra("Pass", mAutentica.getmPass());
                intent.putExtra("Ip", mAutentica.getmIP());
                intent.putExtra("Port", mAutentica.getmPort());
                startActivity(intent);

            }
        });

        //Devolvemos el fragmento
        return fragmento;
    }

    /**Método que redibuja el fragmento para ir recogiendo los valores de los EditText e ir
     * guardándolos en la clase Autentica cada vez que se hace cambio de focus
     *
     * @param fragmento fragmento redibujado
     */
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

    /**Método para mantener los datos al rotar la pantalla y recrear la actividad
     * Los recogeremos en el OnCreate y OnCreateView
     * Los establecemos en el newInstance, como valores por defecto
     *
     * @param outState bundle donde se guardan los parámetros
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Guardamos el usuario y la contraseña de la clase autentica en las variables del bundle
        outState.putString(ARG_PARAM1, mAutentica.getmUser());
        outState.putString(ARG_PARAM2, mAutentica.getmPass());
    }
}
