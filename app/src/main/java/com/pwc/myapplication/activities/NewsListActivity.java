package com.pwc.myapplication.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pwc.myapplication.connections.APIClient;
import com.pwc.myapplication.R;
import com.pwc.myapplication.adapters.DataAdapter;
import com.pwc.myapplication.models.UserContentResponseModel;
import com.pwc.myapplication.interfaces.ApiInterface;
import com.pwc.myapplication.utils.UIUtils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class NewsListActivity extends AppCompatActivity {
    private TextView mTitle;
    private RecyclerView newsRecyclerView;
    private DataAdapter dataAdapter;
    private int status_code_done = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fresco.initialize(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_list);

        init();

        //Check for internet connection
        if(UIUtils.isInternetOn(NewsListActivity.this)){
            getData();
        }else {
            UIUtils.showAlert(this,getResources().getString(R.string.tx_connection_failled),getResources().getString(R.string.tx_no_internet_connection));

        }


    }

    private void init() { /*Initialize objects */
        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        mTitle = (TextView) findViewById(R.id.tv_main_title);
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
                if (statusCode == status_code_done && mNewsData != null) {
                    mTitle.setText(mNewsData.getTitle());
                    showData(mNewsData);
                } else {
                    Toast.makeText(NewsListActivity.this, getResources().getString(R.string.tx_data_fetch_unable), Toast.LENGTH_LONG).show();
                }

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
