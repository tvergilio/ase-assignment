package uk.ac.leedsBeckett.ase.model;

import uk.ac.leedsBeckett.ase.exceptions.PositionNotImplementedException;

public class PencilUtils {

    static void movePencil(double x, double y, PencilPosition pencilPosition) {
        switch (pencilPosition) {
            case CENTRE: {
                Pencil.getInstance().setLayoutX(x);
                Pencil.getInstance().setLayoutY(y);
                break;
            }
            case LEFT_CORNER: {
            }
            case RIGHT_CORNER: {
            }
            default: {
                throw new PositionNotImplementedException();
            }
        }
    }
}
