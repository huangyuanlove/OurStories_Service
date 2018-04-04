package com.huangyuanlove.ourstories.bean;

import java.util.Date;

public class TestBean {
    private int actor_id;
    private String first_name;
    private String last_name;
    private Date last_npdate;

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLast_npdate() {
        return last_npdate;
    }

    public void setLast_npdate(Date last_npdate) {
        this.last_npdate = last_npdate;
    }
}
