package com.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fees", schema = "wpobase")
public class Fee {
    private int feeId;
    private String feeType;
    private BigDecimal feePact;
    private String feeAudit;
    private BigDecimal feeAuditMoney;
    private BigDecimal feePrev;
    private Byte feePercent;
    private BigDecimal feeNow;
    private BigDecimal feeExpenses;
    private BigDecimal feeSubtotal;
    @ManyToOne
    @JoinColumn(name = "fee_progress_id",insertable =false,updatable = false)
    private Progress progress;

    @Id
    @Column(name = "fee_id")
    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    @Basic
    @Column(name = "fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Basic
    @Column(name = "fee_pact")
    public BigDecimal getFeePact() {
        return feePact;
    }

    public void setFeePact(BigDecimal feePact) {
        this.feePact = feePact;
    }

    @Basic
    @Column(name = "fee_audit")
    public String getFeeAudit() {
        return feeAudit;
    }

    public void setFeeAudit(String feeAudit) {
        this.feeAudit = feeAudit;
    }

    @Basic
    @Column(name = "fee_audit_money")
    public BigDecimal getFeeAuditMoney() {
        return feeAuditMoney;
    }

    public void setFeeAuditMoney(BigDecimal feeAuditMoney) {
        this.feeAuditMoney = feeAuditMoney;
    }

    @Basic
    @Column(name = "fee_prev")
    public BigDecimal getFeePrev() {
        return feePrev;
    }

    public void setFeePrev(BigDecimal feePrev) {
        this.feePrev = feePrev;
    }

    @Basic
    @Column(name = "fee_percent")
    public Byte getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(Byte feePercent) {
        this.feePercent = feePercent;
    }

    @Basic
    @Column(name = "fee_now")
    public BigDecimal getFeeNow() {
        return feeNow;
    }

    public void setFeeNow(BigDecimal feeNow) {
        this.feeNow = feeNow;
    }

    @Basic
    @Column(name = "fee_expenses")
    public BigDecimal getFeeExpenses() {
        return feeExpenses;
    }

    public void setFeeExpenses(BigDecimal feeExpenses) {
        this.feeExpenses = feeExpenses;
    }

    @Basic
    @Column(name = "fee_subtotal")
    public BigDecimal getFeeSubtotal() {
        return feeSubtotal;
    }

    public void setFeeSubtotal(BigDecimal feeSubtotal) {
        this.feeSubtotal = feeSubtotal;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fee that = (Fee) o;

        if (feeId != that.feeId) return false;
        if (feeType != null ? !feeType.equals(that.feeType) : that.feeType != null) return false;
        if (feePact != null ? !feePact.equals(that.feePact) : that.feePact != null) return false;
        if (feeAudit != null ? !feeAudit.equals(that.feeAudit) : that.feeAudit != null) return false;
        if (feeAuditMoney != null ? !feeAuditMoney.equals(that.feeAuditMoney) : that.feeAuditMoney != null)
            return false;
        if (feePrev != null ? !feePrev.equals(that.feePrev) : that.feePrev != null) return false;
        if (feePercent != null ? !feePercent.equals(that.feePercent) : that.feePercent != null) return false;
        if (feeNow != null ? !feeNow.equals(that.feeNow) : that.feeNow != null) return false;
        if (feeExpenses != null ? !feeExpenses.equals(that.feeExpenses) : that.feeExpenses != null) return false;
        if (feeSubtotal != null ? !feeSubtotal.equals(that.feeSubtotal) : that.feeSubtotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = feeId;
        result = 31 * result + (feeType != null ? feeType.hashCode() : 0);
        result = 31 * result + (feePact != null ? feePact.hashCode() : 0);
        result = 31 * result + (feeAudit != null ? feeAudit.hashCode() : 0);
        result = 31 * result + (feeAuditMoney != null ? feeAuditMoney.hashCode() : 0);
        result = 31 * result + (feePrev != null ? feePrev.hashCode() : 0);
        result = 31 * result + (feePercent != null ? feePercent.hashCode() : 0);
        result = 31 * result + (feeNow != null ? feeNow.hashCode() : 0);
        result = 31 * result + (feeExpenses != null ? feeExpenses.hashCode() : 0);
        result = 31 * result + (feeSubtotal != null ? feeSubtotal.hashCode() : 0);
        return result;
    }
}
