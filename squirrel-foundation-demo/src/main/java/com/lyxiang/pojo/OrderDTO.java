package com.lyxiang.pojo;

import java.util.Date;

/**
 * @author: liyuxiang
 * @create: 2019-01-21
 */
public class OrderDTO {

    private Integer id;

    private String state;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public OrderDTO(String state) {
        this.state = state;
    }

    public OrderDTO() {
    }
}
