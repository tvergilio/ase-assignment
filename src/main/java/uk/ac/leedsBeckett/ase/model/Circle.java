package uk.ac.leedsBeckett.ase.model;

import java.util.List;

public class Circle extends javafx.scene.shape.Circle {

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

            }
            case 1: {
                return new Circle(parameters.get(0));
            }
            default: {
                return new Circle(250d, 150d, 100d);
            }

        }

    }
}
