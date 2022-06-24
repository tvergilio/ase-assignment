package uk.ac.leedsBeckett.ase;

import javafx.scene.shape.Shape;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.ac.leedsBeckett.ase.model.Action;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.PencilColour;
import uk.ac.leedsBeckett.ase.model.Rectangle;

import java.util.List;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ParsingUtilsTest {

    @Autowired
    private ParsingUtils parsingUtils;

    @Test
    void getTokens_withOneCommandLowerCase_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("circle");
        assertEquals(1, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
    }

    @Test
    void getTokens_withOneCommandUpperCase_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("CIRCLE");
        assertEquals(1, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
    }

    @Test
    void getTokens_withOneCommandMixedCase_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("CIrClE");
        assertEquals(1, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
    }

    @Test
    void getTokens_withOneCommandAndOneSpace_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("circle ");
        assertEquals(1, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
    }

    @Test
    void getTokens_withOneCommandAndTwoSpaces_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("circle  ");
        assertEquals(1, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
    }

    @Test
    void getTokens_withTwoCommands_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("circle red");
        assertEquals(2, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
        assertTrue(tokens.contains("RED"));
    }

    @Test
    void getTokens_withFiveCommands_returnsCorrectTokens() {
        List<String> tokens = parsingUtils.getTokens("circle RED 200 100 30");
        assertEquals(5, tokens.size());
        assertTrue(tokens.contains("CIRCLE"));
        assertTrue(tokens.contains("RED"));
        assertTrue(tokens.contains("200"));
        assertTrue(tokens.contains("100"));
        assertTrue(tokens.contains("30"));
    }

    @Test
    void getTokens_withEmptyString_returnsNoTokens() {
        List<String> tokens = parsingUtils.getTokens("");
        assertEquals(0, tokens.size());
    }

    @Test
    void getTokens_withNullString_returnsNoTokens() {
        List<String> tokens = parsingUtils.getTokens("");
        assertEquals(0, tokens.size());
    }

    @Test
    void parseStrokeColour_withSupportedValue_returnsColour() {
        PencilColour pencilColour = parsingUtils.parseStrokeColour(List.of("BLUE"));
        assertEquals(PencilColour.BLUE, pencilColour);
    }

    @Test
    void parseStrokeColour_withSupportedValueContained_returnsColour() {
        PencilColour pencilColour = parsingUtils.parseStrokeColour(List.of("APPLE", "BLUE", "PEAR"));
        assertEquals(PencilColour.BLUE, pencilColour);
    }

    @Test
    void parseStrokeColour_withMultipleSupportedValues_returnsTheFirstColour() {
        PencilColour pencilColour = parsingUtils.parseStrokeColour(List.of("BLACK", "BLUE", "WHITE"));
        assertEquals(PencilColour.BLACK, pencilColour);
    }

    @Test
    void parseStrokeColour_withNoSupportedValues_returnsTheDefaultColour() {
        PencilColour pencilColour = parsingUtils.parseStrokeColour(List.of("APPLE", "PEAR", "BANANA"));
        assertEquals(PencilColour.DEFAULT, pencilColour);
    }

    @Test
    void parseStrokeColour_withEmptyString_returnsTheDefaultColour() {
        PencilColour pencilColour = parsingUtils.parseStrokeColour(List.of(""));
        assertEquals(PencilColour.DEFAULT, pencilColour);
    }

    @Test
    void parseFill_withOnlyCorrectString_returnsTrue() {
        assertTrue(parsingUtils.parseFill(List.of("FILL")));
    }

    @Test
    void parseFill_withCorrectStringContained_returnsTrue() {
        assertTrue(parsingUtils.parseFill(List.of("APPLE", "FILL", "PEAR")));
    }

    @Test
    void parseFill_withCorrectStringNotContained_returnsFalse() {
        assertFalse(parsingUtils.parseFill(List.of("APPLE", "PEAR")));
    }

    @Test
    void parseAction_withSupportedValue_returnsAction() {
        Action action = parsingUtils.parseAction(List.of("CIRCLE"));
        assertEquals(Action.CIRCLE, action);
    }

    @Test
    void parseAction_withSupportedValueContained_returnsAction() {
        Action action = parsingUtils.parseAction(List.of("APPLE", "PEAR", "CIRCLE"));
        assertEquals(Action.CIRCLE, action);
    }

    @Test
    void parseAction_withMultipleSupportedValues_returnsTheFirstAction() {
        Action action = parsingUtils.parseAction(List.of("TRIANGLE", "SQUARE", "CIRCLE"));
        assertEquals(Action.TRIANGLE, action);
    }

    @Test
    void parseAction_withNoSupportedValues_returnsActionNone() {
        Action action = parsingUtils.parseAction(List.of("APPLE", "PEAR", "BANANA"));
        assertEquals(Action.NONE, action);
    }

    @Test
    void parseAction_withEmptyString_returnsActionNone() {
        Action action = parsingUtils.parseAction(List.of(""));
        assertEquals(Action.NONE, action);
    }

    @Test
    void parseCoordinates_withOnlyNumericToken_returnsCorrectStream() {
        List<String> input = List.of("10.5");
        DoubleStream result = parsingUtils.parseCoordinates(input);
        assertEquals(10.5, result.findAny().getAsDouble());
    }

    @Test
    void parseCoordinates_withNumericTokenContained_returnsCorrectStream() {
        List<String> input = List.of("APPLE", "10.5", "PEAR");
        DoubleStream result = parsingUtils.parseCoordinates(input);
        assertEquals(10.5, result.findAny().getAsDouble());
    }

    @Test
    void parseCoordinates_withNoNumericTokenContained_returnsEmptyStream() {
        List<String> input = List.of("APPLE", "BANANA", "PEAR");
        DoubleStream result = parsingUtils.parseCoordinates(input);
        assertEquals(0, result.count());
    }

    @Test
    void parseCoordinates_withMultipleNumericTokensContained_returnsCorrectStream() {
        List<String> input = List.of("20.24", "10.5", "55");
        DoubleStream result = parsingUtils.parseCoordinates(input);
        assertEquals(10.5, result.sorted().findFirst().getAsDouble());
    }

    @Test
    void parseShape_withCircleRadiusAndCoordinates_returnsCircle() {
        Shape shape = parsingUtils.parseShape(Action.CIRCLE, List.of(200.0, 100.0, 20.0));
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(20.0, circle.getRadius());
    }

    @Test
    void parseShape_withCircleRadius_returnsCircle() {
        Shape shape = parsingUtils.parseShape(Action.CIRCLE, List.of(20.0));
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(20.0, circle.getRadius());
    }

    @Test
    void parseShape_withCircleNoValues_returnsDefaultCircle() {
        Shape shape = parsingUtils.parseShape(Action.CIRCLE, List.of());
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(Circle.DEFAULT_RADIUS, circle.getRadius());
    }

    @Test
    void parseShape_withSquareSideAndCoordinates_returnsSquare() {
        Shape shape = parsingUtils.parseShape(Action.SQUARE, List.of(200.0, 100.0, 20.0));
        assertTrue(shape instanceof Rectangle);
        Rectangle square = (Rectangle) shape;
        assertEquals(20.0, square.getWidth());
        assertEquals(20.0, square.getHeight());
    }

    @Test
    void parseShape_withSquareSide_returnsSquare() {
        Shape shape = parsingUtils.parseShape(Action.SQUARE, List.of(20.0));
        assertTrue(shape instanceof Rectangle);
        Rectangle square = (Rectangle) shape;
        assertEquals(20.0, square.getWidth());
        assertEquals(20.0, square.getHeight());
    }

    @Test
    void parseShape_withSquareNoValues_returnsDefaultSquare() {
        Shape shape = parsingUtils.parseShape(Action.SQUARE, List.of());
        assertTrue(shape instanceof Rectangle);
        Rectangle square = (Rectangle) shape;
        assertEquals(Rectangle.DEFAULT_WIDTH, square.getWidth());
        assertEquals(Rectangle.DEFAULT_HEIGHT, square.getHeight());
    }

    @Test
    void parseShape_withRectangleSidesAndCoordinates_returnsRectangle() {
        Shape shape = parsingUtils.parseShape(Action.SQUARE, List.of(200.0, 100.0, 20.0, 40.0));
        assertTrue(shape instanceof Rectangle);
        Rectangle rectangle = (Rectangle) shape;
        assertEquals(20.0, rectangle.getWidth());
        assertEquals(40.0, rectangle.getHeight());
    }

    @Test
    void parseShape_withRectangleSideAndCoordinates_returnsSquare() {
        Shape shape = parsingUtils.parseShape(Action.SQUARE, List.of(200.0, 100.0, 20.0));
        assertTrue(shape instanceof Rectangle);
        Rectangle rectangle = (Rectangle) shape;
        assertEquals(20.0, rectangle.getWidth());
        assertEquals(20.0, rectangle.getHeight());
    }

    @Test
    void parseShape_withRectangleNoValues_returnsDefaultSquare() {
        Shape shape = parsingUtils.parseShape(Action.RECTANGLE, List.of());
        assertTrue(shape instanceof Rectangle);
        Rectangle square = (Rectangle) shape;
        assertEquals(Rectangle.DEFAULT_WIDTH, square.getWidth());
        assertEquals(Rectangle.DEFAULT_HEIGHT, square.getHeight());
    }
}