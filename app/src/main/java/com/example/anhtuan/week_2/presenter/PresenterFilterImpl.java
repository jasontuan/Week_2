package com.example.anhtuan.week_2.presenter;

import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.model.ImageFilter;

import java.util.ArrayList;
import java.util.List;

public class PresenterFilterImpl implements IArticle.PresenterFilter {

    IArticle.IView iView;
    List<ImageFilter> imageFilterList;

    public PresenterFilterImpl(IArticle.IView iView) {
        this.iView = iView;
        this.imageFilterList = new ArrayList<>();
    }

    public List<ImageFilter> getImageFilterList() {
        return imageFilterList;
    }

    @Override
    public void getDataFilter() {
        imageFilterList.add(new ImageFilter("Art", "https://images.pexels.com/photos/368774/pexels-photo-368774.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"));
        imageFilterList.add(new ImageFilter("Fashion", "https://images.pexels.com/photos/291762/pexels-photo-291762.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"));
        imageFilterList.add(new ImageFilter("Sport", "https://images.pexels.com/photos/186076/pexels-photo-186076.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"));
        imageFilterList.add(new ImageFilter("Technology", "https://images.pexels.com/photos/325153/pexels-photo-325153.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"));

        iView.showDataSuccess();
    }
}
