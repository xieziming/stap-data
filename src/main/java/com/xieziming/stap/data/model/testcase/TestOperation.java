/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
 */

package com.xieziming.stap.data.model.testcase;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@Entity
@Table(name="test_operation")
public class TestOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String operator;

    @Column(length=500)
    private String remark;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Override
    public String toString() {
        return "TestOperation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", remark='" + remark + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestOperation.class.isAssignableFrom(o.getClass())){
            TestOperation to = (TestOperation) o;
            equals = (new EqualsBuilder()
                    .append(name, to.name)
                    .append(operator, to.operator)
                    .append(remark, to.remark).isEquals());
        }
        return equals;
    }
}
