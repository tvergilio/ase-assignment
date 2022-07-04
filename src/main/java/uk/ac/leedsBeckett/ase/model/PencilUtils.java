package uk.ac.leedsBeckett.ase.model;

public class PencilUtils {

    static void movePencil(double x, double y) {
        Pencil pencil = Pencil.getInstance();
        pencil.setLayoutX(x);
        pencil.setLayoutY(y);
    }
}
