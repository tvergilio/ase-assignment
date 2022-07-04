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
import uk.ac.leedsBeckett.ase.model.PencilColour;

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
        initPencil();
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
                message = programController.execute(programInput.getText(), canvas);
            }
            resultText.setText(resultText.getText() + "\n" + message);
            onClearButtonClick();
        }
    }

    private void initPencil() {
        Pencil pencil = Pencil.getInstance();
        pencil.setCenterX(Pencil.DEFAULT_X);
        pencil.setCenterY(Pencil.DEFAULT_Y);
        pencil.setFill(PencilColour.DEFAULT.getColor());
        pencil.setStroke(PencilColour.DEFAULT.getColor());
        pencil.setStrokeWidth(Pencil.DEFAULT_STROKE_WIDTH);
        pencil.setLayoutX(Pencil.DEFAULT_X);
        pencil.setLayoutY(Pencil.DEFAULT_Y);
        pencil.relocate(pencil.getLayoutX(), pencil.getLayoutY());
        canvas.getChildren().add(pencil);
    }

    private void clearPencil() {
        Pencil pencil = Pencil.getInstance();
        canvas.getChildren().remove(pencil);
    }

    @FXML
    protected void onClearButtonClick() {
        commandInput.setText("");
        programInput.setText("");
    }

    @FXML
    protected void onClearCanvasButtonClick() {
        canvas.getChildren().clear();
        resultText.setText("");
        clearPencil();
        initPencil();
    }
}