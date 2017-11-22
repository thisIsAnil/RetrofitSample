package com.infi.samplerv.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by INFIi on 11/21/2017.
 */

public class Model {


    @SerializedName("albumId")
    private Integer albumId;
    @SerializedName("id")
    private Integer index;
    @SerializedName("title")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public Model(Integer albumId, Integer index, String name, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.index = index;
        this.name = name;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "albumId: "+albumId+" index: "+index+" Title: "+name+" url "+url;
    }
}
