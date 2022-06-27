package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.ac.leedsBeckett.ase.model.Circle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CommandControllerTest {

    @Mock
    private Pane pane;

    @Autowired
    private CommandController commandController;

    @BeforeEach
    void setUp() {
        pane = new Pane(Circle.createCircle(List.of()));
    }

    @Test
    void test_execute_returnsCorrectString() {
        String result = commandController.execute("circle", pane);
        assertEquals("Command entered: circle", result);
    }
}