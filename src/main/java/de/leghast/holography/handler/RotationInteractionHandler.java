package de.leghast.holography.handler;

import de.leghast.holography.Holography;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.settings.AdjusterSettings;
import de.leghast.holography.settings.DimensionSettings;
import de.leghast.holography.ui.AnvilInputHelper;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.UserInterface;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RotationInteractionHandler {

    public RotationInteractionHandler(Holography main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings rotationSettings = settings.getRotationSettings();

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            case 27 -> settings.setPage(Page.ATTRIBUTES);
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> AnvilInputHelper.getCustomNumberInput(main, player, settings.getPage(), rotationSettings.getFactor());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
            case 26 -> {
                main.getClipboardManager().remove(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getClipboardManager().getWrapper(player.getUniqueId()).remove();
                main.getClipboardManager().remove(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }

    }
}
