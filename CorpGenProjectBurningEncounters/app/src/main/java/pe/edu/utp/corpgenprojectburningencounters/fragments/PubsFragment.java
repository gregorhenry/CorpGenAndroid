package pe.edu.utp.corpgenprojectburningencounters.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.edu.utp.corpgenprojectburningencounters.R;
import pe.edu.utp.corpgenprojectburningencounters.adapters.PubsAdapter;
import pe.edu.utp.corpgenprojectburningencounters.models.Pub;
import pe.edu.utp.corpgenprojectburningencounters.models.PubsRepository;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PubsFragment extends Fragment {

    RecyclerView pubsRecyclerView;
    PubsAdapter pubsAdapter;
    RecyclerView.LayoutManager pubsLayoutManager;
    List<Pub> pubs;


    public PubsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pubs, container, false);
        pubsRecyclerView = (RecyclerView)
                view.findViewById(R.id.pubsRecyclerView);
        pubs = PubsRepository.getPubs();
        pubsAdapter = new PubsAdapter(pubs);
        pubsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        pubsRecyclerView.setAdapter(pubsAdapter);
        pubsRecyclerView.setLayoutManager(pubsLayoutManager);
        adaptGridTo(getResources().getConfiguration());
        return view;
    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adaptGridTo(newConfig);
    }

    private void adaptGridTo(Configuration configuration) {
        int spanCount = configuration.orientation == ORIENTATION_PORTRAIT ? 2 : 3;
        ((GridLayoutManager)pubsLayoutManager).setSpanCount(spanCount);
    }
}
