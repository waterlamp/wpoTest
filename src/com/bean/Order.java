package com.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "orders", schema = "wpobase")
public class Order {
    @Id
    @GenericGenerator(name = "order-uuid",strategy = "uuid")
    @GeneratedValue(generator = "order-uuid")
    @Column(name = "order_id")
    private String orderId;
    private String orderUserId;
    private Integer orderTypeId;
    private Timestamp orderStarttime;
    private String orderName;
    private Integer orderState;
    @ManyToOne
    @JoinColumn(name = "order_user_id",insertable = false,updatable = false)
    @JSONField(serialize = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "order_ordertype_id",insertable = false,updatable = false)
    @JSONField(serialize = false)
    private OrderType orderType;
//    @JSONField(serialize = false)
//    private Set<Progress> progress;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "order_user_id")
    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    @Basic
    @Column(name = "order_ordertype_id")
    public Integer getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(Integer orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    @Basic
    @Column(name = "order_starttime")
    public Timestamp getOrderStarttime() {
        return orderStarttime;
    }

    public void setOrderStarttime(Timestamp orderStarttime) {
        this.orderStarttime = orderStarttime;
    }

    @Basic
    @Column(name = "order_name")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Basic
    @Column(name = "order_state")
    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

//    public Set<Progress> getProgress() {
//        return progress;
//    }
//
//    public void setProgress(Set<Progress> progress) {
//        this.progress = progress;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (orderUserId != null ? !orderUserId.equals(that.orderUserId) : that.orderUserId != null) return false;
        if (orderTypeId != null ? !orderTypeId.equals(that.orderTypeId) : that.orderTypeId != null)
            return false;
        if (orderStarttime != null ? !orderStarttime.equals(that.orderStarttime) : that.orderStarttime != null)
            return false;
        if (orderName != null ? !orderName.equals(that.orderName) : that.orderName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (orderUserId != null ? orderUserId.hashCode() : 0);
        result = 31 * result + (orderTypeId != null ? orderTypeId.hashCode() : 0);
        result = 31 * result + (orderStarttime != null ? orderStarttime.hashCode() : 0);
        result = 31 * result + (orderName != null ? orderName.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderUserId='" + orderUserId + '\'' +
                ", orderTypeId=" + orderTypeId +
                ", orderStarttime=" + orderStarttime +
                ", orderName='" + orderName + '\'' +
                ", orderState=" + orderState +
                '}';
    }
}
