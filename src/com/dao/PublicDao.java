package com.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.sql.SQLException;
import java.util.List;

public interface PublicDao<T> {
    List<T> query(int page,int pageSize,DetachedCriteria dc) throws SQLException;
    long resultCount(DetachedCriteria dc) throws SQLException;
    void save(Object obj) throws SQLException;
    void saveOrUpdate(Object obj) throws SQLException;
    void delete(Object obj) throws SQLException;
}
