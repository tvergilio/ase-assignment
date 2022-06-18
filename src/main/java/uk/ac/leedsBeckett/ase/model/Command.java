package uk.ac.leedsBeckett.ase.model;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private Action action;
    private PencilColour colour;
    private List<Double> coordinates = new ArrayList<>();

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

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

}
