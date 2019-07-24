package com.bean;

import javax.persistence.*;

@Entity
@Table(name = "ordertypes", schema = "wpobase")
public class OrderType {
    private int ordertypeId;
    private String ordertypeName;

    @Id
    @Column(name = "ordertype_id")
    public int getOrdertypeId() {
        return ordertypeId;
    }

    public void setOrdertypeId(int ordertypeId) {
        this.ordertypeId = ordertypeId;
    }

    @Basic
    @Column(name = "ordertype_name")
    public String getOrdertypeName() {
        return ordertypeName;
    }

    public void setOrdertypeName(String ordertypeName) {
        this.ordertypeName = ordertypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderType that = (OrderType) o;

        if (ordertypeId != that.ordertypeId) return false;
        if (ordertypeName != null ? !ordertypeName.equals(that.ordertypeName) : that.ordertypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ordertypeId;
        result = 31 * result + (ordertypeName != null ? ordertypeName.hashCode() : 0);
        return result;
    }
}
