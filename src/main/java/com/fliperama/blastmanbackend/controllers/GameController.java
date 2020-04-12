package com.fliperama.blastmanbackend.controllers;

import com.fliperama.blastmanbackend.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class GameController {

    private static final Map<UUID, Player> playerMap = new HashMap<>();


    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/connect")
    public Player connectPlayer(@RequestBody Player player) {


        player.setPlayerId(UUID.randomUUID());
        playerMap.put(player.getPlayerId(), player);

        template.convertAndSend("/topic/notification/connect", player);
        return player;

    }


    @PostMapping("/move")
    public void movePlayer(@RequestBody Player player) {
        if (playerMap.containsKey(player.getPlayerId())) {

            /*template.convertAndSend("/topic/notification/connect", player);*/
        }
    }

    @MessageMapping("/test") // endpoint where the client will send messages or events
    @SendTo("/topic/notification/move")
    public String testar(@Payload String teste) {
        System.out.println(teste);
        return teste;
    }

    @MessageMapping("/bomb") // endpoint where the client will send messages or events
    @SendTo("/topic/notification/bomb")
    public String setBomb(@Payload String bomb) {
        System.out.println(bomb);
        return bomb;
    }


}
