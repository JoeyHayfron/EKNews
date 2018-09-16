package com.engineerskasa.eknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.engineerskasa.eknews.Adapters.TopStoriesAdapter;
import com.engineerskasa.eknews.Common.Common;
import com.engineerskasa.eknews.Interface.NewsService;
import com.engineerskasa.eknews.Models.GetTopStories;
import com.engineerskasa.eknews.Models.TopStories;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStoriess extends Fragment {
    SwipeRefreshLayout mSwipeRefreshLayout;
    SliderLayout mSliderLayout;
    NewsService mNewsService;
    RecyclerView mRecyclerView, mRecyclerView1,mRecyclerView2;
    RecyclerView.LayoutManager mLayoutManager,mLayoutManager1,mLayoutManager2;
    TopStoriesAdapter mTopStoriesAdapter;
    SpotsDialog mSpotsDialog;

    public TopStoriess() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_top_stories, container, false);



        mSliderLayout = (SliderLayout)v.findViewById(R.id.topStoriesSliderr);
        mSliderLayout.setIndicatorAnimation(SliderLayout.Animations.WORM);
        mSliderLayout.setScrollTimeInSec(3);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.topStoriesRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView1 = (RecyclerView)v.findViewById(R.id.topStoriesRecycler1);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new LinearLayoutManager(getActivity());
        mRecyclerView1.setLayoutManager(mLayoutManager1);

        mRecyclerView2 = (RecyclerView)v.findViewById(R.id.topStoriesRecycler2);
        mRecyclerView2.setHasFixedSize(true);
        mLayoutManager2 = new LinearLayoutManager(getActivity());
        mRecyclerView2.setLayoutManager(mLayoutManager2);

        mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.topStoriesSwipe);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setSliderLayout(true);
            }
        });

        mSpotsDialog = new SpotsDialog(getActivity());



        mNewsService = Common.getLatestNews();

        setSliderLayout(false);

        return v;
    }

    public void setSliderLayout(boolean isRefereshed){

        if(!isRefereshed){
            mSpotsDialog.show();
            mNewsService.getHeadlines().enqueue(new Callback<GetTopStories>() {
                @Override
                public void onResponse(Call<GetTopStories> call, final Response<GetTopStories> response) {
                    for (int i=0;i<=3;i++){
                        SliderView mSliderview = new SliderView(getActivity());
                        switch(i){
                            case 0:
                                mSliderview.setImageUrl(response.body().getArticles().get(0).getUrlToImage());
                                break;
                            case 1:
                                mSliderview.setImageUrl(response.body().getArticles().get(1).getUrlToImage());
                                break;
                            case 2:
                                mSliderview.setImageUrl(response.body().getArticles().get(2).getUrlToImage());
                                break;
                            case 3:
                                mSliderview.setImageUrl(response.body().getArticles().get(3).getUrlToImage());
                                break;
                        }
                        mSliderview.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                        mSliderview.setDescription(response.body().getArticles().get(i).getTitle());
                        final int finalI = i;
                        mSliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(SliderView sliderView) {
                                Intent intent = new Intent(getContext(), DetailNews.class);
                                intent.putExtra("webURL",response.body().getArticles().get(finalI).getUrl());
                                startActivity(intent);
                            }
                        });
                        mSliderLayout.addSliderView(mSliderview);
                    }
                    List<TopStories> articless = response.body().getArticles();
                    articless.remove(0);
                    articless.remove(1);
                    articless.remove(2);
                    articless.remove(3);
                    mTopStoriesAdapter = new TopStoriesAdapter(articless,getActivity());
                    mTopStoriesAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mTopStoriesAdapter);
                    getSports();
                    getWorld();
                    mSpotsDialog.dismiss();
                }
                @Override
                public void onFailure(Call<GetTopStories> call, Throwable t) {

                }
            });
        }
        else{
            mSpotsDialog.show();
            mNewsService.getHeadlines().enqueue(new Callback<GetTopStories>() {
                @Override
                public void onResponse(Call<GetTopStories> call, Response<GetTopStories> response) {
                    for (int i=0;i<=3;i++){
                        SliderView mSliderview = new SliderView(getActivity());
                        switch(i){
                            case 0:
                                mSliderview.setImageUrl(response.body().getArticles().get(0).getUrlToImage());
                                break;
                            case 1:
                                mSliderview.setImageUrl(response.body().getArticles().get(1).getUrlToImage());
                                break;
                            case 2:
                                mSliderview.setImageUrl(response.body().getArticles().get(2).getUrlToImage());
                                break;
                            case 3:
                                mSliderview.setImageUrl(response.body().getArticles().get(3).getUrlToImage());
                                break;
                        }
                        mSliderview.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                        mSliderview.setDescription(response.body().getArticles().get(i).getTitle());
                        final int finalI = i;
                        mSliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(SliderView sliderView) {
                                Toast.makeText(getActivity(),"This is slider "+(finalI+1),Toast.LENGTH_LONG).show();
                            }
                        });
                        mSliderLayout.addSliderView(mSliderview);
                    }
                    List<TopStories> articless = response.body().getArticles();
                    articless.remove(0);
                    articless.remove(1);
                    articless.remove(2);
                    articless.remove(3);
                    mTopStoriesAdapter = new TopStoriesAdapter(articless,getActivity());
                    mTopStoriesAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mTopStoriesAdapter);
                    getSports();
                    getWorld();
                    mSpotsDialog.dismiss();
                }
                @Override
                public void onFailure(Call<GetTopStories> call, Throwable t) {

                }
            });
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }
    public void getSports(){
        mNewsService.getSportsHeadlines().enqueue(new Callback<GetTopStories>() {
            @Override
            public void onResponse(Call<GetTopStories> call, Response<GetTopStories> response) {
                     List<TopStories> articless = response.body().getArticles();
                     mTopStoriesAdapter = new TopStoriesAdapter(articless,getActivity());
                     mTopStoriesAdapter.notifyDataSetChanged();
                     mRecyclerView1.setAdapter(mTopStoriesAdapter);
            }

            @Override
            public void onFailure(Call<GetTopStories> call, Throwable t) {

            }
        });
    }
    public void getWorld(){
        mNewsService.getWorldHeadlines().enqueue(new Callback<GetTopStories>() {
            @Override
            public void onResponse(Call<GetTopStories> call, Response<GetTopStories> response) {
                List<TopStories> articless = response.body().getArticles();
                mTopStoriesAdapter = new TopStoriesAdapter(articless,getActivity());
                mTopStoriesAdapter.notifyDataSetChanged();
                mRecyclerView2.setAdapter(mTopStoriesAdapter);
            }

            @Override
            public void onFailure(Call<GetTopStories> call, Throwable t) {

            }
        });
    }
}
