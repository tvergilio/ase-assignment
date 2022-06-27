package uk.ac.leedsBeckett.ase.controller;

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
    protected Pane canvas;
    @FXML
    protected Label coordinates;
    @FXML
    protected Text resultText;
    @FXML
    protected TextField commandInput;
    @FXML
    protected TextArea programInput;

    public MainController(CommandController commandController, ProgramController programController) {
        this.commandController = commandController;
        this.programController = programController;
    }

    @FXML
    public void initialize() {
        showPencil();
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
            onRunButtonClick();
        }
    }

    @FXML
    protected void onRunButtonClick() {
        boolean commandPopulated = commandInput != null && !commandInput.getText().isEmpty();
        boolean programPopulated = programInput != null && !programInput.getText().isEmpty();
        if (commandPopulated || programPopulated) {
            String message;
            if (commandPopulated && programPopulated) {
                message = "You can only run a command or a program, not both.";
            } else if (commandPopulated) {
                message = commandController.execute(commandInput.getText(), canvas);
            } else {
                message = programController.execute(programInput.getText());
            }
            resultText.setText(resultText.getText() + "\n" + message);
        }
    }

    private void showPencil() {
        Pencil pencil = Pencil.getInstance();
        pencil.setFill(pencil.getPencilColour().getColor());
        pencil.relocate(pencil.getLayoutX(), pencil.getLayoutY());
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