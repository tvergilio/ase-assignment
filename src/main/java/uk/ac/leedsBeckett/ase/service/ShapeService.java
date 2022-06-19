package uk.ac.leedsBeckett.ase.service;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.Rectangle;

@Component
public class ShapeService {

    public void drawShape(Shape shape, GraphicsContext graphicsContext) {
        graphicsContext.beginPath();

        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;

            graphicsContext.rect(rectangle.getLayoutX(),
                    rectangle.getLayoutY(),
                    rectangle.getWidth(),
                    rectangle.getHeight());

        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;

            graphicsContext.arc(circle.getCenterX(),
                    circle.getCenterY(),
                    circle.getRadius(),
                    circle.getRadius(),
                    0,
                    360.0);
        }
        graphicsContext.stroke();
    }

    public void fillShape(GraphicsContext graphicsContext) {
        graphicsContext.fill();
    }
}
