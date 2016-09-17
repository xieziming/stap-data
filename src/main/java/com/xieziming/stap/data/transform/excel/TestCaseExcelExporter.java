package com.xieziming.stap.data.transform.excel;

import com.xieziming.stap.data.model.testcase.TestCase;
import com.xieziming.stap.data.model.testcase.TestCaseMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Suny on 9/17/16.
 */
@Component
public class TestCaseExcelExporter {
    public static final String EXCEL_META = "Excel";
    public static final String EXCEL_FILENAME_META = "FileName";
    public static final String EXCEL_SHEETNAME_META = "SheetName";

    @Autowired
    ExcelWriter excelWriter;

    public void export(Iterable<TestCase> testCases) throws Exception {
        Map<String, Map<String, List<TestCase>>> testCasesInFile = new HashMap<>();
        for(TestCase testCase : testCases){
            assignFileAndSheet(testCase, testCasesInFile);
        }

        Iterator<String> fileIter = testCasesInFile.keySet().iterator();
        while(fileIter.hasNext()){
            String fileName = fileIter.next();
            Map<String, List<TestCase>> testCaseInSheets = testCasesInFile.get(fileName);
            Iterator<String> sheetIter = testCaseInSheets.keySet().iterator();
            excelWriter.createWorkbook();
            while(sheetIter.hasNext()){
                String sheetName = sheetIter.next();
                List<TestCase> testCaseInSheet = testCaseInSheets.get(sheetName);
                excelWriter.createSheet(sheetName, testCaseInSheet);
            }
            excelWriter.saveExcel(fileName+".xlsx");
        }
    }

    private Map<String, String> getTestCaseExcelMetas(TestCase testCase){
        Map<String, String> testCaseExcelMetas = new HashMap<>();
        testCaseExcelMetas.put(EXCEL_FILENAME_META, "unknown");
        testCaseExcelMetas.put(EXCEL_SHEETNAME_META, "unknown");

        List<TestCaseMeta> testCaseMetas = testCase.getTestCaseMetas();
        if(testCaseMetas != null) {
            for (TestCaseMeta testCaseMeta : testCaseMetas) {
                if (EXCEL_META.equalsIgnoreCase(testCaseMeta.getMetaType())) {
                    String key = testCaseMeta.getMetaKey();
                    if (EXCEL_FILENAME_META.equalsIgnoreCase(key)) {
                        String fileName = testCaseMeta.getMetaValue();
                        testCaseExcelMetas.put(EXCEL_FILENAME_META, fileName);
                    } else if (EXCEL_SHEETNAME_META.equalsIgnoreCase(key)) {
                        String fileName = testCaseMeta.getMetaValue();
                        testCaseExcelMetas.put(EXCEL_SHEETNAME_META, fileName);
                    }
                }
            }
        }

        return testCaseExcelMetas;
    }

    private void assignFileAndSheet(TestCase testCase, Map<String, Map<String, List<TestCase>>> testCasesInFile){
        Map<String, String> testCaseExcelMetas = getTestCaseExcelMetas(testCase);
        if(testCaseExcelMetas != null) {
            String fileName = testCaseExcelMetas.get(EXCEL_FILENAME_META);
            if (!testCasesInFile.containsKey(fileName)) {
                testCasesInFile.put(fileName, new HashMap<String, List<TestCase>>());
            }

            Map<String, List<TestCase>> testCaseInSheet = testCasesInFile.get(fileName);

            String sheetName = testCaseExcelMetas.get(EXCEL_SHEETNAME_META);
            if (!testCaseInSheet.containsKey(sheetName)) {
                testCaseInSheet.put(sheetName, new ArrayList<TestCase>());
            }

            List<TestCase> testCases = testCaseInSheet.get(sheetName);
            testCases.add(testCase);
        }
    }
}
