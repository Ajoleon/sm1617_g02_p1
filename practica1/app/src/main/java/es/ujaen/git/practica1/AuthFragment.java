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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mUser = "";
    private String mPass = "";
    private Autentication mAutentica = new Autentication("","","",0);

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
     * @param user Parameter 1.
     * @param pass Parameter 2.
     * @return A new instance of fragment AuthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthFragment newInstance(String user, String pass) {
        AuthFragment fragment = new AuthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, pass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {//Para que solo lo haga cuando no hay nada
            if (getArguments() != null) {
                mUser = getArguments().getString(ARG_PARAM1);
                mPass = getArguments().getString(ARG_PARAM2);
                mAutentica.setmUser(mUser);
                mAutentica.setmPass(mPass);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState!=null){
            Toast.makeText(getActivity(), "Cambio configuracion. ", Toast.LENGTH_SHORT).show();
            //Cuando se cambia la configuracion se queda toda actualizado
            mAutentica.setmUser(savedInstanceState.getString(ARG_PARAM1));
        }

        // Inflate the layout for this fragment
        View fragmento = inflater.inflate(R.layout.fragment_auth, container, false);

        redibuja(fragmento);

        Button boton = (Button) fragmento.findViewById(R.id.auth_button_send);

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
                mAutentica = new Autentication(mUser,mPass,null,0);

                Toast.makeText(getActivity(), "Usuario: " + mAutentica.getmUser(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Password: " + mAutentica.getmPass(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Ip: " + mAutentica.getmIP(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Puerto: " + mAutentica.getmPort(), Toast.LENGTH_SHORT).show();

            }
        });

        return fragmento;

    }


    //Método que redibuja las vistas
    private void redibuja(View fragmento){


        mEditUser = (EditText) fragmento.findViewById(R.id.auth_edit_user);
        mEditPass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
        //Cuando pierde el foco, actualiza la variable
        mEditUser.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void OnfocusChange(View v, boolean hasFocus){
                mAutentica.setmUser(mEditUser.getEditableText().toString());
            }
        });

        mEditUser.setText(mAutentica.getmUser());
        mEditPass.setText(mAutentica.getmPass());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
