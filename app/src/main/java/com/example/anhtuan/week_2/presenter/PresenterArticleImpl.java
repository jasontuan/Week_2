package com.example.anhtuan.week_2.presenter;

import com.example.anhtuan.week_2.api.ArticleAPI;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.model.Doc;
import com.example.anhtuan.week_2.model.ModelMain;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterArticleImpl implements IArticle.PresenterArticle {

    IArticle.IView iView;
    List<Doc> docList;

    public PresenterArticleImpl(IArticle.IView iView) {
        this.iView = iView;
        this.docList = new ArrayList<>();
    }

    public List<Doc> getDocList() {
        return docList;
    }

    @Override
    public void getDataArticle(ArticleAPI articleAPI, int page, String q, String begin_date, String sort) {
        String api_key = "e579cea5b21249dd939b702102f3cef2";
        Call<ModelMain> call = articleAPI.getAllArticle(api_key, page, q, begin_date, sort);
        call.enqueue(new Callback<ModelMain>() {
            @Override
            public void onResponse(Call<ModelMain> call, Response<ModelMain> response) {
                if (response.body() != null) {
                    docList.addAll(response.body().getResponseArticle().getDocs());
                }
                iView.showDataSuccess();
            }

            @Override
            public void onFailure(Call<ModelMain> call, Throwable t) {
                iView.showDataFail();
            }
        });
    }
}
