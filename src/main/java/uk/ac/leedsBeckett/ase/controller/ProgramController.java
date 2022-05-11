package uk.ac.leedsBeckett.ase.controller;

import org.springframework.stereotype.Component;

@Component
public class ProgramController {

    public String execute(String input) {
        return "Program entered: \n" + input;
    }
}