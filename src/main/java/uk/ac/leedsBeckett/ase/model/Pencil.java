package uk.ac.leedsBeckett.ase.model;

import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

@Component
public class Pencil extends Circle {

    public static final double DEFAULT_RADIUS = 1.0;
    public static final double DEFAULT_X = 10.0;
    public static final double DEFAULT_Y = 10.0;
    public static final double DEFAULT_WIDTH = 3.0;
    public static final Color DEFAULT_COLOUR = Color.WHITE;

    private Color pencilColor;
    private double pencilWidth;

    public Pencil() {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_RADIUS, DEFAULT_WIDTH, DEFAULT_COLOUR);
    }

    private Pencil(double x, double y, double radius, double pencilWidth, Color pencilColor) {
        super(x, y, radius);
        this.pencilWidth = pencilWidth;
        this.pencilColor = pencilColor;
    }

    public Color getPencilColor() {
        return pencilColor;
    }

    public void setPencilColor(Color pencilColor) {
        this.pencilColor = pencilColor;
    }

    public double getPencilWidth() {
        return pencilWidth;
    }

    public void setPencilWidth(double pencilWidth) {
        this.pencilWidth = pencilWidth;
    }
}
