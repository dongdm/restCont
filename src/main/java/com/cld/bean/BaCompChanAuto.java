package com.cld.bean;

import java.util.Date;

public class BaCompChanAuto {
    private Integer id;

    private Integer compId;

    private Integer chanId;

    private Date createdate;

    private String createby;

    private Date updatedate;

    private String updateby;

    private String deleflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public Integer getChanId() {
        return chanId;
    }

    public void setChanId(Integer chanId) {
        this.chanId = chanId;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public String getDeleflag() {
        return deleflag;
    }

    public void setDeleflag(String deleflag) {
        this.deleflag = deleflag == null ? null : deleflag.trim();
    }
}