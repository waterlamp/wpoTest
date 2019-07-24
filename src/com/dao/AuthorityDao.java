package com.dao;

import com.bean.Authority;

import java.sql.SQLException;

public interface AuthorityDao {
    Authority getAuthrity(int id) throws SQLException;
}
