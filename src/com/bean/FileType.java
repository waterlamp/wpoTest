package com.bean;

import javax.persistence.*;

@Entity
@Table(name = "filetypes", schema = "wpobase")
public class FileType {
    private int filetypeId;
    private String filetypeName;
    private Integer filetypeFid;

    @Id
    @Column(name = "filetype_id")
    public int getFiletypeId() {
        return filetypeId;
    }

    public void setFiletypeId(int filetypeId) {
        this.filetypeId = filetypeId;
    }

    @Basic
    @Column(name = "filetype_name")
    public String getFiletypeName() {
        return filetypeName;
    }

    public void setFiletypeName(String filetypeName) {
        this.filetypeName = filetypeName;
    }

    @Basic
    @Column(name = "filetype_fid")
    public Integer getFiletypeFid() {
        return filetypeFid;
    }

    public void setFiletypeFid(Integer filetypeFid) {
        this.filetypeFid = filetypeFid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileType that = (FileType) o;

        if (filetypeId != that.filetypeId) return false;
        if (filetypeName != null ? !filetypeName.equals(that.filetypeName) : that.filetypeName != null) return false;
        if (filetypeFid != null ? !filetypeFid.equals(that.filetypeFid) : that.filetypeFid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filetypeId;
        result = 31 * result + (filetypeName != null ? filetypeName.hashCode() : 0);
        result = 31 * result + (filetypeFid != null ? filetypeFid.hashCode() : 0);
        return result;
    }
}
