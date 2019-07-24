package com.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "approves", schema = "wpobase")
public class Approve {
    @Id
    @Column(name = "approve_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approveId;
    private String approveOpinion;
    private Timestamp approveStartTime;
    private Timestamp approveEndTime;


    public int getApproveId() {
        return approveId;
    }

    public void setApproveId(int approveId) {
        this.approveId = approveId;
    }

    @Basic
    @Column(name = "approve_opinion")
    public String getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    @Basic
    @Column(name = "approve_start_time")
    public Timestamp getApproveStartTime() {
        return approveStartTime;
    }

    public void setApproveStartTime(Timestamp approveStartTime) {
        this.approveStartTime = approveStartTime;
    }

    @Basic
    @Column(name = "approve_end_time")
    public Timestamp getApproveEndTime() {
        return approveEndTime;
    }

    public void setApproveEndTime(Timestamp approveEndTime) {
        this.approveEndTime = approveEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Approve that = (Approve) o;

        if (approveId != that.approveId) return false;
        if (approveOpinion != null ? !approveOpinion.equals(that.approveOpinion) : that.approveOpinion != null)
            return false;
        if (approveStartTime != null ? !approveStartTime.equals(that.approveStartTime) : that.approveStartTime != null)
            return false;
        if (approveEndTime != null ? !approveEndTime.equals(that.approveEndTime) : that.approveEndTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = approveId;
        result = 31 * result + (approveOpinion != null ? approveOpinion.hashCode() : 0);
        result = 31 * result + (approveStartTime != null ? approveStartTime.hashCode() : 0);
        result = 31 * result + (approveEndTime != null ? approveEndTime.hashCode() : 0);
        return result;
    }
}
