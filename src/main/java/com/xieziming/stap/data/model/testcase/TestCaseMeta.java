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
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="test_case_meta")
public class TestCaseMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    private TestCase testCase;

    @Column(length = 50, nullable = false)
    private String metaType;

    @Column(length = 50, nullable = false)
    private String metaKey;

    @Column(length=500)
    private String metaValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Override
    public String toString() {
        return "TestCaseMeta{" +
                "id=" + id +
                ", testCase=" + testCase.getName() +
                ", metaType='" + metaType + '\'' +
                ", metaKey='" + metaKey + '\'' +
                ", metaValue='" + metaValue + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestCaseMeta.class.isAssignableFrom(o.getClass())){
            TestCaseMeta tcm = (TestCaseMeta) o;
            equals = (new EqualsBuilder()
                    .append(testCase.getName(), tcm.getTestCase().getName())
                    .append(metaType, tcm.metaType)
                    .append(metaKey, tcm.metaKey)
                    .append(metaValue, tcm.metaValue).isEquals());
        }
        return equals;
    }
}
