package com.smart.fast.learning.model.domain;

import java.util.ArrayList;

public class IntegerArray2D {

    private ArrayList<ArrayList<Integer>> array2D;

    private Integer rowNum;

    private Integer lineNum;

    public IntegerArray2D(int rowNum, int lineNum) {
        this.rowNum = rowNum;
        this.lineNum = lineNum;
        array2D = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            array2D.add(new ArrayList<>(lineNum));
        }
    }

    public ArrayList<ArrayList<Integer>> getArray2D() {
        return array2D;
    }

    public void setArray2D(ArrayList<ArrayList<Integer>> array2D) {
        this.array2D = array2D;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
