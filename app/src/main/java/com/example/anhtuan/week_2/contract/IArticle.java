package com.example.anhtuan.week_2.contract;

import com.example.anhtuan.week_2.api.ArticleAPI;

public interface IArticle {

    interface OnItemClickListener {
        void OnItemClick(int position, boolean selected);
    }

    interface PresenterArticle {
        void getDataArticle(ArticleAPI articleAPI, int page, String q,
                            String begin_date, String sort);
    }

    interface PresenterFilter {
        void getDataFilter();
    }

    interface IView {
        void showDataSuccess();

        void showDataFail();
    }
}
