package com.jash.himalayademo.entities;

import java.util.List;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午10:32
 */
public class FocusImages {
    private String title;
    private List<FocusImageEntity> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FocusImageEntity> getList() {
        return list;
    }

    public void setList(List<FocusImageEntity> list) {
        this.list = list;
    }
}
