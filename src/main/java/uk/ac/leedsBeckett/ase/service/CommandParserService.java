package uk.ac.leedsBeckett.ase.service;

import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandParserService {

    private List<String> tokens;
    private Command command;

    public Command parseInput(String input) {
        tokens = new ArrayList<>();
        command = new Command();
        if (input.contains(" ")) {
            tokens.addAll(Arrays.stream(input.split(" "))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList()));
        } else {
            tokens.add(input.toUpperCase());
        }
        parseStrokeColour();
        parseFill();
        parseAction();
        parseCoordinates();
        getShape();
        return command;
    }

    private void parseStrokeColour() {
        PencilColour pencilColour = Arrays.stream(PencilColour.values())
                .map(PencilColour::getName)
                .filter(tokens::contains)
                .map(PencilColour::valueOf)
                .findFirst()
                .orElse(PencilColour.DEFAULT);

        command.setColour(pencilColour);
    }

    private void parseAction() {
        Action action = Arrays.stream(Action.values())
                .map(Action::getName)
                .filter(tokens::contains)
                .map(Action::valueOf)
                .findFirst()
                .orElse(Action.NONE);

        command.setAction(action);
    }

    private void parseFill() {
        command.setFill(tokens.contains("FILL"));
    }

    public void getShape() {
        Shape shape = null;
        switch (command.getAction()) {
            case CIRCLE: {
                shape = Circle.createCircle(command.getCoordinates());
                break;
            }
            case RECTANGLE:
            case SQUARE: {
                shape = Rectangle.createRectangle(command.getCoordinates());
            }
        }
        command.setShape(shape);
    }

    private void parseCoordinates() {
        tokens.stream()
                .filter(this::isNumeric)
                .mapToDouble(Integer::parseInt)
                .forEach(command.getCoordinates()::add);
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
