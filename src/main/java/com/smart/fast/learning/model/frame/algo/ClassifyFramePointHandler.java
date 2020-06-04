package com.smart.fast.learning.model.frame.algo;

import com.smart.fast.learning.model.frame.domain.FramePointFor2D;
import com.smart.fast.learning.model.frame.domain.PointFor2D;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;

public class ClassifyFramePointHandler {

    public static List<FramePointFor2D> findOutFramePoint(ArrayList<PointFor2D> pointList, int minSize) {
        TreeMap<String, PointFor2D> pointMap = new TreeMap<>();
        pointList.forEach(pointFor2D -> pointMap.put(pointFor2D.getX() + "_" + pointFor2D.getY(), pointFor2D));
        List<ArrayList<PointFor2D>> groupList = new ArrayList<>();
        while (! pointMap.isEmpty()) {
            String key = pointMap.firstKey();
            PointFor2D pointFor2D = pointMap.get(key);
            pointMap.remove(key);
            ArrayList<PointFor2D> groupItemList = findOutGroup(pointFor2D, pointMap);
            groupList.add(groupItemList);
        }
        List<FramePointFor2D> resultList = new ArrayList<>();
        for (ArrayList<PointFor2D> itemList : groupList) {
            if (itemList.size() > minSize) {
                FramePointFor2D minAndMax = findMinAndMax(itemList);
                if (checkUpToMinSize(minAndMax, minSize)) {
                    resultList.add(minAndMax);
                }
            }
        }
        return resultList;
    }

    //=============================================================================

    private static ArrayList<PointFor2D> findOutGroup(PointFor2D pointFor2D, TreeMap<String, PointFor2D> pointMap) {
        ArrayList<PointFor2D> searchList = new ArrayList<>();
        searchList.add(pointFor2D);
        for (int i = 0; i < searchList.size(); i++) {
            PointFor2D searchPoint = searchList.get(i);
            findPoint(searchList, searchPoint, pointMap, -1, -1);
            findPoint(searchList, searchPoint, pointMap, -1, 0);
            findPoint(searchList, searchPoint, pointMap, -1, 1);

            findPoint(searchList, searchPoint, pointMap, 0, -1);
            findPoint(searchList, searchPoint, pointMap, 0, 1);

            findPoint(searchList, searchPoint, pointMap, 1, -1);
            findPoint(searchList, searchPoint, pointMap, 1, 0);
            findPoint(searchList, searchPoint, pointMap, 1, 1);
        }
        return searchList;
    }

    private static void findPoint(List<PointFor2D> searchList, PointFor2D searchPoint, TreeMap<String, PointFor2D> pointMap, int stepX, int stepY) {
        String key = generateKey(searchPoint, stepX, stepY);
        if (pointMap.containsKey(key)) {
            PointFor2D findPoint = pointMap.get(key);
            searchList.add(findPoint);
            pointMap.remove(key);
        }
    }

    private static String generateKey(PointFor2D pointFor2D, int stepX, int stepY) {
        return (pointFor2D.getX() + stepX) + "_" + (pointFor2D.getY() + stepY);
    }

    //=============================================================================

    private static FramePointFor2D findMinAndMax(ArrayList<PointFor2D> groupItemList) {
        int minX = 100000, minY = 100000, maxX = 0, maxY = 0;
        for (PointFor2D point : groupItemList) {
            if (minX > point.getX()) {
                minX = point.getX();
            }
            if (maxX < point.getX()) {
                maxX = point.getX();
            }
            if (minY > point.getY()) {
                minY = point.getY();
            }
            if (maxY < point.getY()) {
                maxY = point.getY();
            }
        }
        return new FramePointFor2D(new PointFor2D(minX, minY), new PointFor2D(maxX, maxY));
    }

    private static boolean checkUpToMinSize(FramePointFor2D framePointFor2D, int minSize) {
        Integer minX = framePointFor2D.getMinPoint().getX();
        Integer minY = framePointFor2D.getMinPoint().getY();
        Integer maxX = framePointFor2D.getMaxPoint().getX();
        Integer maxY = framePointFor2D.getMaxPoint().getY();
        return ((minSize < (maxX - minX)) && (minSize < (maxY - minY)));
    }
}
