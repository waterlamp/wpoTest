package com.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "progress", schema = "wpobase")
public class Progress {
    @Id
    @Column(name = "progress_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressId;
    private String progressMis;
    private String progressProjectName;
    private BigDecimal progressLimit;
    private BigDecimal progressMainEquipment;
    private BigDecimal progressSupportEquipment;
    private BigDecimal progressMaterials;
    private BigDecimal progressTotal;
    @ManyToOne
    @JoinColumn(name = "progress_order_id")
    private Order order;


    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    @Basic
    @Column(name = "progress_mis")
    public String getProgressMis() {
        return progressMis;
    }

    public void setProgressMis(String progressMis) {
        this.progressMis = progressMis;
    }

    @Basic
    @Column(name = "progress_project_name")
    public String getProgressProjectName() {
        return progressProjectName;
    }

    public void setProgressProjectName(String progressProjectName) {
        this.progressProjectName = progressProjectName;
    }

    @Basic
    @Column(name = "progress_limit")
    public BigDecimal getProgressLimit() {
        return progressLimit;
    }

    public void setProgressLimit(BigDecimal progressLimit) {
        this.progressLimit = progressLimit;
    }

    @Basic
    @Column(name = "progress_main_equipment")
    public BigDecimal getProgressMainEquipment() {
        return progressMainEquipment;
    }

    public void setProgressMainEquipment(BigDecimal progressMainEquipment) {
        this.progressMainEquipment = progressMainEquipment;
    }

    @Basic
    @Column(name = "progress_support_equipment")
    public BigDecimal getProgressSupportEquipment() {
        return progressSupportEquipment;
    }

    public void setProgressSupportEquipment(BigDecimal progressSupportEquipment) {
        this.progressSupportEquipment = progressSupportEquipment;
    }

    @Basic
    @Column(name = "progress_materials")
    public BigDecimal getProgressMaterials() {
        return progressMaterials;
    }

    public void setProgressMaterials(BigDecimal progressMaterials) {
        this.progressMaterials = progressMaterials;
    }

    @Basic
    @Column(name = "progress_total")
    public BigDecimal getProgressTotal() {
        return progressTotal;
    }

    public void setProgressTotal(BigDecimal progressTotal) {
        this.progressTotal = progressTotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

//    public Set<Fee> getFees() {
//        return fees;
//    }
//
//    public void setFees(Set<Fee> fees) {
//        this.fees = fees;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Progress that = (Progress) o;

        if (progressId != that.progressId) return false;
        if (progressMis != null ? !progressMis.equals(that.progressMis) : that.progressMis != null) return false;
        if (progressProjectName != null ? !progressProjectName.equals(that.progressProjectName) : that.progressProjectName != null)
            return false;
        if (progressLimit != null ? !progressLimit.equals(that.progressLimit) : that.progressLimit != null)
            return false;
        if (progressMainEquipment != null ? !progressMainEquipment.equals(that.progressMainEquipment) : that.progressMainEquipment != null)
            return false;
        if (progressSupportEquipment != null ? !progressSupportEquipment.equals(that.progressSupportEquipment) : that.progressSupportEquipment != null)
            return false;
        if (progressMaterials != null ? !progressMaterials.equals(that.progressMaterials) : that.progressMaterials != null)
            return false;
        if (progressTotal != null ? !progressTotal.equals(that.progressTotal) : that.progressTotal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = progressId;
        result = 31 * result + (progressMis != null ? progressMis.hashCode() : 0);
        result = 31 * result + (progressProjectName != null ? progressProjectName.hashCode() : 0);
        result = 31 * result + (progressLimit != null ? progressLimit.hashCode() : 0);
        result = 31 * result + (progressMainEquipment != null ? progressMainEquipment.hashCode() : 0);
        result = 31 * result + (progressSupportEquipment != null ? progressSupportEquipment.hashCode() : 0);
        result = 31 * result + (progressMaterials != null ? progressMaterials.hashCode() : 0);
        result = 31 * result + (progressTotal != null ? progressTotal.hashCode() : 0);
        return result;
    }
}
