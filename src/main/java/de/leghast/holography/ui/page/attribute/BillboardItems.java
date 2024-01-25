package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.ui.page.PageUtil;
import org.bukkit.Material;
import org.bukkit.entity.Display;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BillboardItems {

    public static void getBillboardItems(ItemStack[] content, Display.Billboard billboard){

        ItemStack vertical = new ItemStack(Material.CHAIN);
        ItemMeta verticalMeta = vertical.getItemMeta();
        verticalMeta.setDisplayName("§eSet vertical billboard");
        vertical.setItemMeta(verticalMeta);
        content[29] = vertical;
        if(billboard == Display.Billboard.VERTICAL){
            PageUtil.addGlint(vertical);
        }

        ItemStack horizontal = new ItemStack(Material.ORANGE_CARPET);
        ItemMeta horizontalMeta = horizontal.getItemMeta();
        horizontalMeta.setDisplayName("§eSet horizontal billboard");
        horizontal.setItemMeta(horizontalMeta);
        content[30] = horizontal;
        if(billboard == Display.Billboard.HORIZONTAL){
            PageUtil.addGlint(horizontal);
        }

        ItemStack center = new ItemStack(Material.ENDER_PEARL);
        ItemMeta centerMeta = center.getItemMeta();
        centerMeta.setDisplayName("§eSet center billboard");
        center.setItemMeta(centerMeta);
        content[32] = center;
        if(billboard == Display.Billboard.CENTER){
            PageUtil.addGlint(center);
        }

        ItemStack fixed = new ItemStack(Material.SHIELD);
        ItemMeta fixedMeta = fixed.getItemMeta();
        fixedMeta.setDisplayName("§eSet fixed billboard");
        fixed.setItemMeta(fixedMeta);
        content[33] = fixed;
        if(billboard == Display.Billboard.FIXED){
            PageUtil.addGlint(fixed);
        }

    }
}
