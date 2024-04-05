package de.leghast.holography.manager;

import de.leghast.holography.settings.AdjusterSettings;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class SettingsManager {

    private HashMap<UUID, AdjusterSettings> settings;

    public SettingsManager(){
        settings = new HashMap<>();
    }

    public AdjusterSettings getAdjusterSettings(UUID uuid){
        if(!settings.containsKey(uuid)) addAdjusterSettings(uuid);
        return settings.get(uuid);
    }

    public void addAdjusterSettings(UUID uuid){
        settings.put(uuid, new AdjusterSettings(Bukkit.getPlayer(uuid)));
    }

    public void removeAdjusterSettings(UUID uuid){
            settings.remove(uuid);
    }



}
