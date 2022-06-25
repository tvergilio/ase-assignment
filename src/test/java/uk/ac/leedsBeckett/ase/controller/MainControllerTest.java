package uk.ac.leedsBeckett.ase.controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(ApplicationExtension.class)
@SpringBootTest
class MainControllerTest {

    @Mock
    private CommandController commandController;
    @Mock
    private ProgramController programController;
    @Mock
    private MouseEvent mouseEvent;

    private Label coordinates;

    @Autowired
    private MainController mainController;

    @Start
    protected void start(Stage stage) {
        coordinates = new Label();

        stage.setScene(new Scene(new Pane(coordinates)));
        stage.show();
    }

    @BeforeEach
    void setUp() {
        mainController.coordinates = coordinates;
        when(mouseEvent.getX()).thenReturn(200.0);
        when(mouseEvent.getY()).thenReturn(100.0);
    }

    @Test
    void test_showCoordinates() {
        Platform.runLater(() -> { //Needs multithreading as JavaFx only allows Fx application threads to modify the UI
            mainController.showCoordinates(mouseEvent);
            assertEquals("x = 200, y = 100", mainController.coordinates.getText());
        });
    }
}