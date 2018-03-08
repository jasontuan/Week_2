package com.example.anhtuan.week_2.api;

import com.example.anhtuan.week_2.model.ModelMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleAPI {

    String URL_BASE = "http://api.nytimes.com/svc/search/v2/";

    @GET("articlesearch.json")
    Call<ModelMain> getAllArticle(@Query("api-key") String api_key, @Query("page") int page,
                                  @Query("q") String q, @Query("begin_date") String begin_date,
                                  @Query("sort") String sort);
}
