package com.fliperama.blastmanbackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GameController {

    @PostMapping("/move")
    public void movePlayer(){
        System.out.println("foi");
    }
}
