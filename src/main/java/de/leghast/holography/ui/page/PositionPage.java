package de.leghast.holography.ui.page;

import de.leghast.holography.Holography;
import de.leghast.holography.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PositionPage {

    public static ItemStack[] getPositionPage(Holography main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getAxis();

        PageUtil.addPageSwitchItems(content, Page.POSITION);

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§70.25 blocks");
        quarter.setItemMeta(quarterMeta);
        if(factor == 0.25){
            PageUtil.addGlint(quarter);
        }
        content[11] = quarter;

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§70.5 blocks");
        half.setItemMeta(halfMeta);
        if(factor == 0.5){
            PageUtil.addGlint(half);
        }
        content[12] = half;

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§71 block");
        full.setItemMeta(fullMeta);
        if(factor == 1){
            PageUtil.addGlint(full);
        }
        content[13] = full;

        ItemStack tenBlocks = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta tenBlocksMeta = tenBlocks.getItemMeta();
        tenBlocksMeta.setDisplayName("§710 blocks");
        tenBlocks.setItemMeta(tenBlocksMeta);
        if(factor == 10){
            PageUtil.addGlint(tenBlocks);
        }
        content[14] = tenBlocks;

        ItemStack custom = new ItemStack(Material.PAPER);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.setDisplayName("§7Custom factor §e(" + factor + " block" + (factor == 1 ? "" : "s") + ")");
        custom.setItemMeta(customMeta);
        if(factor != 0.25 && factor != 0.5 && factor != 1 && factor != 10){
            PageUtil.addGlint(custom);
        }
        content[15] = custom;

        PageUtil.addAxisItems(content, axis);

        PageUtil.addGeneralItems(content);

        return content;
    }

}
