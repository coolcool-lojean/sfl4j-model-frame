package com.smart.fast.learning.model.model;

import com.smart.fast.learning.model.domain.FramePointFor2D;
import com.smart.fast.learning.model.domain.IntegerArray2D;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LayerExecuteThread implements Runnable {

    private CountDownLatch countDownLatch;

    private LayerExecuteResult result;

    private IntegerArray2D layerData;

    private LayerConfig layerConfig;

    public LayerExecuteThread(CountDownLatch countDownLatch, LayerExecuteResult result, IntegerArray2D layerData, LayerConfig layerConfig) {
        this.countDownLatch = countDownLatch;
        this.result = result;
        this.layerData = layerData;
        this.layerConfig = layerConfig;
    }

    @Override
    public void run() {
        List<FramePointFor2D> data = LayerExecutor.handleLayer(layerData, layerConfig.getLevel(), layerConfig.getMinSize());
        result.getAllPointList().addAll(data);
        countDownLatch.countDown();
    }
}
