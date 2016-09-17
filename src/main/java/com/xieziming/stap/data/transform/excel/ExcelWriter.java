package com.xieziming.stap.data.transform.excel;

import com.xieziming.stap.data.model.testcase.TestCase;
import com.xieziming.stap.data.model.testcase.TestData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 9/17/16.
 */
@Component
public class ExcelWriter {
    private XSSFWorkbook workbook;

    public void createWorkbook(){
        workbook = new XSSFWorkbook();
    }

    public void createSheet(String sheetName, List<TestCase> testCases) throws Exception {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        List<String> headerColumnNames = collectDistinctColumnNames(testCases);
        XSSFRow headerRow = createHeaderRow(sheet, headerColumnNames);
        for (TestCase testCase : testCases){
            List<TestData> testDatas = testCase.getTestDatas();
            if(testDatas != null) {
                List<TestDataCell> testDataCells = new ArrayList<>();
                testDataCells.addAll(getTestCaseCells(testCase));
                for(TestData testData : testDatas) {
                    TestDataCell testDataCell = new TestDataCell();
                    testDataCell.setHeader(testData.getTestDataDefinition().getField());
                    testDataCell.setValue(testData.getTestDataDefinition().getValue());
                    testDataCell.setComment(testData.getTestDataDefinition().getRemark());
                    testDataCell.setCellStyle(getBasicCellStyle());
                    testDataCells.add(testDataCell);
                }
                appendRow(sheet, testDataCells, headerRow);
            }
        }
    }

    private XSSFComment createComment(XSSFCell cell, String commentText){
        XSSFComment comment = cell.getSheet().createComment();
        comment.setRow(cell.getRowIndex());
        comment.setColumn((short) cell.getColumnIndex());
        comment.setString(commentText);
        return comment;
    }

    private XSSFRow createHeaderRow(XSSFSheet sheet,  List<String> columnNames){
        XSSFRow row = sheet.createRow(sheet.getLastRowNum());
        for(int i=0; i<columnNames.size(); i++){
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(getTestCaseHeaderCellStyle());
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(columnNames.get(i));
        }
        return row;
    }

    private XSSFRow appendRow(XSSFSheet sheet, List<TestDataCell> testDataCells, XSSFRow headerRow) throws Exception {
        XSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
        for(TestDataCell testDataCell : testDataCells) {
            XSSFCell cell = row.createCell(getColumnNo(headerRow, testDataCell));
            cell.setCellStyle(testDataCell.getCellStyle());
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(testDataCell.getValue());
            cell.setCellComment(createComment(cell, testDataCell.getComment()));
        }
        return row;
    }

    public void saveExcel(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.exists()) file.delete();
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }

    private CellStyle getBasicCellStyle(){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    private CellStyle getTestCaseHeaderCellStyle(){
        CellStyle cellStyle = getBasicCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(Color.CYAN).getIndexed());
        cellStyle.setFillBackgroundColor(new XSSFColor(Color.CYAN).getIndexed());
        return cellStyle;
    }

    private List<String> collectDistinctColumnNames(List<TestCase> testCases){
        List<String> columns = new ArrayList<>();
        columns.add("Id");
        columns.add("TestCase");
        columns.add("Description");
        columns.add("Status");
        columns.add("CreatedTime");
        columns.add("LastUpdate");
        for(TestCase testCase : testCases){
            List<TestData> testDatas = testCase.getTestDatas();
            if(testDatas != null){
                for(TestData testData : testDatas){
                    String field = testData.getTestDataDefinition().getField();
                    if(!columns.contains(field)) columns.add(field);
                }
            }
        }

        return columns;
    }

    private int getColumnNo(XSSFRow headerRow, TestDataCell testDataCell) throws Exception {
        for(int i=headerRow.getFirstCellNum(); i<headerRow.getLastCellNum(); i++){
            if(testDataCell.getHeader().equals(headerRow.getCell(i).getStringCellValue())){
                return i;
            }
        }

        throw new Exception("cannot locate column no for cell "+ testDataCell);
    }

    private List<TestDataCell> getTestCaseCells(TestCase testCase){
        List<TestDataCell> testDataCells = new ArrayList<>();

        TestDataCell testDataCell = new TestDataCell();
        testDataCell.setHeader("Id");
        testDataCell.setValue(String.valueOf(testCase.getId()));
        testDataCell.setComment(null);
        testDataCell.setCellStyle(getBasicCellStyle());
        testDataCells.add(testDataCell);

        testDataCell = new TestDataCell();
        testDataCell.setHeader("TestCase");
        testDataCell.setValue(testCase.getName());
        testDataCell.setComment(null);
        testDataCell.setCellStyle(getBasicCellStyle());
        testDataCells.add(testDataCell);

        testDataCell = new TestDataCell();
        testDataCell.setHeader("Description");
        testDataCell.setValue(testCase.getDescription());
        testDataCell.setComment(null);
        testDataCell.setCellStyle(getBasicCellStyle());
        testDataCells.add(testDataCell);

        testDataCell = new TestDataCell();
        testDataCell.setHeader("Status");
        testDataCell.setValue(testCase.getStatus());
        testDataCell.setComment(null);
        testDataCell.setCellStyle(getBasicCellStyle());
        testDataCells.add(testDataCell);

        testDataCell = new TestDataCell();
        testDataCell.setHeader("CreatedTime");
        testDataCell.setValue(new SimpleDateFormat("yyyy-MM-dd").format(testCase.getCreatedTime()));
        testDataCell.setComment(null);
        testDataCell.setCellStyle(getBasicCellStyle());
        testDataCells.add(testDataCell);

        testDataCell = new TestDataCell();
        testDataCell.setHeader("LastUpdate");
        testDataCell.setValue(new SimpleDateFormat("yyyy-MM-dd").format(testCase.getLastUpdate()));
        testDataCell.setComment(null);
        testDataCell.setCellStyle(getBasicCellStyle());
        testDataCells.add(testDataCell);

        return testDataCells;
    }
}
