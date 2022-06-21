package uk.ac.leedsBeckett.ase.model;

public enum Action {
    CIRCLE("CIRCLE"), SQUARE("SQUARE"), RECTANGLE("RECTANGLE"), TRIANGLE("TRIANGLE"), NONE("NONE");

    final String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
