package com.engineerskasa.eknews.Models;

import java.util.List;

public class News {

    private String status;
    private List<GetNews> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetNews> getSources() {
        return sources;
    }

    public void setSources(List<GetNews> sources) {
        this.sources = sources;
    }

    public News(String status, List<GetNews> sources) {

        this.status = status;
        this.sources = sources;
    }
}
