package de.leghast.holography.ui.page.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AlignmentItems {

    public static void getAlignmentItems(ItemStack[] content){

        ItemStack left = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta leftMeta = left.getItemMeta();
        leftMeta.setDisplayName("§cAlign left");
        left.setItemMeta(leftMeta);
        content[30] = left;

        ItemStack center = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta centerMeta = center.getItemMeta();
        centerMeta.setDisplayName("§7Align centered");
        center.setItemMeta(centerMeta);
        content[31] = center;

        ItemStack right = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta rightMeta = right.getItemMeta();
        rightMeta.setDisplayName("§aAlign right");
        right.setItemMeta(rightMeta);
        content[32] = right;

    }

}
