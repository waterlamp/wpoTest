package com.dao;

import com.bean.Project;
import org.hibernate.criterion.DetachedCriteria;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDao {
    Project getProject(int projectId) throws SQLException;
    Project getProject(String mis) throws SQLException;
    void update(Project project) throws SQLException;
//    void updateProject(Project project) throws SQLException;
//    void updateTurn(int projectId) throws SQLException;
//    void updateCount(int projectId,int boxCount,int fileCount) throws SQLException;
    void delete(Project project) throws SQLException;
    List<Project> query(int page, int pageSize, DetachedCriteria dc);
}
