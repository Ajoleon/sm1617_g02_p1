package es.ujaen.git.practica1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pablo on 22/11/2016.
 */

public class Explanation extends Fragment {

    public static Explanation newInstance(){
        Explanation fragment = new Explanation();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmento = inflater.inflate(R.layout.fragment_explanation, container, false);
        return fragmento;
    }

}
