package com.pwc.myapplication.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.pwc.myapplication.R;
import com.pwc.myapplication.adapters.DataAdapter;
import com.pwc.myapplication.connections.APIClient;
import com.pwc.myapplication.interfaces.ApiInterface;
import com.pwc.myapplication.models.UserContentResponseModel;
import com.pwc.myapplication.utils.UIUtils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class NewsListActivity extends AppCompatActivity {

    private TextView mTitle;
    private RecyclerView newsRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataAdapter dataAdapter;
    private int status_code_done = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    /**
     *  Initialize objects
     */
    private void init() {
        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        mTitle = (TextView) findViewById(R.id.tv_main_title);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        // Setup refresh listener which triggers new data loading
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Check for internet connection
                if(UIUtils.isInternetOn(NewsListActivity.this)){
                    getData();
                }else {
                    UIUtils.showAlert(NewsListActivity.this,getResources().getString(R.string.tx_connection_failled),getResources().getString(R.string.tx_no_internet_connection));

                }
            }
        });

    }

    /**
     * Show data from server on the screen
     * @param newsData
     */
    private void showData(UserContentResponseModel newsData) {

        if (newsData != null) {
            dataAdapter = new DataAdapter(newsData.getRows());

            newsRecyclerView.setAdapter(dataAdapter);
        } else {
            Toast.makeText(NewsListActivity.this, getResources().getString(R.string.tx_data_fetch_unable), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Fetch data from server
     */
    private void getData() {

        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);

        Call<UserContentResponseModel> call = apiService.getUserContent();

        call.enqueue(new Callback<UserContentResponseModel>() {
            @Override
            public void onResponse(Response<UserContentResponseModel> response) {

                swipeRefreshLayout.setRefreshing(false);

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
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(NewsListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}
