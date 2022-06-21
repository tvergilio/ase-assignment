package uk.ac.leedsBeckett.ase.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    private final double xPosition = 10.0;
    private final double yPosition = 20.0;
    private final double radius = 30.0;

    @Test
    void createCircle_withThreeParameters_createsCircleCorrectly() {
        Circle circle = Circle.createCircle(Arrays.asList(xPosition, yPosition, radius));
        assertEquals(xPosition, circle.getCenterX());
        assertEquals(yPosition, circle.getCenterY());
        assertEquals(radius, circle.getRadius());
    }
}