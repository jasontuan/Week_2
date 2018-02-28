package com.example.anhtuan.week_2.api;

import com.example.anhtuan.week_2.model.ModelMain;
import com.example.anhtuan.week_2.model.ResponseArticle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleAPI {

    String URL_BASE = "http://api.nytimes.com/svc/search/v2/";

    @GET("articlesearch.json")
    Call<ModelMain<ResponseArticle>> getAllArticle(@Query("api-key") String api_key, @Query("page") int page);

}
