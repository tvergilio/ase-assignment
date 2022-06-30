package uk.ac.leedsBeckett.ase.service;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.exceptions.ShapeNotSupportedException;
import uk.ac.leedsBeckett.ase.model.*;

@Component
public class ShapeService {

    public void drawShape(Shape shape, Pane canvas) {
        if (isSupported(shape)) {
            if (!(shape instanceof Line)) {  //line is drawn in its proper place by default, it doesn't need to be relocated
                shape.relocate(shape.getLayoutX(), shape.getLayoutY());
            }
            canvas.getChildren().add(shape);
            movePencil(shape.getLayoutX(), shape.getLayoutY(), canvas);
        } else {
            throw new ShapeNotSupportedException("The shape specified is not supported: " + shape.getClass().getName());
        }
    }

    private boolean isSupported(Shape shape) {
        return shape instanceof Rectangle || shape instanceof Circle || shape instanceof Triangle || shape instanceof Line;
    }

    public void movePencil(double x, double y, Pane canvas) {
        Pencil pencil = Pencil.getInstance();
        canvas.getChildren().remove(pencil);
        pencil.setCenterX(x);
        pencil.setCenterY(y);
        canvas.getChildren().add(pencil);
    }
}
