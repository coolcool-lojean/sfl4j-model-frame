package com.smart.fast.learning.model.model;

import com.smart.fast.learning.model.algo.Array2DClassifyHandler;
import com.smart.fast.learning.model.algo.Array2DSharpHandler;
import com.smart.fast.learning.model.algo.ClassifyFramePointHandler;
import com.smart.fast.learning.model.domain.ClassifyForArray2D;
import com.smart.fast.learning.model.domain.FramePointFor2D;
import com.smart.fast.learning.model.domain.IntegerArray2D;
import com.smart.fast.learning.model.domain.PointFor2D;

import java.util.ArrayList;
import java.util.List;

public class LayerExecutor {

    public static List<FramePointFor2D> handleLayer(IntegerArray2D layerData, int findLevel, int minSize) {
        //锐化
        IntegerArray2D result = Array2DSharpHandler.sharpArrayByLevel(0, 255, findLevel, layerData);
        //近似分类
        ClassifyForArray2D classifyForArray2D = Array2DClassifyHandler.devideClassify(result);

        List<ArrayList<PointFor2D>> classifyArray = classifyForArray2D.getClassifyArray();
        List<FramePointFor2D> allFramePoint = new ArrayList<>();
        for (ArrayList<PointFor2D> groupList : classifyArray) {
            //找出连续图像
            List<FramePointFor2D> outFramePoint = ClassifyFramePointHandler.findOutFramePoint(groupList, minSize);
            allFramePoint.addAll(outFramePoint);
        }
        return allFramePoint;
    }
}
