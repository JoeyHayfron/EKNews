package com.engineerskasa.eknews.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.engineerskasa.eknews.Models.TopStories;
import com.engineerskasa.eknews.R;
import com.squareup.picasso.Picasso;

import java.util.List;


class SourceNewsAdapterViewHolder extends RecyclerView.ViewHolder{

    TextView title;
    ImageView pic;

    public SourceNewsAdapterViewHolder(View itemView) {
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.sportsTitle);
        pic = (ImageView) itemView.findViewById(R.id.sportsImage);
    }
}
public class SourceNewsAdapter extends RecyclerView.Adapter<SourceNewsAdapterViewHolder>{

    List<TopStories> articles;
    Context mContext;

    public SourceNewsAdapter(List<TopStories> articles, Context context) {
        this.articles = articles;
        mContext = context;
    }

    @NonNull
    @Override
    public SourceNewsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.source_news_layout,parent,false);
        return new SourceNewsAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceNewsAdapterViewHolder holder, int position) {

        holder.title.setText(articles.get(position).getTitle());
        Picasso
                .with(mContext)
                .load(articles.get(position).getUrlToImage())
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
