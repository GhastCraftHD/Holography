package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.display.DisplayWrapper;
import de.leghast.holography.handler.AttributeInteractionHandler;
import de.leghast.holography.handler.PositionInteractionHandler;
import de.leghast.holography.handler.RotationInteractionHandler;
import de.leghast.holography.handler.SizeInteractionHandler;
import de.leghast.holography.settings.AdjusterSettings;
import de.leghast.holography.settings.AttributeSettings;
import de.leghast.holography.settings.DimensionSettings;
import de.leghast.holography.settings.FactorSettings;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.UserInterface;
import de.leghast.holography.ui.AnvilInputHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Axis;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final Holography main;

    public InventoryClickListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Component title = e.getView().title();
        int slot = e.getRawSlot();
        if (title.contains(Page.POSITION.getTitle())){
            new PositionInteractionHandler(main, slot, player);
            e.setCancelled(true);
        }else if(title.contains(Page.SIZE.getTitle())){
            new SizeInteractionHandler(main, slot, player);
            e.setCancelled(true);
        }else if(title.contains(Page.ROTATION.getTitle())){
            new RotationInteractionHandler(main, slot, player);
            e.setCancelled(true);
        }else if(title.contains(Page.ATTRIBUTES.getTitle())){
            new AttributeInteractionHandler(main, slot, player);
            e.setCancelled(true);
        }
    }

}
