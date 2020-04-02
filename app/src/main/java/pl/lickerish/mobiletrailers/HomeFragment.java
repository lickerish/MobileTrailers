package pl.lickerish.mobiletrailers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    int[] pictures = {R.drawable.cover, R.drawable.coverk, R.drawable.covert, R.drawable.cover, R.drawable.coverk, R.drawable.covert,R.drawable.cover, R.drawable.coverk, R.drawable.covert};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.coversRecyclerView);

        layoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(pictures);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }
}
