package com.jash.himalayademo.entities;

import java.util.List;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午10:27
 */
public class DiscoveryColumnEntity {
    private String title;
    private List<DiscoveryEntity> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DiscoveryEntity> getList() {
        return list;
    }

    public void setList(List<DiscoveryEntity> list) {
        this.list = list;
    }
}
