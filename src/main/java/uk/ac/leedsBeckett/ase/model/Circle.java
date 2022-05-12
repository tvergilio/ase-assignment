package uk.ac.leedsBeckett.ase.model;

public class Circle extends Shape {

    public Circle(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }

    public static Circle createCircle(double[] parameters) {
        switch (parameters.length) {
            case 4: {
                return new Circle(parameters[0], parameters[1], parameters[2], parameters[3]);
            }
            case 3: {
            }
            case 2: {
                return new Circle(250d, 150d, parameters[0], parameters[1]);
            }
            case 1: {
                return new Circle(250d, 150d, parameters[0], parameters[0]);
            }
            default: {
                return new Circle(250d, 150d, 100d, 100d);
            }

        }

    }
}
