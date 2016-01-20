package com.jash.himalayademo.entities;

import java.util.List;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午10:30
 */
public class HotRecommendEntity {
    private String title;
    private List<RecommendEntity> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RecommendEntity> getList() {
        return list;
    }

    public void setList(List<RecommendEntity> list) {
        this.list = list;
    }
}
