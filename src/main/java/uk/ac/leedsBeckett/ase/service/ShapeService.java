package uk.ac.leedsBeckett.ase.service;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.exceptions.ShapeNotSupportedException;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.Rectangle;
import uk.ac.leedsBeckett.ase.model.Triangle;

@Component
public class ShapeService {

    public void drawShape(Shape shape, Pane canvas) {
        if (shape instanceof Rectangle || shape instanceof Circle || shape instanceof Triangle) {
            shape.relocate(shape.getLayoutX(), shape.getLayoutY());
            canvas.getChildren().add(shape);
        } else {
            throw new ShapeNotSupportedException("The shape specified is not supported: " + shape.getClass().getName());
        }
    }
}
