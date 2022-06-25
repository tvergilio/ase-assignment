package uk.ac.leedsBeckett.ase.application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.controller.MainController;

@Component
public class PrimaryStageInitialiser implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;

    public PrimaryStageInitialiser(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        stage.setTitle("ASE Assignment");
        Scene scene = new Scene(fxWeaver.loadView(MainController.class, "/view/main-view.fxml"), 970, 500);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/static/brush.png"));
        stage.show();
    }
}