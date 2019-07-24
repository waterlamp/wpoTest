package com.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.json.annotations.JSONFieldBridge;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects", schema = "wpobase")
public class Project{
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @Basic
    @Column(name = "project_mis")
    private String projectMis;
    @Basic
    @Column(name = "project_name")
    private String projectName;
    @Basic
    @Column(name = "project_findate")
    @JSONField(format = "yyyy-MM-dd")
    private Date projectFindate;
    @Basic
    @Column(name = "project__predate")
    @JSONField(format = "yyyy-MM-dd")
    private Date projectPredate;
    @Basic
    @Column(name = "project_turn")
    private Boolean projectTurn;
    @Basic
    @Column(name = "project_turndate")
    @JSONField(format = "yyyy-MM-dd")
    private Date projectTurndate;
    @Basic
    @Column(name = "project_address")
    private String projectAddress;

    @Basic
    @Column(name = "project_type")
    private String projectType;
    @Basic
    @Column(name = "project_storage_life")
    private String projectStorageLife;
    @Basic
    @Column(name = "project_boxs_count")
    private Integer projectBoxsCount;
    @Basic
    @Column(name = "project_files_count")
    private Integer projectFilesCount;
    @Basic
    @Column(name = "project_users_id")
    private String userId;
    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "project_users_id",insertable =false,updatable = false)
    private User user;
//    @JSONField(serialize = false)
//    private Set<File> files;

    public Project() {
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    public String getProjectMis() {
        return projectMis;
    }

    public void setProjectMis(String projectMis) {
        this.projectMis = projectMis;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public Date getProjectFindate() {
        return projectFindate;
    }

    public void setProjectFindate(Date projectFindate) {
        this.projectFindate = projectFindate;
    }


    public Date getProjectPredate() {
        return projectPredate;
    }

    public void setProjectPredate(Date projectPredate) {
        this.projectPredate = projectPredate;
    }


    public Boolean getProjectTurn() {
        return projectTurn;
    }

    public void setProjectTurn(Boolean projectTurn) {
        this.projectTurn = projectTurn;
    }

    public Date getProjectTurndate() {
        return projectTurndate;
    }

    public void setProjectTurndate(Date projectTurndate) {
        this.projectTurndate = projectTurndate;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }


    public String getProjectStorageLife() {
        return projectStorageLife;
    }

    public void setProjectStorageLife(String projectStorageLife) {
        this.projectStorageLife = projectStorageLife;
    }


    public Integer getProjectBoxsCount() {
        return projectBoxsCount;
    }

    public void setProjectBoxsCount(Integer projectBoxsCount) {
        this.projectBoxsCount = projectBoxsCount;
    }

    public Integer getProjectFilesCount() {
        return projectFilesCount;
    }

    public void setProjectFilesCount(Integer projectFilesCount) {
        this.projectFilesCount = projectFilesCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    public Set<File> getFiles() {
//        return files;
//    }
//
//    public void setFiles(Set<File> files) {
//        this.files = files;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project that = (Project) o;

        if (projectId != that.projectId) return false;
        if (projectMis != null ? !projectMis.equals(that.projectMis) : that.projectMis != null) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
        if (projectFindate != null ? !projectFindate.equals(that.projectFindate) : that.projectFindate != null)
            return false;
        if (projectPredate != null ? !projectPredate.equals(that.projectPredate) : that.projectPredate != null)
            return false;
        if (projectTurn != null ? !projectTurn.equals(that.projectTurn) : that.projectTurn != null) return false;
        if (projectTurndate != null ? !projectTurndate.equals(that.projectTurndate) : that.projectTurndate != null)
            return false;
        if (projectAddress != null ? !projectAddress.equals(that.projectAddress) : that.projectAddress != null)
            return false;
        if (projectType != null ? !projectType.equals(that.projectType) : that.projectType != null) return false;
        if (projectStorageLife != null ? !projectStorageLife.equals(that.projectStorageLife) : that.projectStorageLife != null)
            return false;
        if (projectBoxsCount != null ? !projectBoxsCount.equals(that.projectBoxsCount) : that.projectBoxsCount != null)
            return false;
        if (projectFilesCount != null ? !projectFilesCount.equals(that.projectFilesCount) : that.projectFilesCount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + (projectMis != null ? projectMis.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (projectFindate != null ? projectFindate.hashCode() : 0);
        result = 31 * result + (projectPredate != null ? projectPredate.hashCode() : 0);
        result = 31 * result + (projectTurn != null ? projectTurn.hashCode() : 0);
        result = 31 * result + (projectTurndate != null ? projectTurndate.hashCode() : 0);
        result = 31 * result + (projectAddress != null ? projectAddress.hashCode() : 0);
        result = 31 * result + (projectType != null ? projectType.hashCode() : 0);
        result = 31 * result + (projectStorageLife != null ? projectStorageLife.hashCode() : 0);
        result = 31 * result + (projectBoxsCount != null ? projectBoxsCount.hashCode() : 0);
        result = 31 * result + (projectFilesCount != null ? projectFilesCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectMis='" + projectMis + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectFindate=" + projectFindate +
                ", projectPredate=" + projectPredate +
                ", projectTurn=" + projectTurn +
                ", projectTurndate=" + projectTurndate +
                ", projectAddress='" + projectAddress + '\'' +
                ", projectType='" + projectType + '\'' +
                ", projectStorageLife='" + projectStorageLife + '\'' +
                ", projectBoxsCount=" + projectBoxsCount +
                ", projectFilesCount=" + projectFilesCount +
                ", userId='" + userId + '\'' +
                '}';
    }
}
