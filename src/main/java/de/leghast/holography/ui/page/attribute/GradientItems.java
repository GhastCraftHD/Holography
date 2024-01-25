package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.instance.settings.AttributeSettings;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GradientItems {

    public static void getGradientItems(ItemStack[] content, AttributeSettings settings){

        ItemStack first = new ItemStack(Material.PITCHER_POD);
        ItemMeta firstMeta = first.getItemMeta();
        firstMeta.setDisplayName("§eSet first color");
        first.setItemMeta(firstMeta);
        content[30] = first;

        ItemStack second = new ItemStack(Material.PITCHER_PLANT);
        ItemMeta secondMeta = second.getItemMeta();
        secondMeta.setDisplayName("§eSet second color");
        second.setItemMeta(secondMeta);
        content[31] = second;

        ItemStack apply = new ItemStack(Material.TORCHFLOWER);
        ItemMeta applyMeta = apply.getItemMeta();
        applyMeta.setDisplayName("§eApply gradient");
        apply.setItemMeta(applyMeta);
        content[32] = apply;

    }

}
