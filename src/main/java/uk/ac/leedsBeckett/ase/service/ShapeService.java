package uk.ac.leedsBeckett.ase.service;

import javafx.scene.canvas.GraphicsContext;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.Rectangle;
import uk.ac.leedsBeckett.ase.model.Shape;

@Component
public class ShapeService {

    public void drawShape(Shape shape, GraphicsContext graphicsContext) {
        if (shape instanceof Rectangle) {
            graphicsContext.rect(
                    shape.getX(),
                    shape.getY(),
                    shape.getWidth(),
                    shape.getHeight());
        } else if (shape instanceof Circle) {
            graphicsContext.arc(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight(), 0d, 360.0);
        }
        graphicsContext.stroke();
    }
}
