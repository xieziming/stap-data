package com.xieziming.stap.data.model.execution;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="execution_output_file")
public class ExecutionOutputFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Execution execution;

    @Column(length=50)
    private String name;

    @Column(length=50)
    private String type;

    @Lob
    private byte[] content;

    @Column(length=500)
    private String remark;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
