package com.service.impl;

import com.bean.File;
import com.bean.FileType;
import com.bean.PageModel;
import com.bean.Project;
import com.dao.FileDao;
import com.dao.FileTypeDao;
import com.dao.ProjectDao;
import com.dao.PublicDao;
import com.dao.impl.FileDaoImp;
import com.dao.impl.FileTypeDaoImp;
import com.service.FileService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;

@Transactional
@Component("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    @Qualifier("publicDao")
    private PublicDao publicDao;
    @Autowired
    @Qualifier("projectDao")
    private ProjectDao projectDao;
    @Autowired
    @Qualifier("fileDao")
    private FileDao fileDao;
    @Autowired
    @Qualifier("fileTypeDao")
    private FileTypeDao fileTypeDao;

    public void setPublicDao(PublicDao publicDao) {
        this.publicDao = publicDao;
    }
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }
    public void setFileTypeDao(FileTypeDao fileTypeDao) {
        this.fileTypeDao = fileTypeDao;
    }


    @Override
    public PageModel find(int page, DetachedCriteria dc, String url) throws SQLException {

        int totalRecords=(int)publicDao.resultCount(dc);
        ProjectionList pList= Projections.projectionList();
        String alias="file";
        pList.add(Projections.property(alias+".fileId").as("fileId"));
        pList.add(Projections.property(alias+".fileRecordNum").as("fileRecordNum"));
        pList.add(Projections.property(alias+".fileBoxNum").as("fileBoxNum"));
        pList.add(Projections.property(alias+".fileNum").as("fileNum"));
        pList.add(Projections.property(alias+".fileCompany").as("fileCompany"));
        pList.add(Projections.property(alias+".fileName").as("fileName"));
        pList.add(Projections.property(alias+".fileDate").as("fileDate"));
        pList.add(Projections.property(alias+".fileCount").as("fileCount"));
        dc.addOrder(Order.asc(alias+".fileRecordNum"));
        dc.setProjection(pList).setResultTransformer(Transformers.aliasToBean(File.class));
        //dc.setProjection(null).setResultTransformer(DetachedCriteria.ROOT_ENTITY);

        List<File> list=publicDao.query(page,pageSize,dc);
        System.out.println(list.size());
        PageModel pageModel=new PageModel(page,totalRecords,pageSize);
        pageModel.setRecords(list);
        pageModel.setUrl(url);
        return pageModel;
    }

    @Override
    public void save(File file) throws SQLException {

        publicDao.saveOrUpdate(file);
        updateProjectCount(file.getProjectId());
    }

    @Override
    public File getFile(int fileId) throws SQLException {

        return fileDao.getFile(fileId);
    }

    @Override
    public void delete(File file) throws SQLException {
        file=fileDao.getFile(file.getFileId());
        int projectId=file.getProjectId();
        publicDao.delete(file);
        updateProjectCount(projectId);
    }

    @Override
    public void batchSave(int projectId, List<File> list) throws SQLException {

        int maxNum=getMaxNum(projectId);
        System.out.println(list.toString());
        Project project=projectDao.getProject(projectId);
        String recordNum="XM02-GC"+project.getProjectType()+"-"+project.getProjectMis()+"-"
                +project.getProjectStorageLife()+"-";
        for(File file:list){
            maxNum++;
            file.setProjectId(projectId);
            file.setFileRecordNum(recordNum+String.format("%04d",maxNum));
            fileDao.save(file);
        }

        updateProjectCount(projectId);
    }
    private int getBoxCount(int projectId) throws SQLException{
        List<String> list=fileDao.getBoxNums(projectId);
        int max=0;
        int num;
        String boxNum;
        for (String str:list){
            if(str.indexOf("-")>0||str.indexOf(",")>0){
                boxNum=str.lastIndexOf("-")>str.lastIndexOf(",")?
                        str.substring(str.lastIndexOf("-")+1):
                        str.substring(str.lastIndexOf(",")+1);
            }else {
                boxNum=str;
            }
            num=Integer.parseInt(boxNum);
            if(num>max)max=num;
        }
        return  max;
    }
    private void updateProjectCount(int projectId) throws SQLException{

        int boxCount=getBoxCount(projectId);
        int fileCount=fileDao.fileCount(projectId);
        Project project=projectDao.getProject(projectId);
        project.setProjectBoxsCount(boxCount);
        project.setProjectFilesCount(fileCount);
        projectDao.update(project);
    }

    @Override
    public Map<String,List<String[]>> perTypeCount(int projectId) throws SQLException {
        List<FileType> typeList=fileTypeDao.getFileTypes();

        Map<Integer, Integer> map=fileDao.perTypeCount(projectId);
        Map<Integer,List<String[]>> intMap=new LinkedHashMap<>();
        Map<Integer,String> nameMap=new HashMap<>();
        for (FileType fileType:typeList){
            if (fileType.getFiletypeFid()==0){
                List<String[]> list=new ArrayList<>();
                intMap.put(fileType.getFiletypeId(),list);
                nameMap.put(fileType.getFiletypeId(),fileType.getFiletypeName());
            }
        }
        for (FileType fileType:typeList){
            if(fileType.getFiletypeFid()>0){
                String[] strings=new String[2];
                strings[0]=fileType.getFiletypeName();
                if (map.containsKey(fileType.getFiletypeId())){
                    strings[1]=map.get(fileType.getFiletypeId()).toString();
                }else {
                    strings[1]="0";
                }
                intMap.get(fileType.getFiletypeFid()).add(strings);
            }
        }
        Map<String,List<String[]>> typeMap=new LinkedHashMap<>();
        for (Integer key:intMap.keySet()){
            typeMap.put(nameMap.get(key),intMap.get(key));
        }
        return typeMap;
    }

    @Override
    public List<File> findAll(int projectId) throws SQLException {

        return fileDao.findAll(projectId);
    }

    @Override
    public String getNewRecorNum(int projectId) throws SQLException {
        int maxNum=getMaxNum(projectId)+1;

        Project project=projectDao.getProject(projectId);
        return "XM02-GC"+project.getProjectType()+"-"+project.getProjectMis()+"-"+
                project.getProjectStorageLife()+"-"+String.format("%04d",maxNum);

    }
    private int getMaxNum(int projectId) throws SQLException{

        List<String> recordList=fileDao.getRecordNums(projectId);
        int maxNum=0;
        for (String recordNum:recordList){
            int num=Integer.parseInt(recordNum.substring(recordNum.lastIndexOf("-")+1));
            if(num>maxNum)maxNum=num;
        }

        return maxNum;
    }

    @Test
    public void test() throws SQLException{
        int projectId=8;
        Map<String,List<String[]>> typeMap=perTypeCount(projectId);
        for (String key:typeMap.keySet()){
            System.out.println(key);
            List<String[]> list=typeMap.get(key);
            for(String[] strings:list){
                System.out.println(strings[0]+":"+strings[1]);
            }
        }
    }
}
