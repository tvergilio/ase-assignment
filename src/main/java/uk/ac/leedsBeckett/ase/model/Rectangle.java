package uk.ac.leedsBeckett.ase.model;

import javafx.scene.shape.Polygon;

public class Rectangle extends Polygon {

    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
            } case 3: {
                setLayoutX(points[0]);
                setLayoutY(points[1]);
                width = points[2];
                height = points[2];
                break;
            } case 2: {
                setLayoutX(points[0]);
                setLayoutY(points[1]);
            }
        }
    }

    public static Rectangle createRectangle(double[] parameters) {
        switch (parameters.length) {
            case 4: {
                return new Rectangle(parameters[0], parameters[1], parameters[2], parameters[3]);
            }
            case 3: {
                return new Rectangle(parameters[0], parameters[1], parameters[2]);
            }
            case 2: {
                return new Rectangle(parameters[0], parameters[1], 100d, 100d); //default size
            }
            case 1: {
                return new Rectangle(200d, 100d, parameters[0], parameters[0]); //default position
            }
            default: {
                return new Rectangle(200d, 100d, 100d, 100d); //default size and position
            }
        }
    }

}
