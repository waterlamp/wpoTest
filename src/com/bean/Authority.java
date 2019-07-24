package com.bean;

import javax.persistence.*;

@Entity
@Table(name = "authoritys", schema = "wpobase")
public class Authority {
    private int authorityId;
    private String authorityName;
    private Boolean authorityManage;
    private Boolean authorityPm;
    private Boolean authorityAffairs;
    @Id
    @Column(name = "authority_id")
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "authority_name")
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "authority_manage")
    public Boolean getAuthorityManage() {
        return authorityManage;
    }

    public void setAuthorityManage(Boolean authorityManage) {
        this.authorityManage = authorityManage;
    }

    @Basic
    @Column(name = "authority_pm")
    public Boolean getAuthorityPm() {
        return authorityPm;
    }

    public void setAuthorityPm(Boolean authorityPm) {
        this.authorityPm = authorityPm;
    }


    @Basic
    @Column(name = "authority_affairs")
    public Boolean getAuthorityAffairs() {
        return authorityAffairs;
    }

    public void setAuthorityAffairs(Boolean authorityAffairs) {
        this.authorityAffairs = authorityAffairs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority that = (Authority) o;

        if (authorityId != that.authorityId) return false;
        if (authorityName != null ? !authorityName.equals(that.authorityName) : that.authorityName != null)
            return false;
        if (authorityManage != null ? !authorityManage.equals(that.authorityManage) : that.authorityManage != null)
            return false;
        if (authorityPm != null ? !authorityPm.equals(that.authorityPm) : that.authorityPm != null) return false;
        if (authorityAffairs != null ? !authorityAffairs.equals(that.authorityAffairs) : that.authorityAffairs != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorityId;
        result = 31 * result + (authorityName != null ? authorityName.hashCode() : 0);
        result = 31 * result + (authorityManage != null ? authorityManage.hashCode() : 0);
        result = 31 * result + (authorityPm != null ? authorityPm.hashCode() : 0);
        result = 31 * result + (authorityAffairs != null ? authorityAffairs.hashCode() : 0);
        return result;
    }
}
