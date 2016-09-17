/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
 */

package com.xieziming.stap.data.model.testcase;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@Entity
@Table(name="test_case_relation")
public class TestCaseRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private TestCase testCase;

    @ManyToOne(cascade = CascadeType.ALL)
    private TestCase relatedTestCase;

    @Column(length = 50, nullable = false)
    private String relation;

    @Column(length=500)
    private String remark;

    @Override
    public String toString() {
        return "TestCaseRelation{" +
                "id=" + id +
                ", testCase=" + testCase.getName() +
                ", relatedTestCase=" + relatedTestCase.getName() +
                ", relation='" + relation + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestCaseRelation.class.isAssignableFrom(o.getClass())){
            TestCaseRelation tr = (TestCaseRelation) o;
            equals = (new EqualsBuilder()
                    .append(testCase.getName(), tr.getTestCase().getName())
                    .append(relatedTestCase.getName(), tr.relatedTestCase.getName())
                    .append(relation, tr.relation)
                    .append(remark, tr.remark).isEquals());
        }
        return equals;
    }
}
