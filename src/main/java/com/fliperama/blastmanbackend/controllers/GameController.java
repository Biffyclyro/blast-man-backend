package com.fliperama.blastmanbackend.controllers;

import com.fliperama.blastmanbackend.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class GameController {

    private static final Map<UUID, Player> playerMap = new HashMap<>();
    private static int contador = 0;


    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/connect")
    public Object connectPlayer() {


        if(playerMap.size() < 2) {

            var p = new Player(false, UUID.randomUUID(), true);

            playerMap.put(p.getPlayerId(), p);

            if(playerMap.size() == 1){
                p.setX(190);
                p.setY(48);
                p.setSkin("cop");
            }else {
                p.setX(1148);
                p.setY(554);
                p.setSkin("rob");
            }

            return p.getPlayerId().toString();
        }else {
            return "Sala cheia";
        }

    }

    @GetMapping("/reset")
    public void Reset(){
        contador = 0;
        playerMap.clear();
        System.out.println("Servidor resetado");
    }

    @MessageMapping("/test") // endpoint where the client will send messages or events
    @SendTo("/topic/notification/move")
    public String testar(@Payload String teste) {
        return teste;
    }


    @MessageMapping("/bomb")
    @SendTo("/topic/notification/bomb")
    public String setBomb(@Payload String bomb) {
        return bomb;
    }


    @MessageMapping("/explosion")
    @SendTo("/topic/notification/explosion")
    public String explode(@Payload String bomb) {

        System.out.println(bomb);
        return bomb;
    }

    @MessageMapping("/waiting")
    @SendTo("/topic/notification/waiting")
    public Object waiting(@Payload String playerId) {

        var p = playerMap.get(UUID.fromString(playerId) );
        System.out.println(playerMap.keySet().toString());

        if(!p.isConnected()){

            p.setConnected(true);
            contador++;
        }

        if (contador == 2) return playerMap.values();
        else return "wait";
    }


}
