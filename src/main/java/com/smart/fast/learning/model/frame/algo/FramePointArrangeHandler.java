package com.smart.fast.learning.model.frame.algo;

import com.smart.fast.learning.model.frame.domain.FramePointFor2D;
import com.smart.fast.learning.model.frame.domain.PointFor2D;

import java.util.ArrayList;
import java.util.List;

public class FramePointArrangeHandler {

    private static double aroundParam = 0.10;
    private static double lowParam = 0.90;
    private static double highParam = 1.10;

    public static List<FramePointFor2D> arrangeFramePoint(List<FramePointFor2D> framePointList, int hight, int width) {
        List<FramePointFor2D> endPointList = new ArrayList<>();
        if (! framePointList.isEmpty()) {
            for (FramePointFor2D framePoint : framePointList) {
                if (endPointList.isEmpty()) {
                    endPointList.add(framePoint);
                    continue;
                }
                //去除整个页面边框
                boolean isImageFrame = checkImageFrame(framePoint, hight, width);
                if (isImageFrame) {
                    continue;
                }
                dealFramePoint(endPointList, framePoint);
            }
        }
        return checkRepeatPoint(endPointList, hight, width);
    }

    private static void dealFramePoint(List<FramePointFor2D> endPointList, FramePointFor2D framePoint) {
        boolean add = true;
        for (FramePointFor2D endPoint : endPointList) {
            //包含校验
            boolean inset = checkInsetPoint(endPoint, framePoint);
            if (inset) {
                mergePoint(endPoint, framePoint);
                add = false;
            }else {
                //反向检查，反向包含则调换
                boolean contain = checkInsetPoint(framePoint, endPoint);
                if (contain) {
                    mergePoint(endPoint, framePoint);
                    add = false;
                }else {
                    //临近检查，临近则合并
                    boolean around = checkAroundPoint(endPoint, framePoint);
                    if (around) {
                        mergePoint(endPoint, framePoint);
                        add = false;
                    }
                }
            }
        }
        if (add) {
            endPointList.add(framePoint);
        }
    }

    private static List<FramePointFor2D> checkRepeatPoint(List<FramePointFor2D> endPointList, int hight, int width) {
        List<FramePointFor2D> resultList = new ArrayList<>(endPointList);
        if (! endPointList.isEmpty()) {
            for (int i = 0; i < endPointList.size(); i++) {
                for (int j = 0; j < endPointList.size(); j++) {
                    if (i == j) {
                        boolean isImageFrame = checkImageFrame(endPointList.get(j), hight, width);
                        if (isImageFrame) {
                            resultList.remove(endPointList.get(j));
                        }
                        continue;
                    }

                    boolean same = checkSamePoint(endPointList.get(i), endPointList.get(j));
                    if (same) {
                        resultList.remove(endPointList.get(j));
                    }else {
                        boolean isImageFrame = checkImageFrame(endPointList.get(j), hight, width);
                        if (isImageFrame) {
                            resultList.remove(endPointList.get(j));
                        }
                    }
                }
            }
        }
        return resultList;
    }

    private static boolean checkSamePoint(FramePointFor2D existPoint, FramePointFor2D checkPoint) {
        int existMinX = existPoint.getMinPoint().getX();
        int minMinX = (int) Math.ceil(existMinX * lowParam);
        int minMaxX = (int) Math.ceil(existMinX * highParam);
        int checkMinX = checkPoint.getMinPoint().getX();
        boolean sameMinX = (minMinX <= checkMinX && minMaxX >= checkMinX);

        int existMinY = existPoint.getMinPoint().getY();
        int minMinY = (int) Math.ceil(existMinY * lowParam);
        int minMaxY = (int) Math.ceil(existMinY * highParam);
        int checkMinY = checkPoint.getMinPoint().getY();
        boolean sameMinY = (minMinY <= checkMinY && minMaxY >= checkMinY);

        int existMaxX = existPoint.getMaxPoint().getX();
        int maxMinX = (int) Math.ceil(existMaxX * lowParam);
        int maxMaxX = (int) Math.ceil(existMaxX * highParam);
        int checkMaxX = checkPoint.getMaxPoint().getX();
        boolean sameMaxX = (maxMinX <= checkMaxX && maxMaxX >= checkMaxX);

        int existMaxY = existPoint.getMaxPoint().getY();
        int maxMinY = (int) Math.ceil(existMaxY * lowParam);
        int maxMaxY = (int) Math.ceil(existMaxY * highParam);
        int checkMaxY = checkPoint.getMaxPoint().getY();
        boolean sameMaxY = (maxMinY <= checkMaxY && maxMaxY >= checkMaxY);
        //同一校验
        return sameMinX && sameMinY && sameMaxX && sameMaxY;
    }

