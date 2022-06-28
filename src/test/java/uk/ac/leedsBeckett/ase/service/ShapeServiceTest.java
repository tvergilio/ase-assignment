package uk.ac.leedsBeckett.ase.service;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.ac.leedsBeckett.ase.exceptions.ShapeNotSupportedException;
import uk.ac.leedsBeckett.ase.model.Circle;
import uk.ac.leedsBeckett.ase.model.Rectangle;
import uk.ac.leedsBeckett.ase.model.Triangle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ShapeServiceTest {

    @Mock
    private Circle circle;
    @Mock
    private Rectangle rectangle;
    @Mock
    private Triangle triangle;
    @Mock
    private ObservableList<Node> children;
    @Mock
    private Pane pane;
    @Autowired
    private ShapeService shapeService;

    @BeforeEach
    public void setUp() {
        Mockito.when(pane.getChildren()).thenReturn(children);
    }

    @Test
    void drawShape_withValidCircle_callsAppropriateMethodsInPane() {
        shapeService.drawShape(circle, pane);
        verify(circle, times(1)).relocate(circle.getLayoutX(), circle.getLayoutY());
        verify(pane, times(3)).getChildren();
        verify(children, times(1)).add(circle);
    }

    @Test
    void drawShape_withValidRectangle_callsAppropriateMethodsInPane() {
        shapeService.drawShape(rectangle, pane);
        verify(rectangle, times(1)).relocate(rectangle.getLayoutX(), rectangle.getLayoutY());
        verify(pane, times(3)).getChildren(); //add rectangle, add and remove pencil
        verify(children, times(1)).add(rectangle);
    }

    @Test
    void drawShape_withValidTriangle_callsAppropriateMethodsInPane() {
        shapeService.drawShape(triangle, pane);
        verify(triangle, times(1)).relocate(triangle.getLayoutX(), triangle.getLayoutY());
        verify(pane, times(3)).getChildren();
        verify(children, times(1)).add(triangle);
    }

    @Test
    void drawShape_withValidPentagon_throwsShapeNotSupportedException() {
        Polygon pentagon = new javafx.scene.shape.Polygon(10.0, 20.0, 25.0, 30.0, 35.0, 25.0, 20.0, 20.0, 25.0, 35.0);
        assertThrows(ShapeNotSupportedException.class, () -> shapeService.drawShape(pentagon, pane),
                "Exception was not thrown.");
        verify(pane, times(0)).getChildren();
        verify(children, times(0)).add(pentagon);
    }

    @Test
    void drawShape_withNullShape_throwsException() {
        assertThrows(Exception.class, () -> shapeService.drawShape(null, pane),
                "Exception was not thrown.");
    }

    @Test
    void drawShape_withValidShape_andNullPane_throwsException() {
        assertThrows(Exception.class, () -> shapeService.drawShape(circle, null),
                "Exception was not thrown.");
    }

}