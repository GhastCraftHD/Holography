package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.constant.Colors;
import de.leghast.holography.ui.InterfaceItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Display;
import org.bukkit.inventory.ItemStack;

public class BillboardItems {

    public static void getBillboardItems(ItemStack[] content, Display.Billboard billboard){

        content[29] = new InterfaceItem(Material.CHAIN)
                .name(Component.text("Set vertical billboard", Colors.ACCENT))
                .glow(() -> billboard == Display.Billboard.VERTICAL);

        content[30] = new InterfaceItem(Material.ORANGE_CARPET)
                .name(Component.text("Set horizontal billboard", Colors.ACCENT))
                .glow(() -> billboard == Display.Billboard.HORIZONTAL);

        content[32] = new InterfaceItem(Material.ENDER_PEARL)
                .name(Component.text("Set center billboard", Colors.ACCENT))
                .glow(() -> billboard == Display.Billboard.CENTER);

        content[33] = new InterfaceItem(Material.SHIELD)
                .name(Component.text("Set fixed billboard", Colors.ACCENT))
                .glow(() -> billboard == Display.Billboard.FIXED);
    }
}
