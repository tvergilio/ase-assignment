package uk.ac.leedsBeckett.ase.service;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.exceptions.ShapeNotSupportedException;
import uk.ac.leedsBeckett.ase.model.*;

@Component
public class ShapeService {

    public void drawShape(Shape shape, Pane canvas) {
        if (isSupported(shape)) {
            Point2D location = new Point2D(shape.getLayoutX(), shape.getLayoutY());
            if (!(shape instanceof Line)) {  //line is drawn in its proper place by default, it doesn't need to be relocated
                shape.relocate(location.getX(), location.getY());
            }
            canvas.getChildren().add(shape);
            drawPencil(location, canvas);
        } else {
            throw new ShapeNotSupportedException("The shape specified is not supported: " + shape.getClass().getName());
        }
    }

    private boolean isSupported(Shape shape) {
        return shape instanceof Rectangle || shape instanceof Circle || shape instanceof Triangle || shape instanceof Line;
    }

    private void drawPencil(Point2D location, Pane canvas) {
        Pencil pencil = Pencil.getInstance();
        canvas.getChildren().remove(pencil);
        pencil.setCenterX(location.getX());
        pencil.setCenterY(location.getY());
        canvas.getChildren().add(pencil);
    }
}
