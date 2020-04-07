package pl.lickerish.mobiletrailers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.lickerish.mobiletrailers.R;
import pl.lickerish.mobiletrailers.model.Result;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Result> resultList;
    private Context context;
    private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    public RecyclerViewAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Result movie = resultList.get(position);
        Glide.with(context).load(BASE_IMAGE_URL + movie.getPosterPath()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imageView);
        }
    }

    public void addResults(List<Result> results) {
        List<Result> newList = Stream.concat(resultList.stream(), results.stream())
                .collect(Collectors.toList());
        resultList = newList;
        notifyDataSetChanged();
    }
}
