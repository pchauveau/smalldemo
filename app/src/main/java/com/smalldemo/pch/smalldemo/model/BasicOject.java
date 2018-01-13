package com.smalldemo.pch.smalldemo.model;

/**
 * Basic object to reflect the http://jsonplaceholder.typicode.com/photos model
 * Implementing ItemInterface to respect the contract and allowing the swap this object for another.
 */
public class BasicOject implements ItemInterface {
    int albumId;
    int id;
    String title;
    String url;
    String thumbnailUrl;

    public BasicOject() {
        albumId = -1;
        id = -1;
    }

    public BasicOject(String title) {
        albumId = -1;
        id = -1;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getLinkThumbnail() {
        return getThumbnailUrl();
    }

    @Override
    public String getLinkFullsizeImage() {
        return getUrl();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
