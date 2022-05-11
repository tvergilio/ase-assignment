package uk.ac.leedsBeckett.ase.controller;

import org.springframework.stereotype.Component;

@Component
public class CommandController {

    public String execute(String input) {
        return "Command entered: " + input;
    }
}
