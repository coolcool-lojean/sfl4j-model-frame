package com.smart.fast.learning.model.frame.model;

import com.smart.fast.learning.model.frame.domain.IntegerArray2D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageRbgLayersDevider {

    public static List<IntegerArray2D> devide(BufferedImage image) {
        List<IntegerArray2D> list = new ArrayList<>();
        IntegerArray2D redLayer = new IntegerArray2D(image.getWidth(), image.getHeight());
        list.add(redLayer);
        IntegerArray2D greenLayer = new IntegerArray2D(image.getWidth(), image.getHeight());
        list.add(greenLayer);
        IntegerArray2D blueLayer = new IntegerArray2D(image.getWidth(), image.getHeight());
        list.add(blueLayer);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int rgbColor = image.getRGB(i, j);
                int redColor = (rgbColor & 0xff0000) >> 16;
                int greenColor = (rgbColor & 0xff00) >> 8;
                int blueColor = (rgbColor & 0xff);

                setColorToLayer(redLayer, i, redColor);
                setColorToLayer(greenLayer, i, greenColor);
                setColorToLayer(blueLayer, i, blueColor);
            }
        }
        return list;
    }

    private static void setColorToLayer(IntegerArray2D redLayer, int x, int color) {
        ArrayList<Integer> row = redLayer.getArray2D().get(x);
        row.add(color);
    }
}
