package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.bean.File;
import com.bean.FileType;
import com.dao.FileTypeDao;
import com.dao.impl.FileTypeDaoImp;
import com.service.FileTypeService;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component("fileTypeService")
public class FileTypeSeriveImp implements FileTypeService {

    @Autowired
    @Qualifier("fileTypeDao")
    private FileTypeDao dao;
    public void setDao(FileTypeDao dao) {
        this.dao = dao;
    }

    @Override
    public String getFileTypes() throws SQLException {
        List<FileType> list=dao.getFileTypes();
        return JSON.toJSONString(list);
    }

    @Override
    public String getSecName(int fid) throws SQLException {
        List<FileType> list=dao.getFileTypes(fid);
        return JSON.toJSONString(list);
    }

    @Override
    public String getTopName() throws SQLException {
        List<FileType> list=dao.getFileTypes(0);
        return JSON.toJSONString(list);
    }
}
