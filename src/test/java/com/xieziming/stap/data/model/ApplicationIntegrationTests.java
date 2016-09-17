/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xieziming.stap.data.model;

import com.xieziming.stap.data.repository.ExecutionRepository;
import com.xieziming.stap.data.repository.TestCaseRepository;
import com.xieziming.stap.data.transform.excel.TestCaseExcelExporter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test to show the usage of repositories backed by different stores.
 * 
 * @author Oliver Gierke
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIntegrationTests {

	@Autowired
	TestCaseRepository testCaseRepository;

	@Autowired
	ExecutionRepository executionRepository;

	@Autowired
	TestCaseExcelExporter testCaseExcelExporter;

	@Test
	public void fillData() {
		//for(int i=0;i<100;i++){
			//testCaseRepository.save(MockData.createFullFilledTestCase());
		//}

		executionRepository.save(MockData.createFullFilledExecution());
//		Iterable<TestCase> testCases = testCaseRepository.findAll();
//		for(TestCase testCase : testCases){
//			System.out.println(testCase);
//		}
	}

	@Test
	@Transactional
	public void testExcelExporter(){
		try {
			testCaseExcelExporter.export(testCaseRepository.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
