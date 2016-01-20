package com.jash.himalayademo.entities;

import java.util.List;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午10:33
 */
public class BulletArea {
    private String title;
    private List<TrackEntity> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TrackEntity> getList() {
        return list;
    }

    public void setList(List<TrackEntity> list) {
        this.list = list;
    }
}
