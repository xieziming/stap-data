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
@Table(name="test_step")
public class TestStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    private TestCase testCase;

    @Column(length=10)
    private Integer stepOrder;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TestOperation testOperation;

    @Column(length=500)
    private String parameter;

    @Override
    public String toString() {
        return "TestStep{" +
                "id=" + id +
                ", testCase=" + testCase.getName() +
                ", stepOrder=" + stepOrder +
                ", testOperation=" + testOperation +
                ", parameter='" + parameter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestStep.class.isAssignableFrom(o.getClass())){
            TestStep ts = (TestStep) o;
            equals = (new EqualsBuilder()
                    .append(testCase.getName(), ts.getTestCase().getName())
                    .append(stepOrder, ts.stepOrder)
                    .append(testOperation, ts.testOperation)
                    .append(parameter, ts.parameter).isEquals());
        }
        return equals;
    }
}
