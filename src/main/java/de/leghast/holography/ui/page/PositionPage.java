package de.leghast.holography.ui.page;

import de.leghast.holography.Holography;
import de.leghast.holography.ui.FrequentItems;
import de.leghast.holography.ui.Page;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PositionPage {

    public static ItemStack[] getPositionPage(Holography main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getAxis();

        FrequentItems.addPageSwitchItems(content, Page.POSITION);

        List<ItemStack> valueItems = FrequentItems.getValueItems(factor);

        int index = 11;
        for (ItemStack item : valueItems) {
            content[index++] = item;
        }

        FrequentItems.addAxisItems(content, axis);

        FrequentItems.addGeneralItems(content);

        return content;
    }

}
