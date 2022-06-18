package uk.ac.leedsBeckett.ase.model;

import javafx.scene.shape.Polygon;

import java.util.List;

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

    public static Rectangle createRectangle(List<Double> parameters) {
        switch (parameters.size()) {
            case 4: {
                return new Rectangle(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
            }
            case 3: {
                return new Rectangle(parameters.get(0), parameters.get(1), parameters.get(2));
            }
            case 2: {
                return new Rectangle(parameters.get(0), parameters.get(1), 100d, 100d); //default size
            }
            case 1: {
                return new Rectangle(200d, 100d, parameters.get(0), parameters.get(0)); //default position
            }
            default: {
                return new Rectangle(200d, 100d, 100d, 100d); //default size and position
            }
        }
    }

}
