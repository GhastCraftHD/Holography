package de.leghast.holography.ui;

import de.leghast.holography.constant.Colors;
import de.leghast.holography.manager.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class FrequentItems {

    public static void addGlint(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
    }

    public static void addPageSwitchItems(ItemStack[] content, Page page){

        content[0] = new InterfaceItem(Material.MAGENTA_GLAZED_TERRACOTTA)
                .name(Component.text("Position", Colors.ACCENT))
                .description(
                        Component.text("Adjust the position", NamedTextColor.GRAY),
                        Component.text("of the text display", NamedTextColor.GRAY)
                )
                .glow(() -> page == Page.POSITION);

        content[9] = new InterfaceItem(Material.PUFFERFISH)
                .name(Component.text("Size", Colors.ACCENT))
                .description(
                        Component.text("Adjust the size", NamedTextColor.GRAY),
                        Component.text("of the text display", NamedTextColor.GRAY)
                )
                .glow(() -> page == Page.SIZE);

        content[18] = new InterfaceItem(Material.ITEM_FRAME)
                .name(Component.text("Rotation", Colors.ACCENT))
                .description(
                        Component.text("Adjust the rotation", NamedTextColor.GRAY),
                        Component.text("of the text display", NamedTextColor.GRAY)
                )
                .glow(() -> page == Page.ROTATION);

        content[27] = new InterfaceItem(Material.OAK_HANGING_SIGN)
                .name(Component.text("Attributes", Colors.ACCENT))
                .description(
                        Component.text("Adjust the attributes", NamedTextColor.GRAY),
                        Component.text("of the text display", NamedTextColor.GRAY)
                )
                .glow(() -> page == Page.ATTRIBUTES);
    }

    public static void addGeneralItems(ItemStack[] content){

        content[8] = new InterfaceItem(ConfigManager.TOOL)
                .name(Component.text("Adjuster tool", NamedTextColor.GOLD))
                .description(
                        Component.text("The selected adjusting", NamedTextColor.GRAY),
                        Component.text("settings are bound to this item", NamedTextColor.GRAY)
                );

        content[26] = new InterfaceItem(Material.STRUCTURE_VOID)
                .name(Component.text("Deselect Text Display", Colors.ERROR));

        content[44] = new InterfaceItem(Material.BARRIER)
                .name(Component.text("Delete Text Display", Colors.ERROR));

        ItemStack filler = new InterfaceItem(Material.GRAY_STAINED_GLASS_PANE)
                .name(Component.empty());

        for(int i = 0; i < content.length; i++){
            if(content[i] == null){
                content[i] = filler;
            }
        }
    }

    public static List<ItemStack> getValueItems(double factor){
        return List.of(
            new InterfaceItem(Material.COAL)
                    .name(Component.text("0.25 blocks", NamedTextColor.GRAY))
                    .glow(() -> factor == 0.25),

            new InterfaceItem(Material.IRON_INGOT)
                    .name(Component.text("0.5 blocks", NamedTextColor.GRAY))
                    .glow(() -> factor == 0.25),

            new InterfaceItem(Material.DIAMOND)
                    .name(Component.text("1 block", NamedTextColor.GRAY))
                    .glow(() -> factor == 1),

            new InterfaceItem(Material.GRASS_BLOCK)
                    .name(Component.text("5 blocks"))
                    .glow(() -> factor == 5),

            new InterfaceItem(Material.PAPER)
                    .name(Component.text("Custom factor ", NamedTextColor.GRAY)
                            .append(Component.text("(" + factor + " block" + (factor == 1 ? "" : "s") + ")", Colors.ACCENT)))
                    .glow(() -> factor != 0.25 && factor != 0.5 && factor != 1 && factor != 5)
        );
    }

    public static void addAxisItems(ItemStack[] content, Axis axis) {

        content[30] = new InterfaceItem(Material.RED_STAINED_GLASS_PANE)
                .name(Component.text("X-Axis", NamedTextColor.RED))
                .glow(() -> axis == Axis.X);

        content[31] = new InterfaceItem(Material.LIME_STAINED_GLASS_PANE)
                .name(Component.text("Y-Axis", NamedTextColor.GREEN))
                .glow(() -> axis == Axis.Y);

        content[32] = new InterfaceItem(Material.BLUE_STAINED_GLASS_PANE)
                .name(Component.text("Z-Axis", NamedTextColor.BLUE))
                .glow(() -> axis == Axis.Z);
    }

}
