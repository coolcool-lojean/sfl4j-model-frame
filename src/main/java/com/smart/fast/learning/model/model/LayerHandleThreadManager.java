package com.smart.fast.learning.model.model;

import com.smart.fast.learning.model.domain.FramePointFor2D;
import com.smart.fast.learning.model.domain.IntegerArray2D;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class LayerHandleThreadManager {

    private LayerConfig layerConfig;

    public LayerHandleThreadManager(LayerConfig layerConfig) {
        this.layerConfig = layerConfig;
    }

    public List<FramePointFor2D> getFramePointResult(List<IntegerArray2D> list, CountDownLatch countDownLatch) {
        List<FramePointFor2D> allPointList = new Vector<>();
        try {
            LayerExecuteResult result = new LayerExecuteResult(allPointList);
            for (IntegerArray2D layerData : list) {
                LayerExecuteThread thread1 = new LayerExecuteThread(countDownLatch, result, layerData, layerConfig);
                Thread thread = new Thread(thread1);
                thread.start();
            }
        }catch (Exception e) {
        }
        return allPointList;
    }
}
