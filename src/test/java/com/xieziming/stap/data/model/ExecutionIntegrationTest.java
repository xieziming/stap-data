package com.xieziming.stap.data.model;

import com.xieziming.stap.data.model.execution.Execution;
import com.xieziming.stap.data.repository.ExecutionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Suny on 9/17/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ExecutionIntegrationTest {
    @Autowired
    private ExecutionRepository executionRepository;

    @Test
    public void execute(){
        Execution execution = MockData.createFullFilledExecution();
        execution = executionRepository.save(execution);

        execution.setStatus("INPROGRESS");
        executionRepository.save(execution);

        assertThat(executionRepository.findOne(execution.getId()).getStatus(), is("INPROGRESS"));
    }

}
