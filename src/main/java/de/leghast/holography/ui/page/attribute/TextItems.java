package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.constant.Colors;
import de.leghast.holography.ui.InterfaceItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TextItems {

    public static void getTextItems(ItemStack[] content){

        content[29] = new InterfaceItem(Material.NAME_TAG)
                .name(Component.text("Change the displayed text", Colors.ACCENT));

        content[30] = new InterfaceItem(Material.PAPER)
                .name(Component.text("Change the line width", Colors.ACCENT));

        content[31] = new InterfaceItem(Material.BLACKSTONE)
                .name(Component.text("Toggle shadow", Colors.ACCENT));

        content[32] = new InterfaceItem(Material.ORANGE_DYE)
                .name(Component.text("Change the text color", Colors.ACCENT));

        content[33] = new InterfaceItem(Material.WHITE_STAINED_GLASS_PANE)
                .name(Component.text("Change the text opacity", Colors.ACCENT));

    }

}
