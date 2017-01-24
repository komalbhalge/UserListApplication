package com.pwc.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pwc.myapplication.models.UserContentResponseModel;
import com.pwc.myapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mac_admin on 24/01/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<UserContentResponseModel.Row> newsList;

    private Context context;

    public DataAdapter(ArrayList<UserContentResponseModel.Row> newsList) {
        this.newsList = newsList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_view, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder, int i) {

        //Display the data
        if (!TextUtils.isEmpty(newsList.get(i).getImageHref())) {

            viewHolder.progressBar.setVisibility(View.VISIBLE);

            //Used Picasso -an Open source library for lazy loading of images//
            Picasso.with(context).load(newsList.get(i).getImageHref())
                    .placeholder(R.drawable.image_placeholder) //Placeholder until the image load
                   
                    .into(viewHolder.iv_news, new Callback() {
                        @Override
                        public void onSuccess() {
                            viewHolder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                            viewHolder.progressBar.setVisibility(View.GONE);
                        }
                    });

        }

        if (newsList.get(i).getTitle() != null) {
            viewHolder.tv_title.setText(newsList.get(i).getTitle());

        }
        if (!TextUtils.isEmpty(newsList.get(i).getDescription())) {
            viewHolder.tv_description.setText(newsList.get(i).getDescription());
        }


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_description;
        private ImageView iv_news;
        private ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.pv_loading);
            tv_title = (TextView) view.findViewById(R.id.tv_news_title);
            tv_description = (TextView) view.findViewById(R.id.tv_news_description);
            iv_news = (ImageView) view.findViewById(R.id.sdv_news_Image);

        }
    }
}
