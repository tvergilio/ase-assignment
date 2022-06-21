package uk.ac.leedsBeckett.ase.model;

import javafx.scene.shape.Polygon;

import java.security.InvalidParameterException;
import java.util.List;

public class Triangle extends Polygon {

    public static final double[] DEFAULT_X_POINTS = {156d, 247d, 343d};
    public static final double[] DEFAULT_Y_POINTS = {189d, 50d, 191d};

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
        switch (parameters.size()) {
            case 6: { //parameters specified
                return new Triangle(parameters.get(0),
                        parameters.get(1),
                        parameters.get(2),
                        parameters.get(3),
                        parameters.get(4),
                        parameters.get(5));
            } case 0: { //default size and position
                return new Triangle(DEFAULT_X_POINTS[0],
                        DEFAULT_Y_POINTS[0],
                        DEFAULT_X_POINTS[1],
                        DEFAULT_Y_POINTS[1],
                        DEFAULT_X_POINTS[2],
                        DEFAULT_Y_POINTS[2]);
            } default: {
                throw new InvalidParameterException("You must pass zero or six parameters.");
            }
        }
    }
}
