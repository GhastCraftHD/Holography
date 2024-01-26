package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Holography main;

    public PlayerJoinListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(player.isOp()){
            if(main.isUpdateAvailable()){
                player.sendMessage(Util.PREFIX + "§aA new version of Holography is available: §e" + main.getLatestReleaseVersion());
                player.sendMessage(Util.PREFIX + "§aGet it at: §ehttps://github.com/LeGhast/Holography/releases");
            }
        }
    }

}
