package com.smart.fast.learning.model.frame.domain;

public class FramePointFor2D {

    private PointFor2D minPoint;

    private PointFor2D maxPoint;

    public FramePointFor2D(PointFor2D minPoint, PointFor2D maxPoint) {
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    public PointFor2D getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(PointFor2D minPoint) {
        this.minPoint = minPoint;
    }

    public PointFor2D getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(PointFor2D maxPoint) {
        this.maxPoint = maxPoint;
    }
}
