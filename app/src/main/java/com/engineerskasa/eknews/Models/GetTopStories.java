package com.engineerskasa.eknews.Models;

import java.util.List;

public class GetTopStories {
    private String status,source;
    private List<TopStories> articles;

    public GetTopStories(String status, String source, List<TopStories> articles) {
        this.status = status;
        this.source = source;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<TopStories> getArticles() {
        return articles;
    }

    public void setArticles(List<TopStories> articles) {
        this.articles = articles;
    }
}
