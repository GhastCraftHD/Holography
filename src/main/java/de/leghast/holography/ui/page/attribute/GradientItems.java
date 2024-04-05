package de.leghast.holography.ui.page.attribute;

import de.leghast.holography.constant.Colors;
import de.leghast.holography.settings.AttributeSettings;
import de.leghast.holography.ui.InterfaceItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GradientItems {

    public static void getGradientItems(ItemStack[] content, AttributeSettings settings){

        content[30] = new InterfaceItem(Material.PITCHER_POD)
                .name(Component.text("Set first color", Colors.ACCENT))
                .description(
                        Component.text("Current color: " + settings.getGradientFirst().asHexString(), NamedTextColor.GRAY)
                );

        content[31] = new InterfaceItem(Material.PITCHER_PLANT)
                .name(Component.text("Set second color", Colors.ACCENT))
                .description(
                        Component.text("Current color: " + settings.getGradientSecond().asHexString(), NamedTextColor.GRAY)
                );

        content[32] = new InterfaceItem(Material.TORCHFLOWER)
                .name(Component.text("Apply gradient", Colors.ACCENT));

    }

}
