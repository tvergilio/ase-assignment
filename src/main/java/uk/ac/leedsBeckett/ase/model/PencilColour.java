package uk.ac.leedsBeckett.ase.model;

import javafx.scene.paint.Color;

public enum PencilColour {
    DEFAULT(Color.WHITE, "WHITE"),
    BLACK(Color.BLACK, "BLACK"),
    WHITE(Color.WHITE, "WHITE"),
    BLUE(Color.BLUE, "BLUE"),
    GREEN(Color.GREEN, "GREEN"),
    YELLOW(Color.YELLOW, "YELLOW"),
    RED(Color.RED, "RED"),
    PINK(Color.PINK, "PINK"),
    GREY(Color.GREY, "GREY");

    final Color color;
    final String name;

    PencilColour(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
}
