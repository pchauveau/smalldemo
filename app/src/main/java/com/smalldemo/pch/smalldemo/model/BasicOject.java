package com.smalldemo.pch.smalldemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Basic object to reflect the http://jsonplaceholder.typicode.com/photos model for use with Retrofit/Gson
 * Implementing ItemInterface to respect the contract and allowing the swap this object for another.
 * GreenDao on this object to keep it in DB
 */
@Entity
public class BasicOject implements ItemInterface {
    int albumId;
    @Id
    long id;
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

    @Generated(hash = 375493391)
    public BasicOject(int albumId, long id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean isThumbnailToShow() {
        return true;
    }

    @Override
    public void switchImageToShow() {

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
