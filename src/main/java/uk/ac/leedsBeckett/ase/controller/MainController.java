package uk.ac.leedsBeckett.ase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

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
    protected void onRunButtonClick(ActionEvent actionEvent) {
        configureCanvas();
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
        resultText.setText(message);
    }

    private void configureCanvas() {
        canvas.setWidth(graphicGridPane.getWidth());
        canvas.setHeight(graphicGridPane.getHeight());
    }

    @FXML
    protected void onClearButtonClick() {
        commandInput.setText("");
        programInput.setText("");
        resultText.setText("");
    }

}