package uk.ac.leedsBeckett.ase.model;

import javafx.geometry.Point2D;

import java.security.InvalidParameterException;
import java.util.List;

public class Line extends javafx.scene.shape.Line {
    public static final Point2D DEFAULT_END = new Point2D(100.0, 100.0);

    public Line(Point2D start, Point2D end) {
        super(start.getX(), start.getY(), end.getX(), end.getY());
        this.setLayoutX(start.getX());
        this.setLayoutY(start.getY());
        System.out.println("x: " + this.getLayoutX() + ", y: " + this.getLayoutY());
    }

    public static Line createLine(List<Double> parameters) {
        Point2D start;
        Point2D end;
        switch (parameters.size()) {
            case 4: { //start and end points specified
                start = new Point2D(parameters.get(0), parameters.get(1));
                end = new Point2D(parameters.get(2), parameters.get(3));
                break;
            }
            case 2: { //line from pencil to end point specified
                Pencil pencil = Pencil.getInstance();
                start = new Point2D(pencil.getLayoutX(), pencil.getLayoutY());
                end = new Point2D(parameters.get(0), parameters.get(1));
                break;
            }
            case 0: { //default line (from pencil to default point)
                start = new Point2D(Pencil.getInstance().getCenterX(), Pencil.getInstance().getCenterY());
                end = DEFAULT_END;
                break;
            }
            default: {
                throw new InvalidParameterException("You must pass a maximum of three parameters.");
            }
        }
        PencilUtils.movePencil(end.getX(), end.getY());
        return new Line(start, end);
    }
}
