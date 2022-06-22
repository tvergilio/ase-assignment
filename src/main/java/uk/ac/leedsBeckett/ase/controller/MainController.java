package uk.ac.leedsBeckett.ase.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    public Canvas canvas;
    @FXML
    public GridPane graphicGridPane;
    @FXML
    public Label coordinates;
    @FXML
    private Text resultText;
    @FXML
    private TextField commandInput;
    @FXML
    private TextArea programInput;

    private final Pencil pencil;

    public MainController(CommandController commandController, ProgramController programController, Pencil pencil) {
        this.commandController = commandController;
        this.programController = programController;
        this.pencil = pencil;
    }

    public Pencil getPencil() {
        return pencil;
    }

    @FXML
    public void initialize() {
        configureCanvas();
        drawPencil();
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
        configureCanvas();
        drawPencil();
        String message = "";
        boolean bothPopulated = !commandInput.getText().isEmpty() && !programInput.getText().isEmpty();
        boolean commandPopulated = !commandInput.getText().isEmpty();
        boolean programPopulated = !programInput.getText().isEmpty();
        if (bothPopulated) {
            message = "You can only run a command or a program, not both.";
        } else if (commandPopulated) {
            message = commandController.execute(commandInput.getText(), canvas.getGraphicsContext2D());
        } else if (programPopulated) {
            message = programController.execute(programInput.getText());
        }
        resultText.setText(resultText.getText() + "\n" + message);
    }

    private void configureCanvas() {
        canvas.setWidth(graphicGridPane.getWidth());
        canvas.setHeight(graphicGridPane.getHeight());
    }

    private void drawPencil() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(pencil.getPencilColor());
        graphicsContext.setLineWidth(pencil.getPencilWidth());
        graphicsContext.beginPath();
        graphicsContext.arc(pencil.getCenterX(), pencil.getCenterY(), pencil.getRadius(), pencil.getRadius(), 0, 360);
        graphicsContext.stroke();
    }

    @FXML
    protected void onClearButtonClick() {
        commandInput.setText("");
        programInput.setText("");
        resultText.setText("");
    }

    @FXML
    protected void onClearCanvasButtonClick() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight());
        graphicsContext.beginPath();
        resultText.setText("");
    }
}