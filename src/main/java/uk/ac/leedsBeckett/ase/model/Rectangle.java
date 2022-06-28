package uk.ac.leedsBeckett.ase.model;

import javafx.scene.shape.Polygon;

import java.security.InvalidParameterException;
import java.util.List;

public class Rectangle extends Polygon {

    public static final double DEFAULT_WIDTH = 30d;
    public static final double DEFAULT_HEIGHT = 30d;
    public static final double DEFAULT_X = 100d;
    public static final double DEFAULT_Y = 100d;

    private final double width;
    private final double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Rectangle(double x, double y, double width, double height) {
        super(x, y,
                x + width, y,
                x + width, y + height,
                x, y + height);
        this.width = width;
        this.height = height;
        setLayoutX(x);
        setLayoutY(y);
    }

    public static Rectangle createRectangle(List<Double> parameters) {
        double x;
        double y;
        double width;
        double height;

        switch (parameters.size()) {
            case 4: { //position and size specified
                x = parameters.get(0);
                y = parameters.get(1);
                width = parameters.get(2);
                height = parameters.get(3);
                break;
            }
            case 3: { //position and one side specified (square)
                x = parameters.get(0);
                y = parameters.get(1);
                width = parameters.get(2);
                height = parameters.get(2);
                break;
            }
            case 2: { //position specified, default size
                x = parameters.get(0);
                y = parameters.get(1);
                width = DEFAULT_WIDTH;
                height = DEFAULT_HEIGHT;
                break;
            }
            case 1: { //default position, one side specified (square)
                x = DEFAULT_X;
                y = DEFAULT_Y;
                width = parameters.get(0);
                height = parameters.get(0);
                break;
            }
            case 0: { //default position and size (square)
                x = DEFAULT_X;
                y = DEFAULT_Y;
                width = DEFAULT_WIDTH;
                height = DEFAULT_HEIGHT;
                break;
            }
            default: {
                throw new InvalidParameterException("You must pass a maximum of four parameters.");
            }
        }
        PencilUtils.movePencil(x + width / 2, y + height / 2);
        return new Rectangle(x, y, width, height);
    }

}
