package pl.lickerish.mobiletrailers.network.endpoints;

import pl.lickerish.mobiletrailers.model.TopRatedMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiRepository {
    @GET("movie/top_rated")
    Call<TopRatedMovies> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );
    @GET("movie/popular")
    Call<TopRatedMovies> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );
    @GET("movie/upcoming")
    Call<TopRatedMovies> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );
    @GET("movie/now_playing")
    Call<TopRatedMovies> getNowPlayingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );
}
