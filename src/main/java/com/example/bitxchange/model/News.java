package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "news")
public class News {
    @Id
    private String id;
    private String title;
    private String content;
    private String url;
    private String source;
    private LocalDateTime publishedAt;



    public News() {
    }

    public News(String id, String title, String content , String url, String source, LocalDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.source = source;
        this.publishedAt = publishedAt;
    }

    public News(String title, String content, String url, String source, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.source = source;
        this.publishedAt = publishedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}

