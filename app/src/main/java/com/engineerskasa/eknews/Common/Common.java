package com.engineerskasa.eknews.Common;

import com.engineerskasa.eknews.Interface.NewsService;
import com.engineerskasa.eknews.Remote.RetrofitClient;

public class Common {

    private static final String BASE_URL = "https://newsapi.org";

    public static final String API_KEY = "30c322fab66549fb981c9fd76843aa4e";

    public static NewsService getLatestNews(){
        return new RetrofitClient().getCLient(BASE_URL).create(NewsService.class);
    }

    public static String getSourceNews(String sources){
        StringBuilder string = new StringBuilder("https://newsapi.org/v2/everything?sources=");
        return string.append(sources)
                .append("&pageSize=100").append("&language=en")
                .append("&apiKey="+API_KEY).toString();
    }

}
