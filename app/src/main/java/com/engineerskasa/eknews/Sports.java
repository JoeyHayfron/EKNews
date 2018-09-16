package com.engineerskasa.eknews;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.engineerskasa.eknews.Adapters.ChecklistAdapter;
import com.engineerskasa.eknews.Adapters.SourceNewsAdapter;
import com.engineerskasa.eknews.Common.Common;
import com.engineerskasa.eknews.Interface.NewsService;
import com.engineerskasa.eknews.Models.GetNews;
import com.engineerskasa.eknews.Models.GetTopStories;
import com.engineerskasa.eknews.Models.News;
import com.engineerskasa.eknews.Models.TopStories;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Sports extends Fragment {

    LayoutInflater mInflater;
    ViewGroup mContainer;
    View a, b;
    RecyclerView mRecyclerView, mRecyclerView1;
    RecyclerView.LayoutManager mLayoutManager, mLayoutManager1;
    ChecklistAdapter mChecklistAdapter;
    NewsService mNewsService;
    Button done;
    TextView test;
    SourceNewsAdapter mSourceNewsAdapter;
    static String lol1;

    public Sports() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sports, container, false);
        mInflater = inflater;
        mContainer = container;

        mNewsService = Common.getLatestNews();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.checkRecycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView1 = (RecyclerView) v.findViewById(R.id.sportsRecycle);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new LinearLayoutManager(getActivity());
        mRecyclerView1.setLayoutManager(mLayoutManager1);

        done = (Button) v.findViewById(R.id.btnDone);
        test = (TextView) v.findViewById(R.id.test);
        a = v.findViewById(R.id.view1);
        b = v.findViewById(R.id.view2);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String name = sharedPreferences.getString("Key", "");

        if (name != "")
        {
            a.setVisibility(View.GONE);
            b.setVisibility(View.VISIBLE);
            mNewsService.getNewsfromSource(Common.getSourceNews(name)).enqueue(new Callback<GetTopStories>() {
                @Override
                public void onResponse(Call<GetTopStories> call, Response<GetTopStories> response) {
                    List<TopStories> articles = response.body().getArticles();
                    mSourceNewsAdapter = new SourceNewsAdapter(articles, getActivity());
                    mSourceNewsAdapter.notifyDataSetChanged();
                    mRecyclerView1.setAdapter(mSourceNewsAdapter);
                }
                @Override
                public void onFailure(Call<GetTopStories> call, Throwable t) {
                }
            });
        }else
        {
            showList();
        }


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSource();
            }
        });




        // Inflate the layout for this fragment
        return v;
    }

    public void showList() {
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.GONE);
            mNewsService.getSportsSources().enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    List<GetNews> getSources = response.body().getSources();
                    mChecklistAdapter = new ChecklistAdapter(response.body().getSources(), getActivity());
                    mChecklistAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mChecklistAdapter);
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }

    public void showSource() {
        mNewsService.getSportsSources().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                SparseBooleanArray selectedRows = mChecklistAdapter.getSelectedIds();
                if (selectedRows.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < selectedRows.size(); i++) {
                        if (selectedRows.valueAt(i)) {
                            String selectedRowLabel = response.body().getSources().get(selectedRows.keyAt(i)).getId();
                            stringBuilder.append(selectedRowLabel + ",");
                        }
                    }
                    String lol = stringBuilder.toString();
                    lol1 = lol.substring(0, lol.length() - 1);

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Key", lol1);
                    editor.commit();
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.VISIBLE);
                    mNewsService.getNewsfromSource(Common.getSourceNews(lol1)).enqueue(new Callback<GetTopStories>() {
                        @Override
                        public void onResponse(Call<GetTopStories> call, Response<GetTopStories> response) {
                            List<TopStories> articles = response.body().getArticles();
                            mSourceNewsAdapter = new SourceNewsAdapter(articles, getActivity());
                            mSourceNewsAdapter.notifyDataSetChanged();
                            mRecyclerView1.setAdapter(mSourceNewsAdapter);
                        }
                        @Override
                        public void onFailure(Call<GetTopStories> call, Throwable t) {
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });
    }

}