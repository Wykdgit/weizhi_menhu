package com.wizz.demo.model;

public class WizzHistory {
    private int id;
    private String mainTitle;//主标题
    private String briefIntro;//简介

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

}
