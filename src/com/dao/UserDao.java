package com.dao;

import com.bean.User;
import org.hibernate.criterion.DetachedCriteria;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
    void add(User user) throws SQLException;;
    List<User> query(int page, int pageSize, DetachedCriteria dc);
    User getUser(String id) throws SQLException;
    User getUser(String tel,String pwd) throws SQLException;
    User getUserByName(String name) throws SQLException;
}
