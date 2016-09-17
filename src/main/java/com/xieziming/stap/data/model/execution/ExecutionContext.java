package com.xieziming.stap.data.model.execution;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution_context")
public class ExecutionContext {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length=50)
    private String name;

    @Column(length=1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
