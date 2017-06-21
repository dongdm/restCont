package com.cld.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaComp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String compType;

    private String product;

    private Date createdate;

    private String createby;

    private Date updatedate;

    private String updateby;

    private String deleflag;

    private List<Integer> chanids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType == null ? null : compType.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
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

    public List<Integer> getChanids(){return chanids;}

    public void setChanids(List<Integer> chanids){this.chanids = chanids;}

}