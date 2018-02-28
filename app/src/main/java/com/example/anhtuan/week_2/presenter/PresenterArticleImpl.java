package com.example.anhtuan.week_2.presenter;

import com.example.anhtuan.week_2.api.ArticleAPI;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.model.ModelMain;
import com.example.anhtuan.week_2.model.ResponseArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterArticleImpl implements IArticle.PresenterArticle {

    IArticle.IView iView;
    ResponseArticle responseArticle;

    public PresenterArticleImpl(IArticle.IView iView, ResponseArticle responseArticle) {
        this.iView = iView;
        this.responseArticle = responseArticle;
    }

    public ResponseArticle getResponseArticle() {
        return responseArticle;
    }

    @Override
    public void getDataArticle(ArticleAPI articleAPI, int page) {
        String api_key = "e579cea5b21249dd939b702102f3cef2";
        Call<ModelMain> call = articleAPI.getAllArticle(api_key, page);
        call.enqueue(new Callback<ModelMain>() {
            @Override
            public void onResponse(Call<ModelMain> call, Response<ModelMain> response) {
                if (response.body() != null) {

                }
            }

            @Override
            public void onFailure(Call<ModelMain> call, Throwable t) {

            }
        });
    }
}
