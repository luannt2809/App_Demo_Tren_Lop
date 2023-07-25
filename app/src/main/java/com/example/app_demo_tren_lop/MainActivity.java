package com.example.app_demo_tren_lop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<NyModel> listNy = new ArrayList<>();

    EditText edt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.edt);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = edt.getText().toString();
                SocketManager.getInstance().emit("TestEvent", message);
            }
        });

        SocketManager.getInstance().connect();

        SocketManager.getInstance().on("ThongBaoTuServer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("LuanNT " + args[0]);
            }
        });

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
        nyReqModel.name = "Nguyễn Thị E";
        nyReqModel.desc = "Siêu xinh mãi đỉnh";
        nyReqModel.typeId = "64a0033bdc2faed536fc1b4e";

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Day la token");
        headers.put("Client", "sdfsdfsjdflkasf");
        headers.put("Other-Header", "sdfasdfasfg");

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

