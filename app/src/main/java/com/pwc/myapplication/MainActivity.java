package com.pwc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pwc.myapplication.ModelClasses.UserContentResponseClass;
import com.pwc.myapplication.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //first commit

        Button btn= (Button)findViewById(R.id.btn_get);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
getData();
            }
        });

    }

    private  void getData(){
        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);

        Call<UserContentResponseClass> call = apiService.getUserContent();
        call.enqueue(new Callback<UserContentResponseClass>() {
            @Override
            public void onResponse(Call<UserContentResponseClass> call, Response<UserContentResponseClass> response) {
                int statusCode = response.code();
                Log.e("TAG","DATA"+statusCode);
                Toast.makeText(MainActivity.this,""+statusCode,Toast.LENGTH_LONG).show();
                UserContentResponseClass  users= response.body();
            }

            @Override
            public void onFailure(Call<UserContentResponseClass> call, Throwable t) {

            }
        });
    }
}
