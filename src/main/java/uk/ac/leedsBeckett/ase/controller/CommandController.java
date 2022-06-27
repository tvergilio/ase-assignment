package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Command;
import uk.ac.leedsBeckett.ase.model.Pencil;
import uk.ac.leedsBeckett.ase.service.CommandParserService;
import uk.ac.leedsBeckett.ase.service.ShapeService;

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
            draw(canvas, command);
            feedback.append("Command entered: ")
                    .append(input);
        }
        return feedback.toString();
    }

    private void draw(Pane canvas, Command command) {
        changePencilColour(command);
        Pencil pencil = Pencil.getInstance();
        Color color = pencil.getPencilColour().getColor();
        Shape shape = command.getShape();
        if (shape != null) {
            shape.setStroke(color);
            if (command.getFill()) {
                shape.setFill(color);
            }
            shapeService.drawShape(shape, canvas);
        }
    }

    private void changePencilColour(Command command) {
        Pencil pencil = Pencil.getInstance();
        pencil.setPencilColour(command.getPencilColour());
    }
}
