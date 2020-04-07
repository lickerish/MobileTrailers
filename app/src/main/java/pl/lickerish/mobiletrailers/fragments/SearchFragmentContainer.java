package pl.lickerish.mobiletrailers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import pl.lickerish.mobiletrailers.R;
import pl.lickerish.mobiletrailers.adapters.RecyclerViewAdapter;
import pl.lickerish.mobiletrailers.network.MovieApiConnector;
import pl.lickerish.mobiletrailers.network.endpoints.MovieApiRepository;

public class SearchFragmentContainer extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MovieApiRepository apiInterface;
    private RecyclerViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchfragmentcontainer, container, false);


//        setupView(view);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment topRatedFragment = new TopRatedFragment();
        Fragment popularFragment = new PopularFragment();
        Fragment upcomingFragment = new UpcomingFragment();
        Fragment nowPlayingFragment = new NowPlayingFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.topRatedFragmentContainer, topRatedFragment);
        transaction.replace(R.id.popularFragmentContainer, popularFragment);
        transaction.replace(R.id.upcomingFragmentContainer, upcomingFragment);
        transaction.replace(R.id.nowPlayingFragmentContainer, nowPlayingFragment);
        transaction.commit();

    }


    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.fragmentRecyclerContainer);

        layoutManager = new LinearLayoutManager(this.getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = MovieApiConnector.getClient().create(MovieApiRepository.class);
        adapter = new RecyclerViewAdapter(Collections.emptyList(), SearchFragmentContainer.this.getActivity());
        recyclerView.setAdapter(adapter);
    }
}
