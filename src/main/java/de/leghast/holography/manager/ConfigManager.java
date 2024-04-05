package de.leghast.holography.manager;

import de.leghast.holography.Holography;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Display;

public class ConfigManager {

    private static FileConfiguration config;

    public static boolean CHECK_FOR_UPDATE;
    public static Display.Billboard DEFAULT_BILLBOARD;
    public static double RADIUS;
    public static Material TOOL;

    public static void setupConfig(Holography main){
        ConfigManager.config = main.getConfig();
        main.saveDefaultConfig();
        Material material = Material.ECHO_SHARD;
        CHECK_FOR_UPDATE = getBool("check-for-update");
        DEFAULT_BILLBOARD = getBillboard("billboard");
        RADIUS = getDouble("radius");
        TOOL = getMaterial("tool");
    }
    private static String getString(String path){
        return config.getString(path);
    }

    private static boolean getBool(String path){
        return config.getBoolean(path);
    }

    private static int getInt(String path){
        return config.getInt(path);
    }

    private static double getDouble(String path){
        return config.getDouble(path);
    }

    private static Material getMaterial(String path){
        return Material.matchMaterial(getString(path));
    }

    private static Display.Billboard getBillboard(String path){
        return Display.Billboard.valueOf(getString(path));
    }

}
