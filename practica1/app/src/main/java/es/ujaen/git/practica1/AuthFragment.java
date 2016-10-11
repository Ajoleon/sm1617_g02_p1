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
        if (getArguments() != null) {
            mUser = getArguments().getString(ARG_PARAM1);
            mPass = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragmento = inflater.inflate(R.layout.fragment_auth, container, false);

        EditText user = (EditText) fragmento.findViewById(R.id.auth_edit_user);
        EditText pass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
        user.setText(mUser);
        pass.setText(mPass);

        Button boton = (Button) fragmento.findViewById(R.id.auth_button_send);

        mEditUser = (EditText) fragmento.findViewById(R.id.auth_edit_user);
        mEditPass = (EditText) fragmento.findViewById(R.id.auth_edit_pass);
        mEditIp = (EditText) fragmento.findViewById(R.id.auth_edit_ip);
        mEditPort = (EditText) fragmento.findViewById(R.id.auth_edit_port);

        mEditUser.setText(mUser);
        mEditPass.setText(mPass);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = mEditUser.getText().toString();
                String password = mEditPass.getText().toString();
                String ip = mEditIp.getText().toString();
                Integer puerto = Integer.parseInt(mEditPort.getText().toString());
                //Toast.makeText(fragmento.this,nombre,Toast.LENGTH_SHORT).show();
                Autentication datos = new Autentication(usuario, password, ip, puerto);

                Toast.makeText(getActivity(), "Usuario: " + datos.getmUser(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Password: " + datos.getmPass(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Ip: " + datos.getmIP(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Puerto: " + datos.getmPort(), Toast.LENGTH_SHORT).show();
            }
        });

/*
        mEditUser.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    mEditUser.setHint("");
                    mEditUser.setHintTextColor(getResources().getColor(R.color.colorAccent));//.getColor(R.color.white)
                }else
                    mEditUser.setHint("Your hint");
            }
        });*/
        /*
        mEditUser.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            public void onClick(View v) {
                mEditUser.setHint("");
            }
        });
        */
        /*
        mEditUser.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEventCompat event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    perform action;
                    return true;
                }
                return false;
            }
        });*/
        mEditUser.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() != KeyEvent.ACTION_UP) {
                    mEditUser.setHint("");
                }
                if(event.getAction()!= KeyEvent.ACTION_DOWN){
                    mEditUser.setHint("Introduce usuario");
                }

                return false;
            }
        });
        return fragmento;
    }

}
