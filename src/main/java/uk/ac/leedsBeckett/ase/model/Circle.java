package uk.ac.leedsBeckett.ase.model;

public class Circle extends javafx.scene.shape.Circle {

    public Circle(double radius) {
        super(radius);
    }

    public Circle(double x, double y, double radius) {
        super(x, y, radius);
        this.setCenterX(x);
        this.setCenterY(y);
    }

    public static Circle createCircle(double[] parameters) {
        switch (parameters.length) {
            case 3: {
                return new Circle(parameters[0], parameters[1], parameters[2]);
            }
            case 2: {

            }
            case 1: {
                return new Circle(parameters[0]);
            }
            default: {
                return new Circle(250d, 150d, 100d);
            }

        }

    }
}
