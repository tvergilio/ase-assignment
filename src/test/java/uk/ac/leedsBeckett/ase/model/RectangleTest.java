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
        assertEquals(testXPosition - testWidth / 2, rectangle.getLayoutX());
        assertEquals(testYPosition - testHeight / 2, rectangle.getLayoutY());
        assertEquals(testWidth, rectangle.getWidth());
        assertEquals(testHeight, rectangle.getHeight());
    }

    @Test
    void createRectangle_withThreeParameters_createsSquare() {
        Rectangle rectangle = Rectangle.createRectangle(Arrays.asList(testXPosition, testYPosition, testWidth));
        assertEquals(testXPosition - testWidth / 2, rectangle.getLayoutX());
        assertEquals(testYPosition - testWidth / 2, rectangle.getLayoutY());
        assertEquals(testWidth, rectangle.getWidth());
        assertEquals(testWidth, rectangle.getHeight());
    }

    @Test
    void createRectangle_withTwoParameters_createsRectangleUsingDefaultSize() {
        Rectangle rectangle = Rectangle.createRectangle(Arrays.asList(testXPosition, testYPosition));
        assertEquals(testXPosition - Rectangle.DEFAULT_WIDTH / 2, rectangle.getLayoutX());
        assertEquals(testYPosition - Rectangle.DEFAULT_HEIGHT / 2, rectangle.getLayoutY());
        assertEquals(Rectangle.DEFAULT_HEIGHT, rectangle.getWidth());
        assertEquals(Rectangle.DEFAULT_WIDTH, rectangle.getHeight());
    }

    @Test
    void createRectangle_withOneParameter_createsSquareUsingDefaultPosition() {
        Rectangle rectangle = Rectangle.createRectangle(List.of(testWidth));
        assertEquals(Rectangle.DEFAULT_X - testWidth / 2, rectangle.getLayoutX());
        assertEquals(Rectangle.DEFAULT_Y - testWidth / 2, rectangle.getLayoutY());
        assertEquals(testWidth, rectangle.getWidth());
        assertEquals(testWidth, rectangle.getHeight());
    }

    @Test
    void createRectangle_withNoParameters_createsSquareUsingDefaultPositionAndSize() {
        Rectangle rectangle = Rectangle.createRectangle(List.of());
        assertEquals(Rectangle.DEFAULT_X - Rectangle.DEFAULT_WIDTH / 2, rectangle.getLayoutX());
        assertEquals(Rectangle.DEFAULT_Y - Rectangle.DEFAULT_HEIGHT / 2, rectangle.getLayoutY());
        assertEquals(Rectangle.DEFAULT_WIDTH, rectangle.getWidth());
        assertEquals(Rectangle.DEFAULT_HEIGHT, rectangle.getHeight());
    }

    @Test
    void createRectangle_withFiveParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Rectangle.createRectangle(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)),
                "Exception was not thrown.");
    }
}