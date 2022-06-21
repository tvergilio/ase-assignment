package uk.ac.leedsBeckett.ase.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import java.security.InvalidParameterException;
import java.util.List;

public class Rectangle extends Polygon {

    public static final Point2D DEFAULT_POSITION = new Point2D(200d, 100d);
    public static final double DEFAULT_WIDTH = 100d;
    public static final double DEFAULT_HEIGHT = 100d;

    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Rectangle(double... points) {
        super(points);
        switch (points.length) {
            case 4: {
                setLayoutX(points[0]);
                setLayoutY(points[1]);
                width = points[2];
                height = points[3];
                break;
            } case 2: {
                setLayoutX(points[0]);
                setLayoutY(points[1]);
            }
        }
    }

    public static Rectangle createRectangle(List<Double> parameters) {
        switch (parameters.size()) {
            case 4: { //position and size specified
                return new Rectangle(parameters.get(0),
                        parameters.get(1),
                        parameters.get(2),
                        parameters.get(3));
            }
            case 3: { //position and one side specified (square)
                return new Rectangle(parameters.get(0),
                        parameters.get(1),
                        parameters.get(2),
                        parameters.get(2));
            }
            case 2: { //position specified, default size
                return new Rectangle(parameters.get(0),
                        parameters.get(1),
                        DEFAULT_WIDTH,
                        DEFAULT_HEIGHT);
            }
            case 1: { //default position, one side specified (square)
                return new Rectangle(DEFAULT_POSITION.getX(),
                        DEFAULT_POSITION.getY(),
                        parameters.get(0),
                        parameters.get(0));
            }
            case 0: { //default position and size (square)
                return new Rectangle(DEFAULT_POSITION.getX(),
                        DEFAULT_POSITION.getY(),
                        DEFAULT_WIDTH,
                        DEFAULT_HEIGHT);
            }
            default: {
                throw new InvalidParameterException("You must pass a maximum of four parameters.");
            }
        }
    }

}
