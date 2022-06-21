package uk.ac.leedsBeckett.ase.service;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.exceptions.ShapeNotSupportedException;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.Rectangle;
import uk.ac.leedsBeckett.ase.model.Triangle;

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
        } else if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            graphicsContext.strokePolygon(triangle.getXPoints(), triangle.getYPoints(), 3);
        } else {
            throw new ShapeNotSupportedException("The shape specified is not supported: " + shape.getClass().getName());
        }
        graphicsContext.stroke();
    }

    public void fillShape(Shape shape, GraphicsContext graphicsContext) {
        if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            graphicsContext.fillPolygon(triangle.getXPoints(), triangle.getYPoints(), 3);
        } else {
            graphicsContext.fill();
        }
    }
}
