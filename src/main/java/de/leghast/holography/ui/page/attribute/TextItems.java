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
        content[29] = text;

        ItemStack line = new ItemStack(Material.PAPER);
        ItemMeta lineMeta = line.getItemMeta();
        lineMeta.setDisplayName("§eSet the line width");
        line.setItemMeta(lineMeta);
        content[30] = line;

        ItemStack shadow = new ItemStack(Material.BLACKSTONE);
        ItemMeta shadowMeta = shadow.getItemMeta();
        shadowMeta.setDisplayName("§eToggle shadow");
        shadow.setItemMeta(shadowMeta);
        content[31] = shadow;

        ItemStack color = new ItemStack(Material.ORANGE_DYE);
        ItemMeta colorMeta = color.getItemMeta();
        colorMeta.setDisplayName("§eSet the text color");
        color.setItemMeta(colorMeta);
        content[32] = color;

        ItemStack opacity = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta opacityMeta = opacity.getItemMeta();
        opacityMeta.setDisplayName("§eSet text opacity");
        opacity.setItemMeta(opacityMeta);
        content[33] = opacity;

    }

}
