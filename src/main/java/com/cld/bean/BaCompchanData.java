package com.cld.bean;

import java.util.Date;

public class BaCompchanData {
    private Integer id;

    private Integer compChanId;

    private Date createdate;

    private String createby;

    private Date updatedate;

    private String updateby;

    private String deleflag;

    private String comtent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompChanId() {
        return compChanId;
    }

    public void setCompChanId(Integer compChanId) {
        this.compChanId = compChanId;
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

    public String getComtent() {
        return comtent;
    }

    public void setComtent(String comtent) {
        this.comtent = comtent == null ? null : comtent.trim();
    }
}