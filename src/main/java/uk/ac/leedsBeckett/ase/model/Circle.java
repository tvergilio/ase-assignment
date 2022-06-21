package uk.ac.leedsBeckett.ase.model;

import javafx.geometry.Point2D;

import java.security.InvalidParameterException;
import java.util.List;

public class Circle extends javafx.scene.shape.Circle {
    public static final Point2D DEFAULT_POSITION = new Point2D(250d, 150d);
    public static final double DEFAULT_RADIUS = 100d;

    public Circle(double radius) {
        super(radius);
    }

    public Circle(double x, double y, double radius) {
        super(x, y, radius);
        this.setCenterX(x);
        this.setCenterY(y);
    }

    public static Circle createCircle(List<Double> parameters) {
        switch (parameters.size()) {
            case 3: {
                return new Circle(parameters.get(0), parameters.get(1), parameters.get(2));
            }
            case 2: {
                return new Circle(parameters.get(0), parameters.get(1), DEFAULT_RADIUS);
            }
            case 1: {
                return new Circle(DEFAULT_POSITION.getX(), DEFAULT_POSITION.getY(), parameters.get(0));
            }
            case 0: {
                return new Circle(DEFAULT_POSITION.getX(), DEFAULT_POSITION.getY(), DEFAULT_RADIUS);
            } default: {
                throw new InvalidParameterException("You must pass a maximum of three parameters.");
            }

        }

    }
}
