package uk.ac.leedsBeckett.ase;

import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Component
public class ParsingUtils {

    public List<String> getTokens(String input) {
        List<String> tokens = new ArrayList<>();
        if (input != null && !input.isEmpty()) {
            if (input.contains(" ")) {
                tokens.addAll(Arrays.stream(input.split(" "))
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()));
            } else {
                tokens.add(input.toUpperCase());
            }
        }
        return tokens;
    }

    public PencilColour parseStrokeColour(List<String> tokens) {
        return tokens.stream()
                .filter(token -> Arrays.stream(PencilColour.values())
                        .anyMatch(value -> value.getName().equals(token)))
                .map(PencilColour::valueOf)
                .findFirst()
                .orElse(PencilColour.DEFAULT);
    }

    public Boolean parseFill(List<String> tokens) {
        return tokens.contains("FILL");
    }

    public Action parseAction(List<String> tokens) {
        return tokens.stream()
                .filter(token -> Arrays.stream(Action.values())
                        .anyMatch(value -> value.getName().equals(token)))
                .map(Action::valueOf)
                .findFirst()
                .orElse(Action.NONE);
    }

    public DoubleStream parseCoordinates(List<String> tokens) {
        return tokens.stream()
                .filter(this::isNumeric)
                .mapToDouble(Double::parseDouble);
    }

    public Shape parseShape(Action action, List<Double> coordinates) {
        Shape shape = null;
        switch (action) {
            case CIRCLE: {
                shape = Circle.createCircle(coordinates);
                break;
            }
            case RECTANGLE:
            case SQUARE: {
                shape = Rectangle.createRectangle(coordinates);
                break;
            }
            case TRIANGLE: {
                shape = Triangle.createTriangle(coordinates);
            }
        }
        return shape;
    }

    private boolean isNumeric(String token) {
        boolean result;
        try {
            Double.parseDouble(token);
            result = true;
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
