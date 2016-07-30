package nz.stealthcampers.stealthtech.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleMapsService
{
    @GET("maps/api/place/nearbysearch/json")
    Call<PlaceSearchResponse> nearbySearch(
            @Query("key") String key,
            @Query("location") String location,
            @Query("rankby") String rankby,
            @Query("type") String type);

    @GET("maps/api/place/radarsearch/json")
    Call<PlaceSearchResponse> radarSearch(
            @Query("key") String key,
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type);
}
