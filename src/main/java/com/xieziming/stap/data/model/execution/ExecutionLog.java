package com.xieziming.stap.data.model.execution;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution_log")
public class ExecutionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Execution execution;

    @Column(length=50)
    private String level;

    @Column(length=1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
}
