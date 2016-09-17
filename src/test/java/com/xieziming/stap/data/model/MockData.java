/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
 */

package com.xieziming.stap.data.model;

import com.xieziming.stap.data.model.execution.*;
import com.xieziming.stap.data.model.testcase.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Suny on 9/10/16.
 */
public class MockData {
    public static TestCase createFullFilledTestCase(){
        TestCase testCase = new TestCase();
        testCase.setName("fake test case "+Calendar.getInstance().getTimeInMillis());
        testCase.setDescription("test description");
        testCase.setStatus("Ready");
        testCase.setCreatedTime(Calendar.getInstance().getTime());
        testCase.setLastUpdate(Calendar.getInstance().getTime());

        TestCaseMeta testCaseMeta = new TestCaseMeta();
        testCaseMeta.setMetaType("General");
        testCaseMeta.setMetaKey("meta_1");
        testCaseMeta.setMetaValue("meta value");
        testCaseMeta.setTestCase(testCase);
        testCaseMeta.setLastUpdate(Calendar.getInstance().getTime());
        List<TestCaseMeta> testCaseMetas = new ArrayList<>();
        testCaseMetas.add(testCaseMeta);
        testCase.setTestCaseMetas(testCaseMetas);

        TestDataDefinition testDataDefinition = new TestDataDefinition();
        testDataDefinition.setType("Local");
        testDataDefinition.setField("User");
        testDataDefinition.setValue("Suny");
        testDataDefinition.setRemark("test remark");
        testDataDefinition.setLastUpdate(Calendar.getInstance().getTime());
        TestData testData = new TestData();
        testData.setTestCase(testCase);
        testData.setTestDataDefinition(testDataDefinition);
        List<TestData> testDatas = new ArrayList<>();
        testDatas.add(testData);
        testCase.setTestDatas(testDatas);

        TestStep testStep = new TestStep();
        TestOperation testOperation = new TestOperation();
        testOperation.setName("operation 1");
        testOperation.setRemark("operation remark");
        testOperation.setOperator("operator 1");
        testOperation.setLastUpdate(Calendar.getInstance().getTime());
        testStep.setTestOperation(testOperation);
        testStep.setStepOrder(1);
        testStep.setTestCase(testCase);
        testStep.setParameter("operation parameter");
        List<TestStep> testSteps = new ArrayList<>();
        testSteps.add(testStep);
        testCase.setTestSteps(testSteps);

        TestCaseRelation testCaseRelation = new TestCaseRelation();
        testCaseRelation.setTestCase(testCase);
        testCaseRelation.setRelatedTestCase(testCase);
        testCaseRelation.setRelation("the same case");
        testCaseRelation.setRemark("test purpose");
        List<TestCaseRelation> testCaseRelations = new ArrayList<>();
        testCaseRelations.add(testCaseRelation);
        testCase.setTestCaseRelations(testCaseRelations);

        return testCase;
    }

    public static Execution createFullFilledExecution(){
        Execution execution = new Execution();

        //fill context
        ExecutionContext executionContext = new ExecutionContext();
        executionContext.setName("windowsOnly");
        executionContext.setContent("{\"os\":\"windows\"}");
        executionContext.setLastUpdate(Calendar.getInstance().getTime());
        execution.setExecutionContext(executionContext);

        //fill log
        ExecutionLog executionLog = new ExecutionLog();
        executionLog.setLevel("INFO");
        executionLog.setContent("pc *** is running this test case");
        executionLog.setTime(Calendar.getInstance().getTime());
        List<ExecutionLog> executionLogs = new ArrayList<>();
        executionLogs.add(executionLog);
        execution.setExecutionLogs(executionLogs);

        //fill output text
        ExecutionOutputText executionOutputText = new ExecutionOutputText();
        executionOutputText.setType("General");
        executionOutputText.setField("Currency");
        executionOutputText.setValue("USD");
        List<ExecutionOutputText> executionOutputTexts = new ArrayList<>();
        executionOutputTexts.add(executionOutputText);
        execution.setExecutionOutputTexts(executionOutputTexts);

        //fill output file
        ExecutionOutputFile executionOutputFile = new ExecutionOutputFile();
        executionOutputFile.setName("screen.jpg");
        executionOutputFile.setType("image");
        executionOutputFile.setContent("rawIamge".getBytes());
        List<ExecutionOutputFile> executionOutputFiless = new ArrayList<>();
        executionOutputFiless.add(executionOutputFile);
        execution.setExecutionOutputFiles(executionOutputFiless);

        execution.setStartTime(Calendar.getInstance().getTime());
        execution.setEndTime(Calendar.getInstance().getTime());
        execution.setStatus("Completed");
        execution.setResult("Pass");


        //fill execution plan
        ExecutionPlan executionPlan = new ExecutionPlan();

        ExecutionPlanMeta executionPlanMeta = new ExecutionPlanMeta();
        executionPlanMeta.setMetaKey("site");
        executionPlanMeta.setMetaValue("China");
        List<ExecutionPlanMeta> executionPlanMetas = new ArrayList<>();
        executionPlanMetas.add(executionPlanMeta);
        executionPlan.setExecutionPlanMetas(executionPlanMetas);

        executionPlan.setName("Stap test plan");
        executionPlan.setDescription("stap test plan description");
        executionPlan.setStatus("Open");

        execution.setExecutionPlan(executionPlan);

        //fill test case

        execution.setTestCase(MockData.createFullFilledTestCase());

        return execution;
    }
}