    private static boolean checkInsetPoint(FramePointFor2D existPoint, FramePointFor2D checkPoint) {
        int existMinX = existPoint.getMinPoint().getX();
        int checkMinX = checkPoint.getMinPoint().getX();
        boolean insetMinX = (existMinX <= checkMinX);

        int existMinY = existPoint.getMinPoint().getY();
        int checkMinY = checkPoint.getMinPoint().getY();
        boolean insetMinY = (existMinY <= checkMinY);

        int existMaxX = existPoint.getMaxPoint().getX();
        int checkMaxX = checkPoint.getMaxPoint().getX();
        boolean insetMaxX = (existMaxX >= checkMaxX);

        int existMaxY = existPoint.getMaxPoint().getY();
        int checkMaxY = checkPoint.getMaxPoint().getY();
        boolean insetMaxY = (existMaxY >= checkMaxY);
        //包含校验
        return insetMinX && insetMinY && insetMaxX && insetMaxY;
    }

    private static boolean checkImageFrame(FramePointFor2D framePoint, int hight, int width) {
        Integer minX = framePoint.getMinPoint().getX();
        Integer minY = framePoint.getMinPoint().getY();
        Integer maxX = framePoint.getMaxPoint().getX();
        Integer maxY = framePoint.getMaxPoint().getY();
        //去除整个页面边框
        if ((width * aroundParam) >= minX && (hight * aroundParam) >= minY
                && ((width - 1) * (1 - aroundParam)) <= maxX && ((hight - 1) * (1 - aroundParam)) <= maxY) {
            return true;
        }
        return false;
    }

    private static boolean checkAroundPoint(FramePointFor2D existPoint, FramePointFor2D checkPoint) {
        int existMinX = existPoint.getMinPoint().getX();
        int minMinX = (int) Math.ceil(existMinX * lowParam);

        int existMaxX = existPoint.getMaxPoint().getX();
        int maxMaxX = (int) Math.ceil(existMaxX * highParam);

        int existMinY = existPoint.getMinPoint().getY();
        int minMinY = (int) Math.ceil(existMinY * lowParam);

        int existMaxY = existPoint.getMaxPoint().getY();
        int maxMaxY = (int) Math.ceil(existMaxY * highParam);

        Integer minX = checkPoint.getMinPoint().getX();
        Integer minY = checkPoint.getMinPoint().getY();
        Integer maxX = checkPoint.getMaxPoint().getX();
        Integer maxY = checkPoint.getMaxPoint().getY();

        if ((minMinX <= minX && minX <= maxMaxX) && (minMinY <= minY && minY <= maxMaxY)) {
            return true;
        }
        if ((minMinX <= minX && minX <= maxMaxX) && (minMinY <= maxY && maxY <= maxMaxY)) {
            return true;
        }
        if ((minMinX <= maxX && maxX <= maxMaxX) && (minMinY <= minY && minY <= maxMaxY)) {
            return true;
        }
        if ((minMinX <= maxX && maxX <= maxMaxX) && (minMinY <= maxY && maxY <= maxMaxY)) {
            return true;
        }
        return false;
    }

    private static void mergePoint(FramePointFor2D targetPoint, FramePointFor2D checkPoint) {
        Integer minX1 = targetPoint.getMinPoint().getX();
        Integer minY1 = targetPoint.getMinPoint().getY();
        Integer maxX1 = targetPoint.getMaxPoint().getX();
        Integer maxY1 = targetPoint.getMaxPoint().getY();

        Integer minX2 = checkPoint.getMinPoint().getX();
        Integer minY2 = checkPoint.getMinPoint().getY();
        Integer maxX2 = checkPoint.getMaxPoint().getX();
        Integer maxY2 = checkPoint.getMaxPoint().getY();

        Integer minX = minX1 < minX2 ? minX1 : minX2;
        Integer minY = minY1 < minY2 ? minY1 : minY2;
        Integer maxX = maxX1 > maxX2 ? maxX1 : maxX2;
        Integer maxY = maxY1 > maxY2 ? maxY1 : maxY2;
        targetPoint.setMinPoint(new PointFor2D(minX, minY));
        targetPoint.setMaxPoint(new PointFor2D(maxX, maxY));
    }
}
