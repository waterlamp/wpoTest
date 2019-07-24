package com.service;


import java.sql.SQLException;

public interface FileTypeService {
    String getFileTypes() throws SQLException;
    String getSecName(int fid) throws SQLException;
    String getTopName() throws SQLException;
}
