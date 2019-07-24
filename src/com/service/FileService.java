package com.service;

import com.bean.File;
import com.bean.FileType;
import com.bean.PageModel;
import org.hibernate.criterion.DetachedCriteria;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FileService {
    int pageSize=10;
    PageModel find(int page, DetachedCriteria dc, String url) throws SQLException;
    void save(File file) throws SQLException;
    File getFile(int fileId) throws SQLException;
    void delete(File file) throws SQLException;
    void batchSave(int projectId, List<File> list) throws SQLException;
    Map<String,List<String[]>> perTypeCount(int projectId) throws SQLException;
    List<File> findAll(int projectId) throws SQLException;
    String getNewRecorNum(int projectId) throws SQLException;
}
