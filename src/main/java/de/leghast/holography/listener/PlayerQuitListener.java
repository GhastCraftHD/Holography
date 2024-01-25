package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private Holography main;

    public PlayerQuitListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        main.getClipboardManager().remove(e.getPlayer().getUniqueId());
        main.getSettingsManager().removeAdjusterSettings(e.getPlayer().getUniqueId());
    }

}
