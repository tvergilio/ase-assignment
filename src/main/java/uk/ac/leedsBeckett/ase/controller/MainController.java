package uk.ac.leedsBeckett.ase.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.model.Pencil;

@Component
@FxmlView
public class MainController {

    //Dependency Injection
    private final CommandController commandController;
    private final ProgramController programController;

    @FXML
    public Pane canvas;
    @FXML
    public Label coordinates;
    @FXML
    private Text resultText;
    @FXML
    private TextField commandInput;
    @FXML
    private TextArea programInput;

    public MainController(CommandController commandController, ProgramController programController) {
        this.commandController = commandController;
        this.programController = programController;
    }

    @FXML
    public void initialize() {
//        drawPencil();
    }

    @FXML
    protected void showCoordinates(MouseEvent mouseEvent) {
        coordinates.setText("x = " + Math.round(mouseEvent.getX()) + ", y = " + Math.round(mouseEvent.getY()));
    }

    @FXML
    protected void hideCoordinates(MouseEvent mouseEvent) {
        coordinates.setText("");
    }

    @FXML
    public void configureKeys(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onRunButtonClick(keyEvent);
        }
    }

    @FXML
    protected void onRunButtonClick(Event event) {
        String message = "";
        boolean bothPopulated = !commandInput.getText().isEmpty() && !programInput.getText().isEmpty();
        boolean commandPopulated = !commandInput.getText().isEmpty();
        boolean programPopulated = !programInput.getText().isEmpty();
        if (bothPopulated) {
            message = "You can only run a command or a program, not both.";
        } else if (commandPopulated) {
            message = commandController.execute(commandInput.getText(), canvas);
        } else if (programPopulated) {
            message = programController.execute(programInput.getText());
        }
        resultText.setText(resultText.getText() + "\n" + message);
//        clearPreviousPencil();
//        drawPencil();
    }

    private void clearPreviousPencil() {
        canvas.getChildren().remove(Pencil.getInstance());
    }

    private void drawPencil() {
        Pencil pencil = Pencil.getInstance();
        pencil.setFill(pencil.getPencilColour().getColor());
        canvas.relocate(pencil.getCenterX(), pencil.getCenterY());
        canvas.getChildren().add(pencil);
    }

    @FXML
    protected void onClearButtonClick() {
        commandInput.setText("");
        programInput.setText("");
        resultText.setText("");
    }

    @FXML
    protected void onClearCanvasButtonClick() {
        canvas.getChildren().clear();
        resultText.setText("");
    }
}