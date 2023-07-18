package com.example.app_demo_tren_lop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<NyModel> listNy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NYManagerService nyManagerService = RetrofitClient.getService();
//        Call<List<NyModel>> call = nyManagerService.getListNy();
//        call.enqueue(new Callback<List<NyModel>>() {
//            @Override
//            public void onResponse(Call<List<NyModel>> call, Response<List<NyModel>> response) {
//                if (response.code() == 200){
//                    listNy = response.body();
//                    Log.d("zzzzzzzzzzz", "onResponse: " + listNy);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<NyModel>> call, Throwable t) {
//                System.out.println(t);
//            }
//        });

        NyReqModel nyReqModel = new NyReqModel();
        nyReqModel.name = "Nguyễn Thị D";
        nyReqModel.desc = "Siêu xinh mãi đỉnh";
        nyReqModel.typeId = "64a0033bdc2faed536fc1b4e";

        Call<String> call = nyManagerService.addNewNy(nyReqModel);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("zzzzzzzzzzzz", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}

class NyModel {
    public String _id;
    public String name;
    public String desc;
    public TypeId typeId;
}

class TypeId {
    public String _id;
    public String name;
    public String desc;
}

class NyReqModel {
    public String _id;
    public String name;
    public String desc;
    public String typeId;
}