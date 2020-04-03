package pl.lickerish.mobiletrailers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.lickerish.mobiletrailers.model.RetroPhoto;
import pl.lickerish.mobiletrailers.network.RetrofitClientInstance;
import pl.lickerish.mobiletrailers.network.endpoints.GetDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    int[] pictures = {R.drawable.cover, R.drawable.coverk, R.drawable.covert, R.drawable.cover, R.drawable.coverk, R.drawable.covert, R.drawable.cover, R.drawable.coverk, R.drawable.covert};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.coversRecyclerView);

//        layoutManager = new GridLayoutManager(this.getActivity(), 2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerViewAdapter = new RecyclerViewAdapter(pictures);
//
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setHasFixedSize(true);


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                Toast.makeText(HomeFragment.this.getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<RetroPhoto> photoList) {
        recyclerView = getView().findViewById(R.id.coversRecyclerView);
        adapter = new CustomAdapter(this.getActivity(), photoList);
        layoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
