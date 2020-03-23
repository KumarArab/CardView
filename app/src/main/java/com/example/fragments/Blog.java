package com.example.fragments;

public class Blog {

    private String title;
    private String desc;
    private String image;

    public Blog() {

    }

//        Blog(String title, String desc, String image) {
//        this.title = title;
//        this.desc = desc;
//        this.image = image;
//    }

     String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

     String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

     String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
