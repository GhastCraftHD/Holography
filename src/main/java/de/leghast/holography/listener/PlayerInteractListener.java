package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.instance.settings.AdjusterSettings;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.UserInterface;
import de.leghast.holography.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private Holography main;

    public PlayerInteractListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Material material = ConfigManager.getAdjusterToolMaterial();

        if(material == player.getInventory().getItemInMainHand().getType() && player.hasPermission("holography.use")){
            if(!main.getSettingsManager().hasAdjusterSettings(player.getUniqueId())){
                main.getSettingsManager().addAdjusterSettings(player.getUniqueId());
            }
            e.setCancelled(true);
            if(main.getClipboardManager().hasClipboard(player.getUniqueId())){
                if(e.getAction().isLeftClick()){
                    AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                    switch (settings.getPage()) {
                        case POSITION -> main.getClipboardManager().getWrapper(player.getUniqueId()).move(
                                settings.getPositionSettings().getAxis(),
                                settings.getPositionSettings().getFactor()
                        );
                        case ROTATION -> main.getClipboardManager().getWrapper(player.getUniqueId()).rotate(
                                settings.getRotationSettings().getAxis(),
                                settings.getRotationSettings().getFactor()
                        );
                        case SIZE ->
                                main.getClipboardManager().getWrapper(player.getUniqueId()).scaleUp(settings.getSizeSettings().getFactor());
                    }
                }else if(e.getAction().isRightClick()){
                    if(player.isSneaking()){
                        new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
                    }else{
                        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                        switch (settings.getPage()){
                            case POSITION -> main.getClipboardManager().getWrapper(player.getUniqueId()).move(
                                    settings.getPositionSettings().getAxis(),
                                    -settings.getPositionSettings().getFactor()
                            );
                            case ROTATION -> main.getClipboardManager().getWrapper(player.getUniqueId()).rotate(
                                    settings.getRotationSettings().getAxis(),
                                    -settings.getRotationSettings().getFactor()
                            );
                            case SIZE -> main.getClipboardManager().getWrapper(player.getUniqueId()).scaleDown(settings.getSizeSettings().getFactor());
                        }
                    }
                }
            }else{
                player.sendMessage(Util.PREFIX + "§cYou have no Text Display selected");
            }
        }


    }

}
