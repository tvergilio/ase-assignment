package uk.ac.leedsBeckett.ase.model;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {

    private final double[] testXPoints = {10.0, 12.0, 15.0};
    private final double[] testYPoints = {20.0, 22.0, 25.0};

    @Test
    void createTriangle_withSixParameters_createsTriangleCorrectly() {
        Triangle triangle = Triangle.createTriangle(Arrays.asList(testXPoints[0],
                testYPoints[0],
                testXPoints[1],
                testYPoints[1],
                testXPoints[2],
                testYPoints[2]));
        assertEquals(testXPoints[0], triangle.getXPoints()[0]);
        assertEquals(testYPoints[0], triangle.getYPoints()[0]);
        assertEquals(testXPoints[1], triangle.getXPoints()[1]);
        assertEquals(testYPoints[1], triangle.getYPoints()[1]);
        assertEquals(testXPoints[2], triangle.getXPoints()[2]);
        assertEquals(testYPoints[2], triangle.getYPoints()[2]);
    }

    @Test
    void createRectangle_withNoParameters_createsTriangleUsingDefaultPoints() {
        Triangle triangle = Triangle.createTriangle(List.of());
        assertEquals(Triangle.DEFAULT_X_POINTS[0], triangle.getXPoints()[0]);
        assertEquals(Triangle.DEFAULT_Y_POINTS[0], triangle.getYPoints()[0]);
        assertEquals(Triangle.DEFAULT_X_POINTS[1], triangle.getXPoints()[1]);
        assertEquals(Triangle.DEFAULT_Y_POINTS[1], triangle.getYPoints()[1]);
        assertEquals(Triangle.DEFAULT_X_POINTS[2], triangle.getXPoints()[2]);
        assertEquals(Triangle.DEFAULT_Y_POINTS[2], triangle.getYPoints()[2]);
    }

    @Test
    void createTriangle_withSevenParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)),
                "Exception was not thrown.");
    }

    @Test
    void createTriangle_withFiveParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)),
                "Exception was not thrown.");
    }

    @Test
    void createTriangle_withFourParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(Arrays.asList(1.0, 2.0, 3.0, 4.0)),
                "Exception was not thrown.");
    }

    @Test
    void createTriangle_withThreeParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(Arrays.asList(1.0, 2.0, 3.0)),
                "Exception was not thrown.");
    }

    @Test
    void createTriangle_withTwoParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(Arrays.asList(1.0, 2.0)),
                "Exception was not thrown.");
    }

    @Test
    void createTriangle_withOneParameters_throwsException() {
        assertThrows(InvalidParameterException.class, () -> Triangle.createTriangle(List.of(1.0)),
                "Exception was not thrown.");
    }
}