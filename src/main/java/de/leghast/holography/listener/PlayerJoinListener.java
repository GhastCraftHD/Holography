package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Holography main;

    public PlayerJoinListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        Bukkit.getScheduler().runTaskLaterAsynchronously(main,
                () -> {
                    if(player.isOp()){
                        if(main.isUpdateAvailable()){
                            player.sendMessage(Message.newVersionAvailable(main.getLatestReleaseVersion()));
                        }
                    }
                }, 10L
        );

    }

}
