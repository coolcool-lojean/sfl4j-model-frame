package com.smart.fast.learning.model;

import com.smart.fast.learning.model.model.ImageRbgLayersDevider;
import com.smart.fast.learning.model.model.LayerConfig;
import com.smart.fast.learning.model.model.LayerHandleThreadManager;
import com.smart.fast.learning.model.algo.FramePointArrangeHandler;
import com.smart.fast.learning.model.domain.FramePointFor2D;
import com.smart.fast.learning.model.domain.IntegerArray2D;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FrameModel {

    public static List<FramePointFor2D> findImageFrame(BufferedImage image, LayerConfig layerConfig) {
        try {
            List<IntegerArray2D> list = ImageRbgLayersDevider.devide(image);
            final CountDownLatch countDownLatch = new CountDownLatch(list.size());
            LayerHandleThreadManager threadManager = new LayerHandleThreadManager(layerConfig);
            List<FramePointFor2D> allPointList = threadManager.getFramePointResult(list, countDownLatch);
            countDownLatch.await();
            return FramePointArrangeHandler.arrangeFramePoint(allPointList, image.getHeight(), image.getWidth());
        }catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }
}
