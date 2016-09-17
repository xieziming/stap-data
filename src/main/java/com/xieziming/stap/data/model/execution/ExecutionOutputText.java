package com.xieziming.stap.data.model.execution;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution_output_text")
public class ExecutionOutputText {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Execution execution;

    @Column(length=50)
    private String type;

    @Column(length=50)
    private String field;

    @Column(length=500)
    private String value;

    @Column(length=500)
    private String remark;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
