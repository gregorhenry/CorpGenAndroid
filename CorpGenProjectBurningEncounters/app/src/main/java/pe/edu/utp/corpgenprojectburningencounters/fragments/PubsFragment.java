package pe.edu.utp.corpgenprojectburningencounters.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.utp.corpgenprojectburningencounters.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PubsFragment extends Fragment {


    public PubsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pubs, container, false);
    }



   //private Fragment getFragmentFor(int id){
     //   switch (id){
       //     case R.id.navigation_pubs:
         //       return new PubsFragment();
        //}

    //}

    //private boolean navigateAccordingTo(int id){
      //  getSupportFragmentManager().beginTransaction().replace(R.id.content, getFragmentFor(id))commit();
        //return true;
    //}
}
