package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Action;
import uk.ac.leedsBeckett.ase.model.Command;
import uk.ac.leedsBeckett.ase.model.Pencil;
import uk.ac.leedsBeckett.ase.service.CommandParserService;
import uk.ac.leedsBeckett.ase.service.ShapeService;

import java.security.InvalidParameterException;

@Component
public class CommandController {

    private final CommandParserService commandParserService;
    private final ShapeService shapeService;

    public CommandController(CommandParserService commandParserService, ShapeService shapeService) {
        this.commandParserService = commandParserService;
        this.shapeService = shapeService;
    }

    public String execute(String input, Pane canvas) {
        StringBuilder feedback = new StringBuilder();
        if (input != null && !input.isEmpty() && canvas != null) {
            Command command = commandParserService.parseInput(input);
            Shape shape = command.getShape();
            boolean move = Action.MOVE.equals(command.getAction());
            if (shape != null) {
                changePencilColour(command);
                draw(canvas, shape, command.getFill());
            } else if (move) {
                if (2 != command.getCoordinates().size()) {
                    throw new InvalidParameterException("You must pass X and Y coordinates to move the pencil.");
                }
                shapeService.movePencil(command.getCoordinates().get(0), command.getCoordinates().get(1), canvas);
            }
            feedback.append("Command entered: ")
                    .append(input);
        }
        return feedback.toString();
    }

    private void draw(Pane canvas, Shape shape, Boolean fill) {
        Pencil pencil = Pencil.getInstance();
        Color color = pencil.getPencilColour().getColor();
        shape.setStroke(color);
        if (fill) {
            shape.setFill(color);
        }
        shapeService.drawShape(shape, canvas);
    }

    private void changePencilColour(Command command) {
        Pencil.getInstance()
                .setPencilColour(command.getPencilColour());
    }
}
