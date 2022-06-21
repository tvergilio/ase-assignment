package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Command;
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

    public String execute(String input, GraphicsContext graphicsContext) {
        graphicsContext.setLineWidth(3.0);
        Command command = commandParserService.parseInput(input);
        choosePencil(graphicsContext, command);
        draw(graphicsContext, command);
        return "Command entered: " + input;
    }

    private void choosePencil(GraphicsContext graphicsContext, Command command) {
        Color color = command.getColour().getColor();
        graphicsContext.setStroke(color);
        graphicsContext.setFill(color);
    }

    private void draw(GraphicsContext graphicsContext, Command command) {
        if (command.getShape() != null) {
            shapeService.drawShape(command.getShape(), graphicsContext);
        }
        if (command.getFill()) {
            shapeService.fillShape(command.getShape(), graphicsContext);
        }
    }
}
