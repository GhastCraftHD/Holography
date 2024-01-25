package de.leghast.holography.manager;

import de.leghast.holography.instance.settings.AdjusterSettings;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class SettingsManager {

    private HashMap<UUID, AdjusterSettings> settings;

    public SettingsManager(){
        settings = new HashMap<>();
    }

    public boolean hasAdjusterSettings(UUID uuid){
        return settings.containsKey(uuid);
    }

    public AdjusterSettings getAdjusterSettings(UUID uuid){
        return settings.get(uuid);
    }

    public void addAdjusterSettings(UUID uuid){
        settings.put(uuid, new AdjusterSettings(Bukkit.getPlayer(uuid)));
    }

    public void removeAdjusterSettings(UUID uuid){
        if(hasAdjusterSettings(uuid)){
            settings.remove(uuid);
        }
    }



}
