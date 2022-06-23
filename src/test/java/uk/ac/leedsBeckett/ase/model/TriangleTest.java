package uk.ac.leedsBeckett.ase.model;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {

    private final double size = 10.0;
    private final double x = 200;
    private final double y = 100;

    @Test
    void createTriangle_withThreeParameters_createsTriangleCorrectly() {
        Triangle triangle = Triangle.createTriangle(List.of(x, y, size));
        assertEquals(x, triangle.getA().getX());
        assertEquals(x - size, triangle.getB().getX());
        assertEquals(x + size, triangle.getC().getX());
        //Y is inverted
        assertEquals(y - size, triangle.getA().getY());
        assertEquals(y + size, triangle.getB().getY());
        assertEquals(y + size, triangle.getB().getY());
        //Position
        assertEquals(x - size, triangle.getLayoutX());
        assertEquals(y - size, triangle.getLayoutY());
    }

    @Test
    void createTriangle_withTwoParameters_createsTriangleCorrectly() {
        Triangle triangle = Triangle.createTriangle(List.of(x, y));
        assertEquals(x, triangle.getA().getX());
        assertEquals(x - Triangle.DEFAULT_SIZE, triangle.getB().getX());
        assertEquals(x + Triangle.DEFAULT_SIZE, triangle.getC().getX());
        //Y is inverted
        assertEquals(y - Triangle.DEFAULT_SIZE, triangle.getA().getY());
        assertEquals(y + Triangle.DEFAULT_SIZE, triangle.getB().getY());
        assertEquals(y + Triangle.DEFAULT_SIZE, triangle.getB().getY());
        //Position
        assertEquals(x - Triangle.DEFAULT_SIZE, triangle.getLayoutX());
        assertEquals(y - Triangle.DEFAULT_SIZE, triangle.getLayoutY());
    }

    @Test
    void createTriangle_withOneParameter_createsTriangleCorrectly() {
        Triangle triangle = Triangle.createTriangle(List.of(size));
        assertEquals(Triangle.DEFAULT_X, triangle.getA().getX());
        assertEquals(Triangle.DEFAULT_X - size, triangle.getB().getX());
        assertEquals(Triangle.DEFAULT_X + size, triangle.getC().getX());
        //Y is inverted
        assertEquals(Triangle.DEFAULT_Y - size, triangle.getA().getY());
        assertEquals(Triangle.DEFAULT_Y + size, triangle.getB().getY());
        assertEquals(Triangle.DEFAULT_Y + size, triangle.getB().getY());
        //Position
        assertEquals(Triangle.DEFAULT_X - size, triangle.getLayoutX());
        assertEquals(Triangle.DEFAULT_Y - size, triangle.getLayoutY());
    }

    @Test
    void createTriangle_withNoParameters_createsTriangleCorrectly() {
        Triangle triangle = Triangle.createTriangle(List.of());
        assertEquals(Triangle.DEFAULT_X, triangle.getA().getX());
        assertEquals(Triangle.DEFAULT_X - Triangle.DEFAULT_SIZE, triangle.getB().getX());
        assertEquals(Triangle.DEFAULT_X + Triangle.DEFAULT_SIZE, triangle.getC().getX());
        //Y is inverted
        assertEquals(Triangle.DEFAULT_Y - Triangle.DEFAULT_SIZE, triangle.getA().getY());
        assertEquals(Triangle.DEFAULT_Y + Triangle.DEFAULT_SIZE, triangle.getB().getY());
        assertEquals(Triangle.DEFAULT_Y + Triangle.DEFAULT_SIZE, triangle.getB().getY());
        //Position
        assertEquals(Triangle.DEFAULT_X - Triangle.DEFAULT_SIZE, triangle.getLayoutX());
        assertEquals(Triangle.DEFAULT_Y - Triangle.DEFAULT_SIZE, triangle.getLayoutY());
    }

    @Test
    void createTriangle_withFourParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(Arrays.asList(1.0, 2.0, 3.0, 4.0)),
                "Exception was not thrown.");
    }

}