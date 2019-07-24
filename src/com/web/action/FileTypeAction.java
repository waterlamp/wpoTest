package com.web.action;

import com.bean.FileType;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.FileTypeService;
import com.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
@Component("fileTypeAction")
@Scope("prototype")
public class FileTypeAction extends ActionSupport implements ModelDriven<FileType> {
    private FileType fileType=new FileType();
    private String result="";
    @Autowired
    @Qualifier("fileTypeService")
    private FileTypeService service;

    public void setFileTypeService(FileTypeService service) {
        this.service = service;
    }
    @Override
    public FileType getModel() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String totalType(){
        Jedis jedis= JedisUtils.getJedis();
        String json=null;
        if (jedis.exists("fileTypes")){
            json=jedis.get("fileTypes");
        }else {
            try {

                json=service.getFileTypes();
                jedis.append("fileTypes",json);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JedisUtils.closeJedis(jedis);
        this.setResult(json);
        return SUCCESS;
    }
    public String getTopName(){
        System.out.println("topName");
        Jedis jedis= JedisUtils.getJedis();
        String json=null;
        if (jedis.exists("topName")){
            json=jedis.get("topName");
        }else {
            try {
                json=service.getTopName();
                jedis.append("topName",json);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JedisUtils.closeJedis(jedis);
        this.setResult(json);
        return SUCCESS;
    }
    public String getSecName(){
        int fid=fileType.getFiletypeFid();
        String key="SecName"+fid;
        Jedis jedis=JedisUtils.getJedis();
        String json="";
        if(jedis.exists(key)){
            System.out.println("第二次");
            json=jedis.get(key);
        }else {
            System.out.println("第一次");
            try {
                json=service.getSecName(fid);
                jedis.append(key,json);
            } catch (SQLException e) {
                throw new RuntimeException("文件类型二级目录查询错误");
            }
        }
        JedisUtils.closeJedis(jedis);
        this.setResult(json);
        return SUCCESS;
    }
}
