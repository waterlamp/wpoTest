package com.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "files", schema = "wpobase")
public class File {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;
    @Basic
    @Column(name = "file_record_num")
    private String fileRecordNum;
    @Basic
    @Column(name = "file_box_num")
    private String fileBoxNum;
    @Basic
    @Column(name = "file_num")
    private String fileNum;
    @Basic
    @Column(name = "file_company")
    private String fileCompany;
    @Basic
    @Column(name = "file_name")
    private String fileName;
    @Basic
    @Column(name = "file_date")
    @JSONField(format = "yyyy-MM-dd")
    private Date fileDate;
    @Basic
    @Column(name = "file_count")
    private Integer fileCount;
    @Basic
    @Column(name = "file_fileType_id")
    private Integer fileTypeId;
    @ManyToOne
    @JoinColumn(name ="file_fileType_id",insertable =false,updatable = false )

    private FileType fileType;
    @Basic
    @Column(name = "file_project_id")
    private int projectId;
    @ManyToOne
    @JoinColumn(name ="file_project_id",insertable =false,updatable = false )
    @JSONField(serialize = false)
    private Project project;


    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }


    public String getFileRecordNum() {
        return fileRecordNum;
    }

    public void setFileRecordNum(String fileRecordNum) {
        this.fileRecordNum = fileRecordNum;
    }


    public String getFileBoxNum() {
        return fileBoxNum;
    }

    public void setFileBoxNum(String fileBoxNum) {
        this.fileBoxNum = fileBoxNum;
    }


    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }


    public String getFileCompany() {
        return fileCompany;
    }

    public void setFileCompany(String fileCompany) {
        this.fileCompany = fileCompany;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }


    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File that = (File) o;

        if (fileId != that.fileId) return false;
        if (fileRecordNum != null ? !fileRecordNum.equals(that.fileRecordNum) : that.fileRecordNum != null)
            return false;
        if (fileBoxNum != null ? !fileBoxNum.equals(that.fileBoxNum) : that.fileBoxNum != null) return false;
        if (fileNum != null ? !fileNum.equals(that.fileNum) : that.fileNum != null) return false;
        if (fileCompany != null ? !fileCompany.equals(that.fileCompany) : that.fileCompany != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (fileDate != null ? !fileDate.equals(that.fileDate) : that.fileDate != null) return false;
        if (fileCount != null ? !fileCount.equals(that.fileCount) : that.fileCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileId;
        result = 31 * result + (fileRecordNum != null ? fileRecordNum.hashCode() : 0);
        result = 31 * result + (fileBoxNum != null ? fileBoxNum.hashCode() : 0);
        result = 31 * result + (fileNum != null ? fileNum.hashCode() : 0);
        result = 31 * result + (fileCompany != null ? fileCompany.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileDate != null ? fileDate.hashCode() : 0);
        result = 31 * result + (fileCount != null ? fileCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileRecordNum='" + fileRecordNum + '\'' +
                ", fileBoxNum='" + fileBoxNum + '\'' +
                ", fileNum='" + fileNum + '\'' +
                ", fileCompany='" + fileCompany + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileDate=" + fileDate +
                ", fileCount=" + fileCount +
                ", fileTypeId=" + fileTypeId +
                ", projectId=" + projectId +
                '}';
    }
}
