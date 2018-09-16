package com.engineerskasa.eknews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.engineerskasa.eknews.DetailNews;
import com.engineerskasa.eknews.Interface.ItemClickListener;
import com.engineerskasa.eknews.Models.TopStories;
import com.engineerskasa.eknews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


class TopStoriesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

     ItemClickListener mItemClickListener;
     TextView storyTitle;
     CircleImageView storyImage;

    public TopStoriesAdapterViewHolder(View itemView) {
        super(itemView);
        storyTitle = (TextView)itemView.findViewById(R.id.storyTitle);
        storyImage = (CircleImageView)itemView.findViewById(R.id.storyImage);

        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onClick(view,getAdapterPosition(),false);
    }


}

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapterViewHolder> {
    private Context mContext;
    private List<TopStories> articles;
    public TopStoriesAdapter(List<TopStories> articles,Context context ) {
        mContext = context;
        this.articles = articles;
    }


    @NonNull
    @Override
    public TopStoriesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.top_stories_layout,parent,false);
        return new TopStoriesAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesAdapterViewHolder holder, int position) {

            Picasso
                    .with(mContext)
                    .load(articles.get(position).getUrlToImage())
                    .into(holder.storyImage);

            holder.storyTitle.setText(articles.get(position).getTitle());

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Intent intent = new Intent (mContext, DetailNews.class);
                    intent.putExtra("webURL",articles.get(position).getUrl());
                    view.getContext().startActivity(intent);
                }
            });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
