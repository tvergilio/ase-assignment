package uk.ac.leedsBeckett.ase.service;

import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.Rectangle;

import java.util.Arrays;

@Component
public class CommandParserService {

    public Shape getShape(String input) {
        String command = input;
        String[] dimensions;

        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
            dimensions = input.substring(input.indexOf(" ") + 1).split(" ");
        } else {
            dimensions = new String[]{};
        }
        Shape shape = null;
        switch (command) {
            case "circle": {
                shape = Circle.createCircle(Arrays.stream(dimensions)
                        .filter(s -> !s.isEmpty())
                        .mapToDouble(Double::valueOf)
                        .toArray());
                break;
            }
            case "rectangle":
            case "square": {
                shape = Rectangle.createRectangle(Arrays.stream(dimensions)
                        .filter(s -> !s.isEmpty())
                        .mapToDouble(Double::valueOf)
                        .toArray());
            }
        }
        return shape;
    }
}
