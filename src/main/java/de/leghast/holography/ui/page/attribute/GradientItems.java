package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.instance.settings.AttributeSettings;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GradientItems {

    public static void getGradientItems(ItemStack[] content, AttributeSettings settings){

        ItemStack first = new ItemStack(Material.PITCHER_POD);
        ItemMeta firstMeta = first.getItemMeta();
        firstMeta.setDisplayName("§eSet first color");
        List<String> firstLore = new ArrayList<>();
        firstLore.add("§7Current color: " + settings.getFirst() + "#" + Integer.toHexString(settings.getFirst().getColor().getRGB()).substring(2));
        firstMeta.setLore(firstLore);
        first.setItemMeta(firstMeta);
        content[30] = first;

        ItemStack second = new ItemStack(Material.PITCHER_PLANT);
        ItemMeta secondMeta = second.getItemMeta();
        secondMeta.setDisplayName("§eSet second color");
        List<String> secondLore = new ArrayList<>();
        secondLore.add("§7Current color: " + settings.getSecond() + "#" + Integer.toHexString(settings.getSecond().getColor().getRGB()).substring(2));
        secondMeta.setLore(secondLore);
        second.setItemMeta(secondMeta);
        content[31] = second;

        ItemStack apply = new ItemStack(Material.TORCHFLOWER);
        ItemMeta applyMeta = apply.getItemMeta();
        applyMeta.setDisplayName("§eApply gradient");
        apply.setItemMeta(applyMeta);
        content[32] = apply;

    }

}
