package de.leghast.holography.handler;

import de.leghast.holography.Holography;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.settings.AdjusterSettings;
import de.leghast.holography.settings.FactorSettings;
import de.leghast.holography.ui.AnvilInputHelper;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.UserInterface;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SizeInteractionHandler {

    public SizeInteractionHandler(Holography main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        FactorSettings sizeSettings = settings.getSizeSettings();

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.TOOL));
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.ATTRIBUTES);
            case 20 -> sizeSettings.setFactor(0.25);
            case 21 -> sizeSettings.setFactor(0.5);
            case 22 -> sizeSettings.setFactor(1);
            case 23 -> sizeSettings.setFactor(5);
            case 24 -> AnvilInputHelper.getCustomNumberInput(main, player, settings.getPage(), sizeSettings.getFactor());
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
