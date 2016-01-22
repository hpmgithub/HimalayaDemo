package com.jash.himalayademo.entities;

/**
 * Created by jash
 * Date: 16-1-22
 * Time: 上午9:51
 */
public class CategoryEntity {
    private int id;
    private String name;
    private String title;
    private boolean isChecked;
    private int orderNum;
    private String coverPath;
    private boolean selectedSwitch;
    private boolean isFinished;
    private String contentType;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public void setSelectedSwitch(boolean selectedSwitch) {
        this.selectedSwitch = selectedSwitch;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean isSelectedSwitch() {
        return selectedSwitch;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public String getContentType() {
        return contentType;
    }
}
