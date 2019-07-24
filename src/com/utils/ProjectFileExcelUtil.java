package com.utils;

import com.bean.File;
import com.bean.Project;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ProjectFileExcelUtil {
    private static HSSFWorkbook wb;

    /**
     *
     * @param response 请求
     * @param project 项目
     * @param list 文件列表
     * @param map 各类文件的统计值
     * @return 返回一个HSSFWorkboo
     */
    public static HSSFWorkbook fileExport(HttpServletResponse response, Project project,
                                          List<File> list, Map<String,List<String[]>> map) throws Exception{
        //设置请求头
        response.setContentType("application/application/vnd.ms-excel");
        response.setHeader("Content-disposition",
                "attachment;filename=" + URLEncoder.encode(project.getProjectMis() + ".xls", "UTF-8"));
        // 创建一个Workbook，对应一个Excel文件
        wb = new HSSFWorkbook();
        setValue("工程档案移交登记表",project,list,map);
        setValue("归档文件目录",project,list,map);
        // 设置单元格样式


        OutputStream outputStream = response.getOutputStream();// 打开流
        wb.write(outputStream);// HSSFWorkbook写入流
        wb.close();// HSSFWorkbook关闭
        outputStream.flush();// 刷新流
        outputStream.close();// 关闭流
        return wb;
    }
    private static void setValue(String sheetName,Project project,List<File> list, Map<String,List<String[]>> map){
        int sheetNum;
        String[] titles;
        int[] colWidths;
        int titleRow;
        if(sheetName.equals("工程档案移交登记表")){
            sheetNum=0;
            titles=new String[]{"序号","文号","责任部门","文件名称","备注"};

            colWidths=new int[]{2700,2700,6000,(int)((40+0.72)*256),2700};
            titleRow=4;
        }else {
            sheetNum=1;
            titles=new String[]{"档案号","盒号","文件编号","责任者","文件题名","文件日期","备注"};
            colWidths=new int[]{7300,(int)((9+0.72)*256),4000,4500,6000,(int)((18+0.72)*256),(int)((9+0.72)*256)};
            titleRow=2;
        }

        //创建一个Worksheet
        HSSFSheet sheet=wb.createSheet(sheetName);
        for (int i=0;i<colWidths.length;i++){
            sheet.setColumnWidth(i,colWidths[i]);

        }
        // 设置单元格样式
        HSSFCellStyle sheetTitleStyle=getCellStyle(true,"center",(short)16,false);
        HSSFRow row = sheet.createRow( 0);// 行数从0开始
        HSSFCell cell = row.createCell(0);// cell列 从0开始
        cell.setCellValue(sheetName);
        cell.setCellStyle(sheetTitleStyle);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,titles.length-1));
        if(sheetNum==0){
            sheet1AddProjectMsg(sheet,project);
        }else {
            sheet2AddProjectMsg(sheet,project);
        }
        setTitleValue(titles,sheet,titleRow);
        if(sheetNum==0){
            setSheet1Value(sheet,map,project);

        }else {
            setSheet2Value(sheet,list,project);

            HSSFPrintSetup ps = sheet.getPrintSetup();
            ps.setLandscape(true); // 打印方向，true：横向，false：纵向(默认
        }
    }
    private static HSSFCellStyle getCellStyle(boolean isBold,String alignment,short fontSize,boolean hasBolder){
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font=wb.createFont();
        font.setBold(isBold);
        font.setFontName("宋体");
        font.setFontHeightInPoints(fontSize);
        style.setFont(font);

        switch (alignment){
            case "left":style.setAlignment(HorizontalAlignment.LEFT);break;
            case "right":style.setAlignment(HorizontalAlignment.RIGHT);break;
            default:style.setAlignment(HorizontalAlignment.CENTER);
        }

        style.setVerticalAlignment(VerticalAlignment.CENTER);
        if(hasBolder){
            style.setBorderTop(BorderStyle.THIN);// 上边框 细边线
            style.setBorderBottom(BorderStyle.THIN);// 下边框 细边线
            style.setBorderLeft(BorderStyle.THIN);// 左边框 细边线
            style.setBorderRight(BorderStyle.THIN);// 右边框 细边线
        }
        return style;
    }

    //填写工程档案移交登记表工程信息
    private static void sheet1AddProjectMsg(HSSFSheet sheet,Project project){
        HSSFCellStyle style=getCellStyle(true,"left",(short)11,false);
        HSSFRow row=sheet.createRow(1);
        HSSFCell cell=row.createCell(0);
        cell.setCellValue("工程名称："+project.getProjectName());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
        row=sheet.createRow(2);
        cell=row.createCell(0);
        cell.setCellValue("工程编号："+project.getProjectMis());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(2,2,0,4));
        row=sheet.createRow(3);
        cell=row.createCell(0);
        cell.setCellValue("项目负责人:"+project.getUser().getUserName());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(3,3,0,4));
    }
    //填写工程档案移交登记表工程信息
    private static void sheet2AddProjectMsg(HSSFSheet sheet,Project project){
        HSSFCellStyle style=getCellStyle(true,"left",(short)11,false);
        HSSFRow row=sheet.createRow(1);
        HSSFCell cell=row.createCell(0);
        cell.setCellValue("工程名称："+project.getProjectName());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,2));
        cell=row.createCell(3);
        cell.setCellValue("工程编号："+project.getProjectMis());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(1,1,3,4));
        cell=row.createCell(5);
        cell.setCellValue("项目负责人:"+project.getUser().getUserName());
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(1,1,5,6));
    }
    private static void setTitleValue(String[] titles,HSSFSheet sheet,int titleRow){
        HSSFRow row=sheet.createRow(titleRow);
        HSSFCellStyle titleStyle=getCellStyle(true,"center",(short)11,true);
        for (int i=0;i<titles.length;i++){
            HSSFCell cell=row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(titleStyle);
        }

    }
    private static void setSheet1Value(HSSFSheet sheet,Map<String,List<String[]>> map,Project project){
        HSSFCellStyle titleStyle=getCellStyle(true,"center",(short)11,true);
        HSSFCellStyle cellStyle=getCellStyle(false,"center",(short)11,true);

        int index=1;
        int currentRow=5;
        for (String key:map.keySet()){
            HSSFRow row1=sheet.createRow(currentRow);
            cellSetValue(key,titleStyle,0,row1);
            cellSetValue("",titleStyle,4,row1);
            sheet.addMergedRegion(new CellRangeAddress(currentRow,currentRow,0,4));
            List<String[]> list=map.get(key);
            currentRow++;
            for(String[] strings:list){
                HSSFRow row2=sheet.createRow(currentRow);
                cellSetValue(String.valueOf(index),cellStyle,0,row2);
                cellSetValue("",cellStyle,1,row2);
                cellSetValue("",cellStyle,2,row2);
                cellSetValue(strings[0],cellStyle,3,row2);
                cellSetValue(strings[1],cellStyle,4,row2);
                currentRow++;
                index++;
            }
        }
        HSSFRow row=sheet.createRow(currentRow);
        cellSetValue("合计",titleStyle,0,row);
        sheet.addMergedRegion(new CellRangeAddress(currentRow,currentRow,0,3));
        cellSetValue(project.getProjectFilesCount().toString(),titleStyle,4,row);
        currentRow++;

        String[][] foots=new String[][]{{"移交部门：","接收部门："},{"移交人签字：","接收人签字："},{"移交日期：","接收日期："}};
        HSSFCellStyle footStyle=getCellStyle(false,"left",(short)11,true);
        for (String[] foot : foots) {
            row = sheet.createRow(currentRow);
            //POI中的行高＝Excel的行高度*20
            //Excel的行高度=POI中的行高/20
            row.setHeight((short) 1360);
            cellSetValue(foot[0], footStyle, 0, row);
            cellSetValue("",footStyle,1,row);
            cellSetValue("",footStyle,2,row);
            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 2));
            cellSetValue(foot[1], footStyle, 3, row);
            cellSetValue("",footStyle,4,row);
            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 3, 4));
            currentRow++;
        }


    }
    private static void setSheet2Value(HSSFSheet sheet,List<File> list,Project project){
        HSSFCellStyle cellStyle=getCellStyle(false,"center",(short)11,true);
        cellStyle.setWrapText(true);
        int currentRow=3;
        for(File file:list){
            HSSFRow row=sheet.createRow(currentRow);
            cellSetValue(file.getFileRecordNum(),cellStyle,0,row);
            cellSetValue(file.getFileBoxNum(),cellStyle,1,row);
            cellSetValue(file.getFileNum(),cellStyle,2,row);
            cellSetValue(file.getFileCompany(),cellStyle,3,row);
            cellSetValue(file.getFileName(),cellStyle,4,row);
            cellSetValue(file.getFileDate().toString().substring(0,10),cellStyle,5,row);
            cellSetValue(file.getFileCount().toString(),cellStyle,6,row);
            currentRow++;
        }
        HSSFRow row=sheet.createRow(currentRow);
        cellSetValue("合计",cellStyle,0,row);
        for(int i=1;i<6;i++){
            cellSetValue("",cellStyle,i,row);
        }
        sheet.addMergedRegion(new CellRangeAddress(currentRow,currentRow,0,5));
        cellSetValue(project.getProjectFilesCount().toString(),cellStyle,6,row);
    }
    private static void cellSetValue(String value,HSSFCellStyle cellStyle,int col,HSSFRow row){
        HSSFCell cell=row.createCell(col);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }

}
