package com.smart.fast.learning.model.frame.domain;

import java.util.ArrayList;
import java.util.List;

public class ClassifyForArray2D {

    private List<ArrayList<PointFor2D>> classifyArray;

    public ClassifyForArray2D(List<ArrayList<PointFor2D>> classifyArray) {
        this.classifyArray = classifyArray;
    }

    public List<ArrayList<PointFor2D>> getClassifyArray() {
        return classifyArray;
    }

    public void setClassifyArray(List<ArrayList<PointFor2D>> classifyArray) {
        this.classifyArray = classifyArray;
    }
}
