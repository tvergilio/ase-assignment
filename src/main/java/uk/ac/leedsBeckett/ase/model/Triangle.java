package uk.ac.leedsBeckett.ase.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class Triangle extends Polygon {

    public static final double DEFAULT_SIZE = 30d;
    public static final double DEFAULT_X = 100d;
    public static final double DEFAULT_Y = 100d;

    private final Point2D a;
    private final Point2D b;
    private final Point2D c;

    private Triangle(Point2D a, Point2D b, Point2D c) {
        super(a.getX(),
                a.getY(),
                b.getX(),
                b.getY(),
                c.getX(),
                c.getY());
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point2D getA() {
        return a;
    }

    public Point2D getB() {
        return b;
    }

    public Point2D getC() {
        return c;
    }

    private static List<Point2D> calculatePoints(Point2D midPoint, double distanceFromCentre) {
        return Arrays.asList(new Point2D(midPoint.getX(), midPoint.getY() - distanceFromCentre),
                new Point2D(midPoint.getX() - distanceFromCentre, midPoint.getY() + distanceFromCentre),
                new Point2D(midPoint.getX() + distanceFromCentre, midPoint.getY() + distanceFromCentre));
    }

    private Triangle setLayout(Point2D midPoint, double size) {
        this.setLayoutX(midPoint.getX() - size);
        this.setLayoutY(midPoint.getY() - size);
        System.out.println("Points: A: " + a + " B: " + b + " C: " + c + " Midpoint: " + midPoint);
        return this;
    }

    /**
     * Create an isosceles triangle specifying x and y (position) and size
     * @param parameters The first element represents the x coordinate.
     *                   The second element represents the y coordinate.
     *                   The third element represents the size.
     * @return an instance of a triangle.
     */
    public static Triangle createTriangle(List<Double> parameters) {
        List<Point2D> points;
        Point2D position;
        double size;

        switch (parameters.size()) {
            case 3: { //position and size specified
                position = new Point2D(parameters.get(0), parameters.get(1));
                size = parameters.get(2);
                break;
            }
            case 2: { //position specified, default size
                position = new Point2D(parameters.get(0), parameters.get(1));
                size = DEFAULT_SIZE;
                break;
            }
            case 1: { //default position, size specified
                position = new Point2D(DEFAULT_X, DEFAULT_Y);
                size = parameters.get(0);
                break;
            }
            case 0: { //default position and size
                position = new Point2D(DEFAULT_X, DEFAULT_Y);
                size = DEFAULT_SIZE;
                break;
            }
            default: {
                throw new InvalidParameterException("You must pass between zero and three parameters.");
            }
        }
        points = Triangle.calculatePoints(position, size);
        return new Triangle(points.get(0), points.get(1), points.get(2))
                .setLayout(position, size);
    }
}
