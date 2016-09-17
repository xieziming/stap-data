package com.xieziming.stap.data.transform.excel;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by Suny on 9/17/16.
 */
@Data
public class TestDataCell {
    private String header;
    private String value;
    private String comment;
    private CellStyle cellStyle;

    @Override
    public String toString() {
        return "TestDataCell{" +
                "header='" + header + '\'' +
                ", value='" + value + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
