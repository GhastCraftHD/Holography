package de.leghast.holography.ui.page.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackgroundItems {

    public static void getBackgroundItems(ItemStack[] content){

        ItemStack color = new ItemStack(Material.ORANGE_DYE);
        ItemMeta colorMeta = color.getItemMeta();
        colorMeta.setDisplayName("§eSet background color");
        color.setItemMeta(colorMeta);
        content[30] = color;

        ItemStack opacity = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta defaultMeta = opacity.getItemMeta();
        defaultMeta.setDisplayName("§eReset the background");
        opacity.setItemMeta(defaultMeta);
        content[32] = opacity;

    }

}
