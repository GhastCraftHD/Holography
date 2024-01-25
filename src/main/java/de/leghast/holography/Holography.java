package de.leghast.holography;

import de.leghast.holography.command.HologramCommand;
import de.leghast.holography.listener.InventoryClickListener;
import de.leghast.holography.listener.PlayerInteractListener;
import de.leghast.holography.manager.ClipboardManager;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Holography extends JavaPlugin {

    private ClipboardManager clipboardManager;
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        clipboardManager = new ClipboardManager(this);
        settingsManager = new SettingsManager();
        ConfigManager.setupConfig(this);
        getCommand("hologram").setExecutor(new HologramCommand(this));
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
    }

    @Override
    public void onDisable(){
        this.saveConfig();
    }

    public ClipboardManager getClipboardManager(){
        return clipboardManager;
    }

    public SettingsManager getSettingsManager(){
        return settingsManager;
    }
}
