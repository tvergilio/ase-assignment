package uk.ac.leedsBeckett.ase.application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uk.ac.leedsBeckett.ase.controller.MainController;

/**
 * @author <a href="mailto:rene.gielen@gmail.com">Rene Gielen</a>
 */
@Component
public class PrimaryStageInitialiser implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;

    @Autowired
    public PrimaryStageInitialiser(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        stage.setTitle("ASE Assignment");
        Scene scene = new Scene(fxWeaver.loadView(MainController.class, "/view/main-view.fxml"), 970, 500);
        stage.setScene(scene);
        stage.show();
    }
}