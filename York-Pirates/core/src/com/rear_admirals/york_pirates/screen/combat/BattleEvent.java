package com.rear_admirals.york_pirates.screen.combat;

public enum BattleEvent {
    // Non event
    NONE,
    // Players Turn
    PLAYER_MOVE,
    // Enemy ships turn
    ENEMY_MOVE,
    // Player's previous move is charged (special attacks)
    PLAYER_DIES,
    // Enemy ship sunk
    ENEMY_DIES,
    // Players flee is successful
    PLAYER_FLEES,
    // Ends combat screen.
    SCENE_RETURN,
}
