package com.smart.fast.learning.model.algo;

import com.smart.fast.learning.model.domain.IntegerArray2D;

import java.util.ArrayList;
import java.util.List;

public class Array2DSharpHandler {

    public static IntegerArray2D sharpArrayByLevel(int startRange, int endRange, int level, IntegerArray2D oldArray2D) {
        List<Integer> range = getRangeListByLevel(startRange, endRange, level);
        ArrayList<ArrayList<Integer>> array2D = oldArray2D.getArray2D();

        IntegerArray2D result = new IntegerArray2D(oldArray2D.getRowNum(), oldArray2D.getLineNum());
        ArrayList<ArrayList<Integer>> resultArray2D = result.getArray2D();

        for (int i = 0; i < oldArray2D.getRowNum(); i++) {
            ArrayList<Integer> row = array2D.get(i);
            ArrayList<Integer> resultRow = resultArray2D.get(i);
            for (int j = 0; j < oldArray2D.getLineNum(); j++) {
                Integer cellNo = row.get(j);
                int sharpNo = chooseSharpNo(range, cellNo);
                resultRow.add(sharpNo);
            }
        }
        return result;
    }

    private static List<Integer> getRangeListByLevel(int startRange, int endRange, int level) {
        int size = endRange - startRange;
        int subSize = size / level;
        int fixSize = (size % level) / level + ((level/2) <= (size % level) ? 1 : 0);
        List<Integer> range = new ArrayList<>();
        int nextStart = startRange;
        range.add(nextStart);
        for (int i = 1; i < level; i++) {
            nextStart = nextStart + subSize + fixSize;
            range.add(nextStart);
        }
        range.add(endRange);
        return range;
    }

    private static int chooseSharpNo(List<Integer> rangeList, int cellNo) {
        for (int i = 0; i < rangeList.size() - 1; i++) {
            Integer range = rangeList.get(i);
            Integer nextRange = rangeList.get(i + 1);
            if (cellNo > nextRange) {
                continue;
            }
            int flagNo = (range + nextRange) / 2;
            if (cellNo <= flagNo) {
                return range;
            }else {
                return nextRange;
            }
        }
        throw new RuntimeException("cell number out of range! : " + cellNo);
    }
}
