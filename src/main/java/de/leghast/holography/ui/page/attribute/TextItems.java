package de.leghast.holography.ui.page.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TextItems {

    public static void getTextItems(ItemStack[] content){

        ItemStack text = new ItemStack(Material.NAME_TAG);
        ItemMeta textMeta = text.getItemMeta();
        textMeta.setDisplayName("§eChange the displayed text");
        text.setItemMeta(textMeta);
        content[30] = text;

        ItemStack opacity = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta opacityMeta = opacity.getItemMeta();
        opacityMeta.setDisplayName("§eSet text opacity");
        opacity.setItemMeta(opacityMeta);
        content[32] = opacity;

    }

}
