package nz.stealthcampers.stealthtech.view;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.service.GoogleMapsService;
import nz.stealthcampers.stealthtech.service.PlaceSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener()
        {
            @Override
            public void onCameraChange(CameraPosition cameraPosition)
            {
                Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                String location = cameraPosition.target.latitude + "," + cameraPosition.target.longitude;

                GoogleMapsService service = retrofit.create(GoogleMapsService.class);
                //Call<PlaceSearchResponse> call = service.nearbySearch(getResources().getString(R.string.google_maps_key), location, "distance", "campground");
                Call<PlaceSearchResponse> call = service.radarSearch(getResources().getString(R.string.google_maps_key), location, 50000, "campground");
                call.enqueue(new Callback<PlaceSearchResponse>()
                {
                    @Override
                    public void onResponse(Call<PlaceSearchResponse> call, Response<PlaceSearchResponse> response)
                    {
                        int i = 1;
                    }

                    @Override
                    public void onFailure(Call<PlaceSearchResponse> call, Throwable t)
                    {
                        int i = 1;
                    }
                });
            }
        });
    }
}
