package com.example.anhtuan.week_2.model;

import java.util.List;

public class ImageFilter {

    String imageName;
    String imageLink;
    boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ImageFilter(String imageName, String imageLink) {
        this.imageName = imageName;
        this.imageLink = imageLink;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
