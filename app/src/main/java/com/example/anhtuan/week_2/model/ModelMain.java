package com.example.anhtuan.week_2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMain {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private ResponseArticle responseArticle;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ResponseArticle getResponseArticle() {
        return responseArticle;
    }

    public void setResponseArticle(ResponseArticle responseArticle) {
        this.responseArticle = responseArticle;
    }
}
