package uk.ac.leedsBeckett.ase;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.leedsBeckett.ase.application.JavaFxApplication;

@SpringBootApplication
public class AseAssignmentApplication {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }

}
