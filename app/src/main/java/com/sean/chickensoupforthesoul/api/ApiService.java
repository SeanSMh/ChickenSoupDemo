package com.sean.chickensoupforthesoul.api;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface ApiService {

    @GET("index?key=2b7f2c2b20c492314b4225219ac44464")
    Call<ChickenSoupEntity> getChickenSoup();

    @POST("send?key=8b6031f0-816a-438a-86fa-96ed564a68c1")
    Call<ResponseBody> postWXMessage(@Body RequestBody body);

}
