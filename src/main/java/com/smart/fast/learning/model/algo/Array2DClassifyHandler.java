package com.smart.fast.learning.model.algo;

import com.smart.fast.learning.model.domain.ClassifyForArray2D;
import com.smart.fast.learning.model.domain.IntegerArray2D;
import com.smart.fast.learning.model.domain.PointFor2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Array2DClassifyHandler {

    public static ClassifyForArray2D devideClassify(IntegerArray2D array2D) {
        HashMap<Integer, ArrayList<PointFor2D>> classifyMap = new HashMap<>();

        ArrayList<ArrayList<Integer>> rowList = array2D.getArray2D();
        for (int i = 0; i < array2D.getRowNum(); i++) {
            ArrayList<Integer> lineList = rowList.get(i);
            for (int j = 0; j < array2D.getLineNum(); j++) {
                Integer cellNo = lineList.get(j);

                if (classifyMap.containsKey(cellNo)) {
                    ArrayList<PointFor2D> pointList = classifyMap.get(cellNo);
                    pointList.add(new PointFor2D(i, j));
                }else {
                    ArrayList<PointFor2D> pointList = new ArrayList<>();
                    pointList.add(new PointFor2D(i, j));
                    classifyMap.put(cellNo, pointList);
                }
            }
        }
        Collection<ArrayList<PointFor2D>> values = classifyMap.values();
        return new ClassifyForArray2D(new ArrayList<>(values));
    }
}
