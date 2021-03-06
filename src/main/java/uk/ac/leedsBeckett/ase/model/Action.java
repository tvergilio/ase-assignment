package uk.ac.leedsBeckett.ase.model;

public enum Action {
    CIRCLE("CIRCLE"),
    SQUARE("SQUARE"),
    RECTANGLE("RECTANGLE"),
    TRIANGLE("TRIANGLE"),
    LINE("LINE"),
    MOVE("MOVE"),
    NONE("NONE");

    final String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
