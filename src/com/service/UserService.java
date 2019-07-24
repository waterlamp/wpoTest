package com.service;

import com.bean.PageModel;
import com.bean.User;
import org.hibernate.criterion.DetachedCriteria;

import java.sql.SQLException;

public interface UserService {
    int pageSize=10;
    User getUser(String tel,String pwd) throws SQLException;
    PageModel find(int page,DetachedCriteria dc,String url) throws SQLException;
    User getUser(String id) throws SQLException;
    User getUserByName(String userName) throws SQLException;
    void save(User user) throws SQLException;
}
