package com.dao.impl;

import com.bean.FileType;
import com.dao.FileTypeDao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("fileTypeDao")
public class FileTypeDaoImp extends HibernateDaoSupport implements FileTypeDao {
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<FileType> getFileTypes(int fid) throws SQLException {
        String hql="FROM FileType where filetypeFid=:fid";

        Query query=this.currentSession().createQuery(hql);
        query.setParameter("fid",fid);
        List<FileType> list=query.list();
        return list;
    }

    @Override
    public List<FileType> getFileTypes() throws SQLException {
        String hql="FROM FileType order by filetypeId";

        Query query=this.currentSession().createQuery(hql);

        List<FileType> list=query.list();
        return list;
    }

    @Override
    public FileType getFileType(int id) throws SQLException {

        return this.currentSession().get(FileType.class,id);
    }
}
