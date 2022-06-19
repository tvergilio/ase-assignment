package uk.ac.leedsBeckett.ase.model;

import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private Action action;
    private PencilColour colour;
    private Shape shape;
    private Boolean solid;
    private final List<Double> coordinates = new ArrayList<>();

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public PencilColour getColour() {
        return colour;
    }

    public void setColour(PencilColour colour) {
        this.colour = colour;
    }

    public Boolean getSolid() {
        return solid;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setSolid(Boolean solid) {
        this.solid = solid;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
}
