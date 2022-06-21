package uk.ac.leedsBeckett.ase.model;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {

    private final double testXPosition = 15.0;
    private final double testYPosition = 18.0;
    private final double testWidth = 30.0;
    private final double testHeight = 20.0;

    @Test
    void createRectangle_withFourParameters_createsRectangleCorrectly() {
        Rectangle rectangle = Rectangle.createRectangle(Arrays.asList(testXPosition, testYPosition, testWidth, testHeight));
        assertEquals(testXPosition, rectangle.getLayoutX());
        assertEquals(testYPosition, rectangle.getLayoutY());
        assertEquals(testWidth, rectangle.getWidth());
        assertEquals(testHeight, rectangle.getHeight());
    }

    @Test
    void createRectangle_withThreeParameters_createsSquare() {
        Rectangle rectangle = Rectangle.createRectangle(Arrays.asList(testXPosition, testYPosition, testWidth));
        assertEquals(testXPosition, rectangle.getLayoutX());
        assertEquals(testYPosition, rectangle.getLayoutY());
        assertEquals(testWidth, rectangle.getWidth());
        assertEquals(testWidth, rectangle.getHeight());
    }

    @Test
    void createRectangle_withTwoParameters_createsRectangleUsingDefaultSize() {
        Rectangle rectangle = Rectangle.createRectangle(Arrays.asList(testXPosition, testYPosition));
        assertEquals(testXPosition, rectangle.getLayoutX());
        assertEquals(testYPosition, rectangle.getLayoutY());
        assertEquals(Rectangle.DEFAULT_HEIGHT, rectangle.getWidth());
        assertEquals(Rectangle.DEFAULT_WIDTH, rectangle.getHeight());
    }

    @Test
    void createRectangle_withOneParameters_createsSquareUsingDefaultPosition() {
        Rectangle rectangle = Rectangle.createRectangle(List.of(testWidth));
        assertEquals(Rectangle.DEFAULT_POSITION.getX(), rectangle.getLayoutX());
        assertEquals(Rectangle.DEFAULT_POSITION.getY(), rectangle.getLayoutY());
        assertEquals(testWidth, rectangle.getWidth());
        assertEquals(testWidth, rectangle.getHeight());
    }

    @Test
    void createRectangle_withNoParameters_createsSquareUsingDefaultPositionAndSize() {
        Rectangle rectangle = Rectangle.createRectangle(List.of());
        assertEquals(Rectangle.DEFAULT_POSITION.getX(), rectangle.getLayoutX());
        assertEquals(Rectangle.DEFAULT_POSITION.getY(), rectangle.getLayoutY());
        assertEquals(Rectangle.DEFAULT_WIDTH, rectangle.getWidth());
        assertEquals(Rectangle.DEFAULT_HEIGHT, rectangle.getHeight());
    }

    @Test
    void createRectangle_withFiveParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Rectangle.createRectangle(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)),
                "Exception was not thrown.");
    }
}