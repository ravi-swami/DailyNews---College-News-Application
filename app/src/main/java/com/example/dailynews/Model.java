package com.example.dailynews;

public class Model {
    String title, date, imageUrl, content;

    public Model(String title, String date, String imageUrl, String content) {
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public Model() {
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContent() {
        return content;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
