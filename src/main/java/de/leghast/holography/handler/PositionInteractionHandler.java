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

public class PositionInteractionHandler {

    public PositionInteractionHandler(Holography main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings positionSettings = settings.getPositionSettings();

        switch(slot){
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.ATTRIBUTES);
            case 15 -> AnvilInputHelper.getCustomNumberInput(main, player, settings.getPage(), positionSettings.getFactor());
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(5);
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
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
