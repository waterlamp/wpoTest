package com.dao;

import com.bean.File;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FileDao {

    int fileCount(int projectId) throws SQLException;
    List<String> getBoxNums(int projectId) throws SQLException;
    Map<Integer,Integer> perTypeCount(int projectId) throws SQLException;
    List<File> findAll(int projectId) throws SQLException;
    File getFile(int fileId) throws SQLException;
    List<String> getRecordNums(int projectId) throws SQLException;
    void save(File file)throws SQLException;
}
