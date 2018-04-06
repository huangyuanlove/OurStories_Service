package com.huangyuanlove.ourstories.entites;

import java.util.Date;

public class Confidence {

    private int id;
    private int fromUserId;
    private String targetName;
    private String content;
    private String images;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Confidence{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", targetName='" + targetName + '\'' +
                ", content='" + content + '\'' +
                ", images='" + images + '\'' +
                ", time=" + time +
                '}';
    }
}
