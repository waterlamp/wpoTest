package com.bean;

import javax.persistence.*;

@Entity
@Table(name = "orderflow", schema = "wpobase")
public class OrderFlow {
    @Id
    @Column(name = "orderflow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderflowId;
    private int orderTypeId;
    private String orderflowName;
    private Integer orderflowPrev;


    public int getOrderflowId() {
        return orderflowId;
    }

    public void setOrderflowId(int orderflowId) {
        this.orderflowId = orderflowId;
    }

    @Basic
    @Column(name = "orderflow_name")
    public String getOrderflowName() {
        return orderflowName;
    }

    public void setOrderflowName(String orderflowName) {
        this.orderflowName = orderflowName;
    }

    @Basic
    @Column(name = "orderflow_prev")
    public Integer getOrderflowPrev() {
        return orderflowPrev;
    }

    public void setOrderflowPrev(Integer orderflowPrev) {
        this.orderflowPrev = orderflowPrev;
    }

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderFlow that = (OrderFlow) o;

        if (orderflowId != that.orderflowId) return false;
        if (orderflowName != null ? !orderflowName.equals(that.orderflowName) : that.orderflowName != null)
            return false;
        if (orderflowPrev != null ? !orderflowPrev.equals(that.orderflowPrev) : that.orderflowPrev != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderflowId;
        result = 31 * result + (orderflowName != null ? orderflowName.hashCode() : 0);
        result = 31 * result + (orderflowPrev != null ? orderflowPrev.hashCode() : 0);
        return result;
    }
}
