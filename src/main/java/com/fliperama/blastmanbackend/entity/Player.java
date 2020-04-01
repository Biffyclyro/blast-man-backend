package com.fliperama.blastmanbackend.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Player {
    private boolean connected;
    private UUID playerId;
    private boolean alive;
    private double x;
    private double y;
}
