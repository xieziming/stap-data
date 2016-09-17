package com.xieziming.stap.data.model.execution;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution_plan_meta")
public class ExecutionPlanMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ExecutionPlan executionPlan;

    @Column(length=50)
    private String metaType;

    @Column(length=50)
    private String metaKey;

    @Column(length=500)
    private String metaValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
