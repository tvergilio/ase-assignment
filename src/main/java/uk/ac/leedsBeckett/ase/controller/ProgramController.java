package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ProgramController {

    private final CommandController commandController;

    public ProgramController(CommandController commandController) {
        this.commandController = commandController;
    }

    public String execute(String input, Pane canvas) {
        return Arrays.stream(input.split("[\\r\\n]+"))
                .map(line -> commandController.execute(line, canvas))
                .collect(Collectors.joining("\n"));
    }
}