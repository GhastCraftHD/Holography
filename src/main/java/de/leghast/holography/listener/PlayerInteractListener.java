package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Message;
import de.leghast.holography.handler.AdjusterInteractionHandler;
import de.leghast.holography.settings.AdjusterSettings;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.UserInterface;
import de.leghast.holography.ui.AnvilInputHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private final Holography main;

    public PlayerInteractListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Material material = ConfigManager.TOOL;

        if(material != player.getInventory().getItemInMainHand().getType()) return;
        if(!player.hasPermission(Holography.PERMISSION)) return;

        e.setCancelled(true);

        new AdjusterInteractionHandler(main, e.getAction(), player);

    }

}
