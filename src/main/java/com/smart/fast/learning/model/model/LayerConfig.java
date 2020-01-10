package com.smart.fast.learning.model.model;

public class LayerConfig {

    private Integer level;

    private Integer minSize;

    public LayerConfig(Integer level, Integer minSize) {
        this.level = level;
        this.minSize = minSize;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }
}
