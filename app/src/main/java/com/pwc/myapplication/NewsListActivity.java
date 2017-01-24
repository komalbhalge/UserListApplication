package com.pwc.myapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pwc.myapplication.Adapters.DataAdapter;
import com.pwc.myapplication.ModelClasses.UserContentResponseModel;
import com.pwc.myapplication.Interfaces.ApiInterface;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
//import retrofit2.Callback;
//import retrofit2.Response;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private DataAdapter dataAdapter;
    //   private UserContentResponseModel mNewsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fresco.initialize(this);
        setContentView(R.layout.activity_news_list);

        init();
        getData();

    }


    private void init() { /*Initialize objects */
        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showData(UserContentResponseModel newsData) {


        if (newsData != null) {
            dataAdapter = new DataAdapter(newsData.getRows());

            newsRecyclerView.setAdapter(dataAdapter);
        } else {
            Toast.makeText(NewsListActivity.this, "UNABKLR", Toast.LENGTH_LONG).show();
        }
    }

    private void getData() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "", getResources().getString(R.string.tx_inprogress), false, false);


        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);

        Call<UserContentResponseModel> call = apiService.getUserContent();

        call.enqueue(new Callback<UserContentResponseModel>() {
            @Override
            public void onResponse(Response<UserContentResponseModel> response) {
                //Dismissing the loading progressbar
                loading.dismiss();
                int statusCode = response.code();

                UserContentResponseModel mNewsData = response.body();
                showData(mNewsData);
            }

            @Override
            public void onFailure(Throwable t) {
                //Dismissing the loading progressbar
                loading.dismiss();
                Toast.makeText(NewsListActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}
