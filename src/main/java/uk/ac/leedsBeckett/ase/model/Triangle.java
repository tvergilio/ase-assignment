package uk.ac.leedsBeckett.ase.model;

import javafx.scene.shape.Polygon;

import java.util.List;

public class Triangle extends Polygon {

    private final double[] xPoints = new double[3];
    private final double[] yPoints = new double[3];

    public double[] getXPoints() {
        return xPoints;
    }

    public double[] getYPoints() {
        return yPoints;
    }

    public Triangle(double... points) {
        super(points);
        if (points.length == 6) {
            xPoints[0] = points[0];
            yPoints[0] = points[1];
            xPoints[1] = points[2];
            yPoints[1] = points[3];
            xPoints[2] = points[4];
            yPoints[2] = points[5];
        }
    }

    public static Triangle createTriangle(List<Double> parameters) {
        if (parameters.size() == 6) {
            return new Triangle(parameters.get(0), parameters.get(1), parameters.get(2),
                    parameters.get(3), parameters.get(4), parameters.get(5));
        }
        return new Triangle(156, 189, 247, 50, 343, 191); //default size and position
    }
}
