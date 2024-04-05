package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.ui.InterfaceItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AlignmentItems {

    public static void getAlignmentItems(ItemStack[] content){

        content[30] = new InterfaceItem(Material.RED_STAINED_GLASS_PANE)
                .name(Component.text("Align left", NamedTextColor.RED));

        content[31] = new InterfaceItem(Material.WHITE_STAINED_GLASS_PANE)
                .name(Component.text("Align centered", NamedTextColor.GRAY));

        content[32] = new InterfaceItem(Material.LIME_STAINED_GLASS_PANE)
                .name(Component.text("Align right", NamedTextColor.GREEN));

    }

}
