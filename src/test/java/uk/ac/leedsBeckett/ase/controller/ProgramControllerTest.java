package uk.ac.leedsBeckett.ase.controller;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.ac.leedsBeckett.ase.model.Circle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ProgramControllerTest {

    @Mock
    private Pane pane;

    @Mock
    private CommandController commandController;

    @Autowired
    private ProgramController programController;

    @BeforeEach
    void setUp() {
        pane = new Pane(Circle.createCircle(List.of()));
    }

    @Test
    void test_execute_withOneCommand_returnsCorrectString() {
        String result = programController.execute("circle", pane);
        assertEquals("Command entered: circle", result);
    }

    @Test
    void test_execute_withTwoCommands_returnsCorrectString() {
        String result = programController.execute("circle\\\nsquare", pane);
        assertEquals("Command entered: circle\\\nCommand entered: square", result);
    }

    @Test
    void test_execute_withThreeCommands_returnsCorrectString() {
        String result = programController.execute("circle\\\nsquare 100 100 blue fill\\\nline 100 100 200 100 pink", pane);
        assertEquals("Command entered: circle" +
                "\\\nCommand entered: square 100 100 blue fill" +
                "\\\nCommand entered: line 100 100 200 100 pink", result);
    }

    @Test
    void test_execute_withNoCommands_returnsEmptyString() {
        String result = programController.execute("", pane);
        assertEquals("", result);
    }

    @Test
    void test_execute_withOneCommand_delegatesToCommandController_once() {
        programController = new ProgramController(commandController);
        when(commandController.execute(any(), any())).thenReturn("circle yellow fill");
        programController.execute("circle yellow fill", pane);
        verify(commandController, times(1)).execute(any(), any());
    }

    @Test
    void test_execute_withThreeCommands_delegatestoCommandController_threeTimes() {
        programController = new ProgramController(commandController);
        when(commandController.execute(any(), any())).thenReturn("circle")
                .thenReturn("square 100 100 blue fill")
                .thenReturn("line 100 100 200 100 pink");
        programController.execute("circle\\\nsquare 100 100 blue fill\\\nline 100 100 200 100 pink", pane);
        verify(commandController, times(3)).execute(any(), any());
    }

    @Test
    void test_execute_withOneCommand_andTrailingCarriageReturn_delegatesToCommandController_once() {
        programController = new ProgramController(commandController);
        when(commandController.execute(any(), any())).thenReturn("circle yellow fill\\\n");
        programController.execute("circle yellow fill", pane);
        verify(commandController, times(1)).execute(any(), any());
    }
}