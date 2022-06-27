package uk.ac.leedsBeckett.ase.service;

import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.ParsingUtils;
import uk.ac.leedsBeckett.ase.model.*;

import java.util.List;

@Component
public class CommandParserService {

    private final ParsingUtils parsingUtils;

    public CommandParserService(ParsingUtils parsingUtils) {
        this.parsingUtils = parsingUtils;
    }

    public Command parseInput(String input) {
        List<String> tokens = parsingUtils.getTokens(input);
        Command command = new Command();
        if (!tokens.isEmpty()) {
            command.setPencilColour(parsingUtils.parseStrokeColour(tokens));
            command.setFill(parsingUtils.parseFill(tokens));
            command.setAction(parsingUtils.parseAction(tokens));
            parsingUtils.parseCoordinates(tokens).forEach(command.getCoordinates()::add);
            command.setShape(parsingUtils.parseShape(command.getAction(), command.getCoordinates()));
        }
        return command;
    }

}
