package uk.ac.leedsBeckett.ase.model;

import java.security.InvalidParameterException;
import java.util.List;

public class Circle extends javafx.scene.shape.Circle {
    public static final double DEFAULT_RADIUS = 20d;
    public static final double DEFAULT_X = 100d;
    public static final double DEFAULT_Y = 100d;

    public Circle(double x, double y, double radius) {
        super(x, y, radius);
        this.setLayoutX(x - radius);
        this.setLayoutY(y - radius);
    }

    /**
     * Create a circle specifying x and y (position) and size (radius)
     * @param parameters The first element represents the x coordinate.
     *                   The second element represents the y coordinate.
     *                   The third element represents the size (radius).
     * @return an instance of a circle.
     */
    public static Circle createCircle(List<Double> parameters) {
        double x;
        double y;
        double radius;
        switch (parameters.size()) {
            case 3: { //position and size specified
                x = parameters.get(0);
                y = parameters.get(1);
                radius = parameters.get(2);
                break;
            }
            case 2: { //position specified, default size
                x = parameters.get(0);
                y = parameters.get(1);
                radius = DEFAULT_RADIUS;
                break;
            }
            case 1: { //size specified, default position (position of pencil)
                x = DEFAULT_X;
                y = DEFAULT_Y;
                radius = parameters.get(0);
                break;
            }
            case 0: { //default position (position of pencil) and default size
                x = DEFAULT_X;
                y = DEFAULT_Y;
                radius = DEFAULT_RADIUS;
                break;
            } default: {
                throw new InvalidParameterException("You must pass a maximum of three parameters.");
            }
        }
        PencilUtils.movePencil(x, y);
        return new Circle(x, y, radius);
    }
}
