package com.example.travelmate.Network;

import com.example.travelmate.Model.DataModel;
import com.example.travelmate.Model.DataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/v2/5c261ccb3000004f0067f6ec")
    Call<DataResponse> getAllPhotos();
}
