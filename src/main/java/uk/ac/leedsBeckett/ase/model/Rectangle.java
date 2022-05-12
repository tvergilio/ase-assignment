package uk.ac.leedsBeckett.ase.model;

public class Rectangle extends Shape {

    public Rectangle(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }

    public static Rectangle createRectangle(double[] parameters) {
        switch (parameters.length) {
            case 4: {
                return new Rectangle(parameters[0], parameters[1], parameters[2], parameters[3]);
            }
            case 3: {
            }
            case 2: {
                return new Rectangle(250d, 100d, parameters[0], parameters[1]);
            }
            case 1: {
                return new Rectangle(250d, 100d, parameters[0], parameters[0]);
            }
            default: {
                return new Rectangle(200d, 100d, 100d, 100d);
            }
        }
    }
}
