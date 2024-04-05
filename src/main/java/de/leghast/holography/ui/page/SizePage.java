package de.leghast.holography.ui.page;

import de.leghast.holography.Holography;
import de.leghast.holography.ui.FrequentItems;
import de.leghast.holography.ui.Page;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SizePage {

    public static ItemStack[] getSizePage(Holography main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getSizeSettings().getFactor();

        FrequentItems.addPageSwitchItems(content, Page.SIZE);

        List<ItemStack> valueItems = FrequentItems.getValueItems(factor);

        int index = 20;
        for (ItemStack item : valueItems) {
            content[index++] = item;
        }

        FrequentItems.addGeneralItems(content);

        return content;
    }

}
