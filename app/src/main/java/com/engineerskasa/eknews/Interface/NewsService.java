package com.engineerskasa.eknews.Interface;

import com.engineerskasa.eknews.Common.Common;
import com.engineerskasa.eknews.Models.GetNews;
import com.engineerskasa.eknews.Models.GetTopStories;
import com.engineerskasa.eknews.Models.News;
import com.engineerskasa.eknews.Models.TopStories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET("/v2/everything?domains=bbc.co.uk,cnn.com,nytimes.com&pageSize=12&language=en&apiKey="+ Common.API_KEY)
    Call<GetTopStories> getHeadlines();

    @GET("/v2/top-headlines?sources=bleacher-report&pageSize=4&apiKey="+ Common.API_KEY)
    Call<GetTopStories> getSportsHeadlines();

    @GET("/v2/top-headlines?sources=al-jazeera-english&pageSize=4&apiKey="+ Common.API_KEY)
    Call<GetTopStories> getWorldHeadlines();

    @GET("/v2/sources?&category=Sports&apiKey="+Common.API_KEY)
    Call<News> getSportsSources();

    @GET
    Call<GetTopStories> getNewsfromSource(@Url String string);

}
