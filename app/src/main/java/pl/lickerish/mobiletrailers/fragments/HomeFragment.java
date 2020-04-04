package pl.lickerish.mobiletrailers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import pl.lickerish.mobiletrailers.R;
import pl.lickerish.mobiletrailers.adapters.RecyclerViewAdapter;
import pl.lickerish.mobiletrailers.model.Result;
import pl.lickerish.mobiletrailers.model.TopRatedMovies;
import pl.lickerish.mobiletrailers.network.MovieApi;
import pl.lickerish.mobiletrailers.network.endpoints.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private String API_KEY = "26c487906fb944a1dc78fe3e70fac38a";
    private MovieService apiInterface;
    private RecyclerViewAdapter adapter;

    private int pageNumber = 1;

    //Variables for pagination
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_treshold;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.coversRecyclerView);

        layoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = MovieApi.getClient().create(MovieService.class);
        adapter = new RecyclerViewAdapter(Collections.emptyList(), HomeFragment.this.getActivity());
        recyclerView.setAdapter(adapter);
        performPagination();

        return view;
    }

    private void performPagination() {
        Call<TopRatedMovies> call = apiInterface.getTopRatedMovies(API_KEY, "en-US", pageNumber);

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

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previous_total) {
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    }
                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_treshold)) {
                        pageNumber++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }
}
//        layoutManager = new GridLayoutManager(this.getActivity(), 2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerViewAdapter = new RecyclerViewAdapter(pictures);
//
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setHasFixedSize(true);

//
//        /*Create handle for the RetrofitInstance interface*/
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<RetroPhoto>> call = service.getAllPhotos();
//        call.enqueue(new Callback<List<RetroPhoto>>() {
//            @Override
//            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
//                generateDataList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
//                Toast.makeText(HomeFragment.this.getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        return view;


//
//    /*Method to generate List of data using RecyclerView with custom adapter*/
//    private void generateDataList(List<RetroPhoto> photoList) {
//        recyclerView = getView().findViewById(R.id.coversRecyclerView);
//        adapter = new CustomAdapter(this.getActivity(), photoList);
//        layoutManager = new GridLayoutManager(this.getActivity(), 2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//    }
