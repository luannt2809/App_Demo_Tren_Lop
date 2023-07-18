package com.example.app_demo_tren_lop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class RetrofitClient {

    private static final String BASE_URL = "http://10.24.50.104:3000/";

    public static NYManagerService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(NYManagerService.class);
    }
}

interface NYManagerService {
    @GET("ny/list")
    Call<List<NyModel>> getListNy();

    @POST("ny/add")
    Call<String> addNewNy(@Body NyReqModel nyReqModel);

}
