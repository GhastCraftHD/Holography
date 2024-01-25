package de.leghast.holography.manager;

import de.leghast.holography.Holography;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Display;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(Holography main){
        ConfigManager.config = main.getConfig();
    }

    public static Display.Billboard getDefaultBillboard(){
        return Display.Billboard.valueOf(config.getString("billboard"));
    }

    public static double getRadius(){
        return config.getDouble("radius");
    }

    public static Material getAdjusterToolMaterial(){
        return Material.matchMaterial(config.getString("tool"));
    }

}
