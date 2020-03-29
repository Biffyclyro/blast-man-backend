package com.fliperama.blastmanbackend.controllers;

import com.fliperama.blastmanbackend.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GameController {

    private Player player1;
    private boolean logado = false;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/connect")
    public void connectPlayer(@RequestBody Player player){
        this.player1 = player;
        this.logado = true;
        template.convertAndSend("/topic/notification", true);
    }



    @PostMapping("/move")
    public void movePlayer(@RequestBody Player player){
        this.player1 = player;
       /* template.convertAndSend("/topic/notification", player);*/
    }
}
