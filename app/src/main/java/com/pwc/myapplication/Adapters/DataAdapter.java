package com.pwc.myapplication.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pwc.myapplication.ModelClasses.UserContentResponseModel;
import com.pwc.myapplication.R;
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
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        if (!TextUtils.isEmpty(newsList.get(i).getImageHref())) {

            Glide.with(context).load(newsList.get(i).getImageHref())
                    //.fitCenter()
                    .dontAnimate()
                    .placeholder(R.drawable.image_placeholder)
                    .into(viewHolder.iv_news)
            ;
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

        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView) view.findViewById(R.id.tv_news_title);
            tv_description = (TextView) view.findViewById(R.id.tv_news_description);
            iv_news = (ImageView) view.findViewById(R.id.sdv_news_Image);

        }
    }
}
