package pl.lickerish.mobiletrailers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import pl.lickerish.mobiletrailers.R;
import pl.lickerish.mobiletrailers.adapters.RecyclerViewAdapter;
import pl.lickerish.mobiletrailers.model.Result;
import pl.lickerish.mobiletrailers.model.TopRatedMovies;
import pl.lickerish.mobiletrailers.network.MovieApiConnector;
import pl.lickerish.mobiletrailers.network.endpoints.MovieApiRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private String API_KEY = "26c487906fb944a1dc78fe3e70fac38a";
    private MovieApiRepository apiInterface;
    private RecyclerViewAdapter adapter;

    //Variables for pagination
    private int pageNumber = 1;
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_treshold;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        setupView(view);
        loadNextPage();

        return view;

    }

    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.popularRecycler);

        layoutManager = new LinearLayoutManager(this.getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = MovieApiConnector.getClient().create(MovieApiRepository.class);
        adapter = new RecyclerViewAdapter(Collections.emptyList(), PopularFragment.this.getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void loadNextPage() {
        Call<TopRatedMovies> call = apiInterface.getPopularMovies(API_KEY, "en-US", pageNumber);

        call.enqueue(new Callback<TopRatedMovies>() {
            @Override
            public void onResponse(Call<TopRatedMovies> call, Response<TopRatedMovies> response) {
                List<Result> resultList = response.body().getResults();

                adapter.addResults(resultList);

            }

            @Override
            public void onFailure(Call<TopRatedMovies> call, Throwable t) {

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if (dx > 0) {
                    if (isLoading) {
                        if (totalItemCount > previous_total) {
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    }
                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_treshold)) {
                        pageNumber++;
                        loadNextPage();
                        isLoading = true;
                    }
                }
            }
        });
    }
}

