package uk.ac.leedsBeckett.ase.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import uk.ac.leedsBeckett.ase.ParsingUtils;
import uk.ac.leedsBeckett.ase.service.CommandParserService;
import uk.ac.leedsBeckett.ase.service.ShapeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
@SpringBootTest
class MainControllerTest {

    @Mock
    private CommandController commandController = new CommandController(new CommandParserService(new ParsingUtils()), new ShapeService());
    @Mock
    private ProgramController programController = new ProgramController();
    @Mock
    private MouseEvent mouseEvent;
    @Mock
    private KeyEvent keyEvent;

    private TextField commandTextField;
    private TextArea programTextArea;
    private Label coordinates;
    private Text resultText;

    @Spy // need to verify methods within the same class were called
    private MainController mainController = new MainController(commandController, programController);

    @Start
    protected void start(Stage stage) {
        coordinates = new Label();
        commandTextField = new TextField();
        programTextArea = new TextArea();
        resultText = new Text("");
        Pane canvas = new Pane(coordinates, commandTextField, programTextArea, resultText);
        stage.setScene(new Scene(canvas));
        stage.show();
    }

    @BeforeEach
    void setUp() {
        mainController.coordinates = coordinates;
    }

    @Test
    void test_showCoordinates_displaysCoordinatesFromMouseEvent() {
        when(mouseEvent.getX()).thenReturn(200.0);
        when(mouseEvent.getY()).thenReturn(100.0);

        Platform.runLater(() -> { //Needs multithreading as JavaFx only allows Fx application threads to modify the UI
            mainController.showCoordinates(mouseEvent);
            assertEquals("x = 200, y = 100", mainController.coordinates.getText());
        });
    }

    @Test
    void test_hideCoordinates_displaysBlankText() {
        Platform.runLater(() -> {
            mainController.hideCoordinates(mouseEvent);
            assertEquals("", mainController.coordinates.getText());
        });
    }

    @Test
    void test_configureKeys_processesButtonClick_whenEnterKeyPressed() {
        Platform.runLater(() -> {
            when(keyEvent.getCode()).thenReturn(KeyCode.ENTER);
            mainController.configureKeys(keyEvent);
            verify(mainController, times(1)).onRunButtonClick();
        });
    }

    @Test
    void test_configureKeys_doesNothing_whenKeyOtherThanEnterPressed() {
        Platform.runLater(() -> {
            when(keyEvent.getCode()).thenReturn(KeyCode.ALT);
            mainController.configureKeys(keyEvent);
            verify(mainController, times(0)).onRunButtonClick();
        });
    }

    @Test
    void test_onButtonClick_showsError_whenBothCommandAndProgramPopulated() {
        Platform.runLater(() -> {
            commandTextField.setText("circle");
            programTextArea.setText("circle");
            mainController.commandInput = commandTextField;
            mainController.programInput = programTextArea;
            mainController.onRunButtonClick();
            verify(commandController, times(0)).execute(any(), any());
            verify(programController, times(0)).execute(any());
            assertEquals("You can only run a command or a program, not both.", resultText.getText());
        });
    }

    @Test
    void test_onRunButtonClick_processesInput_whenValidCommandEntered() {
        KeyFrame onlyCommandPopulated = new KeyFrame(Duration.seconds(0), e -> {
            commandTextField.setText("circle");
            mainController.commandInput = commandTextField;
            mainController.onRunButtonClick();
            verify(commandController, times(1)).execute("circle", any());
            assertEquals("Command entered: circle", resultText.getText());
        });

        Timeline timeline = new Timeline(onlyCommandPopulated);
        Platform.runLater(timeline::play);
    }

    @Test
    void test_onRunButtonClick_processesInput_whenValidProgramEntered() {
        KeyFrame onlyProgramPopulated = new KeyFrame(Duration.seconds(0), e -> {
            programTextArea.setText("circle");
            mainController.programInput = programTextArea;
            mainController.onRunButtonClick();
            verify(programController, times(1)).execute("circle");
            assertEquals("Program entered: circle", resultText.getText());
        });

        Timeline timeline = new Timeline(onlyProgramPopulated);
        Platform.runLater(timeline::play);
    }
}
