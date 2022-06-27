package uk.ac.leedsBeckett.ase.model;

public class Pencil extends Circle {

    public static final double DEFAULT_RADIUS = 1.5;
    public static final double DEFAULT_X = 20.0;
    public static final double DEFAULT_Y = 20.0;
    public static final double DEFAULT_STROKE_WIDTH = 1.5;

    private PencilColour pencilColour;
    private double strokeWidth;

    private static Pencil INSTANCE;

    public synchronized static Pencil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Pencil();
        }
        return INSTANCE;
    }

    private Pencil() {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_RADIUS, DEFAULT_STROKE_WIDTH, PencilColour.DEFAULT);
    }

    private Pencil(double layoutX, double layoutY, double pencilRadius, double strokeWidth, PencilColour pencilColour) {
        super(layoutX, layoutY, pencilRadius);
        this.strokeWidth = strokeWidth;
        this.pencilColour = pencilColour;
    }

    public PencilColour getPencilColour() {
        return pencilColour;
    }

    public void setPencilColour(PencilColour pencilColour) {
        this.pencilColour = pencilColour;
    }
}
