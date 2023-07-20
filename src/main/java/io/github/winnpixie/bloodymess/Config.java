package io.github.winnpixie.bloodymess;

import io.github.winnpixie.hukkit.configs.Link;

public class Config {
    @Link(path = "triggers.player.attacked-by-player")
    public static boolean PLAYER_ATTACKED_BY_PLAYER;

    @Link(path = "triggers.player.attacked-by-entity")
    public static boolean PLAYER_ATTACKED_BY_MOB;

    @Link(path = "triggers.player.falling")
    public static boolean PLAYER_FALLING;

    @Link(path = "triggers.player.environment")
    public static boolean PLAYER_ENVIRONMENT;

    @Link(path = "triggers.entity.attacked-by-player")
    public static boolean ENTITY_ATTACKED_BY_PLAYER;

    @Link(path = "triggers.entity.attacked-by-entity")
    public static boolean ENTITY_ATTACKED_BY_ENTITY;

    @Link(path = "triggers.entity.falling")
    public static boolean ENTITY_FALLING;

    @Link(path = "triggers.entity.environment")
    public static boolean ENTITY_ENVIRONMENT;

    @Link(path = "particles.count")
    public static int PARTICLE_COUNT;

    @Link(path = "particles.x-spread")
    public static double SPREAD_X;

    @Link(path = "particles.y-spread")
    public static double SPREAD_Y;

    @Link(path = "particles.z-spread")
    public static double SPREAD_Z;
}
