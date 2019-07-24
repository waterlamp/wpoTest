package com.service;

import com.bean.PageModel;
import com.bean.Project;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;

import java.sql.SQLException;

public interface ProjectService {
    int pageSize=10;
    PageModel find(int page, DetachedCriteria dc, String url) throws SQLException;
    void save(Project project) throws SQLException;
    void update(Project project) throws SQLException;
    void turnProject(int projectId) throws SQLException;

    Project getProject(int projectId) throws SQLException;
    Project getProject(String mis) throws SQLException;
    void delete(int projectId) throws SQLException;
}
