package pl.lickerish.mobiletrailers.network.endpoints;

import java.util.List;

import pl.lickerish.mobiletrailers.model.RetroPhoto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}
