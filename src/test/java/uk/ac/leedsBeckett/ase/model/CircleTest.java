package uk.ac.leedsBeckett.ase.model;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    private final double testXPosition = 10.0;
    private final double testYPosition = 20.0;
    private final double testDiameter = 60.0;

    @Test
    void createCircle_withThreeParameters_createsCircleCorrectly() {
        Circle circle = Circle.createCircle(Arrays.asList(testXPosition, testYPosition, testDiameter));
        assertEquals(testXPosition, circle.getLayoutX());
        assertEquals(testYPosition, circle.getLayoutY());
        assertEquals(testDiameter/2, circle.getRadius());
    }

    @Test
    void createCircle_withTwoParameters_createsCircleUsingDefaultRadius() {
        Circle circle = Circle.createCircle(Arrays.asList(testXPosition, testYPosition));
        assertEquals(testXPosition, circle.getLayoutX());
        assertEquals(testYPosition, circle.getLayoutY());
        assertEquals(Circle.DEFAULT_RADIUS, circle.getRadius());
    }

    @Test
    void createCircle_withOneParameter_createsCircleUsingDefaultPosition() {
        Circle circle = Circle.createCircle(List.of(testDiameter));
        assertEquals(Circle.DEFAULT_X, circle.getLayoutX());
        assertEquals(Circle.DEFAULT_Y, circle.getLayoutY());
        assertEquals(testDiameter/2, circle.getRadius());
    }

    @Test
    void createCircle_withNoParameters_createsCircleUsingDefaultRadiusAndPosition() {
        Circle circle = Circle.createCircle(List.of());
        assertEquals(Circle.DEFAULT_X, circle.getLayoutX());
        assertEquals(Circle.DEFAULT_Y, circle.getLayoutY());
        assertEquals(Circle.DEFAULT_RADIUS, circle.getRadius());
    }

    @Test
    void createCircle_withFourParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Circle.createCircle(Arrays.asList(1.0, 2.0, 3.0, 4.0)),
                "Exception was not thrown.");
    }
}