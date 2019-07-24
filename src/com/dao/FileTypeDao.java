package com.dao;

import com.bean.FileType;

import java.sql.SQLException;
import java.util.List;

public interface FileTypeDao {
    List<FileType> getFileTypes(int fid) throws SQLException;
    List<FileType> getFileTypes() throws SQLException;
    FileType getFileType(int id) throws SQLException;

}
