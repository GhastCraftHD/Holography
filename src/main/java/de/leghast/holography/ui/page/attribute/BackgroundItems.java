package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.constant.Colors;
import de.leghast.holography.ui.InterfaceItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackgroundItems {

    public static void getBackgroundItems(ItemStack[] content){

        content[30] = new InterfaceItem(Material.ORANGE_DYE)
                .name(Component.text("Set background color", Colors.ACCENT));

        content[31] = new InterfaceItem(Material.GLASS_PANE)
                .name(Component.text("Toggle see-through", Colors.ACCENT));

        content[32] = new InterfaceItem(Material.WHITE_STAINED_GLASS_PANE)
                .name(Component.text("Reset the background", Colors.ACCENT));

    }

}
