package uk.ac.leedsBeckett.ase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.ac.leedsBeckett.ase.ParsingUtils;
import uk.ac.leedsBeckett.ase.model.Action;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CommandParserServiceTest {

    @Spy
    private ParsingUtils parsingUtils;

    private CommandParserService commandParserService;

    @BeforeEach
    void setUp() {
        commandParserService = new CommandParserService(parsingUtils);
    }

    @Test
    void parseInput_withCircleLowerCase_callsExpectedParsingMethods() {
        commandParserService.parseInput("circle");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.CIRCLE, List.of());
    }

    @Test
    void parseInput_withCircleAndCoordinates_callsExpectedParsingMethods() {
        commandParserService.parseInput("circle 200 100");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.CIRCLE, List.of(200.0, 100.0));
    }

    @Test
    void parseInput_withCircleCoordinatesAndRadius_callsExpectedParsingMethods() {
        commandParserService.parseInput("circle 200 100 20");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.CIRCLE, List.of(200.0, 100.0, 20.0));
    }

    @Test
    void parseInput_withCircleCoordinatesRadiusAndColour_callsExpectedParsingMethodsWithCorrectArguments() {
        commandParserService.parseInput("circle 200 100 20 blue");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(List.of("CIRCLE", "200", "100", "20", "BLUE"));
        verify(parsingUtils, times(1)).parseFill(List.of("CIRCLE", "200", "100", "20", "BLUE"));
        verify(parsingUtils, times(1)).parseAction(List.of("CIRCLE", "200", "100", "20", "BLUE"));
        verify(parsingUtils, times(1)).parseCoordinates(List.of("CIRCLE", "200", "100", "20", "BLUE"));
        verify(parsingUtils, times(1)).parseShape(Action.CIRCLE, List.of(200.0, 100.0, 20.0));
    }

    @Test
    void parseInput_withCircleCoordinatesRadiusColourAndFill_callsExpectedParsingMethodsWithCorrectArguments() {
        commandParserService.parseInput("circle 200 100 20 blue fill");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(List.of("CIRCLE", "200", "100", "20", "BLUE", "FILL"));
        verify(parsingUtils, times(1)).parseFill(List.of("CIRCLE", "200", "100", "20", "BLUE", "FILL"));
        verify(parsingUtils, times(1)).parseAction(List.of("CIRCLE", "200", "100", "20", "BLUE", "FILL"));
        verify(parsingUtils, times(1)).parseCoordinates(List.of("CIRCLE", "200", "100", "20", "BLUE", "FILL"));
        verify(parsingUtils, times(1)).parseShape(Action.CIRCLE, List.of(200.0, 100.0, 20.0));
    }

    @Test
    void parseInput_withEmptyString_doesNotCallParsingMethods() {
        commandParserService.parseInput("");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(0)).parseStrokeColour(any());
        verify(parsingUtils, times(0)).parseFill(any());
        verify(parsingUtils, times(0)).parseAction(any());
        verify(parsingUtils, times(0)).parseCoordinates(any());
        verify(parsingUtils, times(0)).parseShape(Action.CIRCLE, List.of());
    }

    @Test
    void parseInput_withTriangleLowerCase_callsExpectedParsingMethods() {
        commandParserService.parseInput("triangle");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.TRIANGLE, List.of());
    }

    @Test
    void parseInput_withTriangleAndCoordinates_callsExpectedParsingMethods() {
        commandParserService.parseInput("triangle 200 100");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.TRIANGLE, List.of(200.0, 100.0));
    }

    @Test
    void parseInput_withTriangleCoordinatesAndSize_callsExpectedParsingMethods() {
        commandParserService.parseInput("triangle 200 100 30");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.TRIANGLE, List.of(200.0, 100.0, 30.0));
    }

    @Test
    void parseInput_withSquareLowerCase_callsExpectedParsingMethods() {
        commandParserService.parseInput("square");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.SQUARE, List.of());
    }

    @Test
    void parseInput_withSquareAndCoordinates_callsExpectedParsingMethods() {
        commandParserService.parseInput("square 200 100");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.SQUARE, List.of(200.0, 100.0));
    }

    @Test
    void parseInput_withSquareCoordinatesAndSide_callsExpectedParsingMethods() {
        commandParserService.parseInput("square 200 100 20");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.SQUARE, List.of(200.0, 100.0, 20.0));
    }

    @Test
    void parseInput_withRectangleLowerCase_callsExpectedParsingMethods() {
        commandParserService.parseInput("rectangle");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.RECTANGLE, List.of());
    }

    @Test
    void parseInput_withRectangleAndCoordinates_callsExpectedParsingMethods() {
        commandParserService.parseInput("rectangle 200 100");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.RECTANGLE, List.of(200.0, 100.0));
    }

    @Test
    void parseInput_withRectangleCoordinatesWidthAndHeight_callsExpectedParsingMethods() {
        commandParserService.parseInput("rectangle 200 100 20 40");
        verify(parsingUtils, times(1)).getTokens(any());
        verify(parsingUtils, times(1)).parseStrokeColour(any());
        verify(parsingUtils, times(1)).parseFill(any());
        verify(parsingUtils, times(1)).parseAction(any());
        verify(parsingUtils, times(1)).parseCoordinates(any());
        verify(parsingUtils, times(1)).parseShape(Action.RECTANGLE, List.of(200.0, 100.0, 20.0, 40.0));
    }
}
