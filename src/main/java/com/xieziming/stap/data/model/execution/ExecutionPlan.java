package com.xieziming.stap.data.model.execution;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution_plan")
public class ExecutionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length=50)
    private String name;

    @Column(length=500)
    private String description;

    @Column(length=50)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "executionPlan")
    private List<ExecutionPlanMeta> executionPlanMetas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "executionPlan")
    private List<Execution> executions;
}
