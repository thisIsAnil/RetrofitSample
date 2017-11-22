package com.infi.samplerv.helper;

import com.infi.samplerv.models.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by INFIi on 11/21/2017.
 */

public interface RetrofitInterface {

    @GET("/photos")
    Call<List<Model>> getPhotos();

    //can be used to load single item at time in recycler view prefered for slow network
    @GET("/photos/{id}")
    Call<Model> getPhotoAtPostition(@Path("id") int position);

    //can add using @POST and delete using @DELETE
}
