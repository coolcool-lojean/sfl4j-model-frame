package com.smart.fast.learning.model.frame.model;

import com.smart.fast.learning.model.frame.domain.FramePointFor2D;

import java.util.List;

public class LayerExecuteResult {

    private List<FramePointFor2D> allPointList;

    public LayerExecuteResult(List<FramePointFor2D> allPointList) {
        this.allPointList = allPointList;
    }

    public List<FramePointFor2D> getAllPointList() {
        return allPointList;
    }

    public void setAllPointList(List<FramePointFor2D> allPointList) {
        this.allPointList = allPointList;
    }
}
