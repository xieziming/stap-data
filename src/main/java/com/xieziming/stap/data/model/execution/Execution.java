package com.xieziming.stap.data.model.execution;

import com.xieziming.stap.data.model.testcase.TestCase;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution")
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ExecutionPlan executionPlan;

    @OneToOne(cascade = CascadeType.ALL)
    private TestCase testCase;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ExecutionContext executionContext;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(length=50)
    private String status;

    @Column(length=50)
    private String result;

    @Column(length=500)
    private String remark;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "execution")
    private List<ExecutionLog> executionLogs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "execution")
    private List<ExecutionOutputText> executionOutputTexts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "execution")
    private List<ExecutionOutputFile> executionOutputFiles;
}
