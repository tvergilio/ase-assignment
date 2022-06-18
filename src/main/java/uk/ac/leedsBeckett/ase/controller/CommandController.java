package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
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
        graphicsContext.setFill(Color.YELLOW); //change this later to accept different colours at runtime
        Command command = commandParserService.parseInput(input);
        graphicsContext.setStroke(command.getColour().getColor());
        Shape shape = commandParserService.getShape();
        if (shape != null) {
            shapeService.drawShape(shape, graphicsContext);
        }
        return "Command entered: " + input;
    }
}
