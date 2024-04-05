package de.leghast.holography.ui.page;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Colors;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.ui.FrequentItems;
import de.leghast.holography.ui.InterfaceItem;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.page.attribute.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AttributesPage {

    public static ItemStack[] getAttributesPage(Holography main, Player player){
        ItemStack[] content = new ItemStack[45];

        Attribute attribute = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings().getAttribute();

        FrequentItems.addPageSwitchItems(content, Page.ATTRIBUTES);

        content[11] = new InterfaceItem(Material.FILLED_MAP)
                .name(Component.text("Text", Colors.ACCENT))
                .description(
                        Component.text("Adjust text attributes", NamedTextColor.GRAY)
                )
                .glow(() -> attribute == Attribute.TEXT);

        content[12] = new InterfaceItem(Material.TORCHFLOWER)
                .name(Component.text("Gradients", Colors.ACCENT))
                .description(
                        Component.text("Apply gradients", NamedTextColor.GRAY)
                )
                .glow(() -> attribute == Attribute.GRADIENT);

        content[13] = new InterfaceItem(Material.PAINTING)
                .name(Component.text("Background", Colors.ACCENT))
                .description(
                        Component.text("Adjust background", NamedTextColor.GRAY)
                )
                .glow(() -> attribute == Attribute.BACKGROUND);

        content[14] = new InterfaceItem(Material.ANVIL)
                .name(Component.text("Alignment", Colors.ACCENT))
                .description(
                        Component.text("Adjust alignment", NamedTextColor.GRAY)
                )
                .glow(() -> attribute == Attribute.ALIGNMENT);

        content[15] = new InterfaceItem(Material.OAK_SIGN)
                .name(Component.text("Billboard", Colors.ACCENT))
                .description(
                        Component.text("Adjust the billboard", NamedTextColor.GRAY)
                )
                .glow(() -> attribute == Attribute.BILLBOARD);

        switch(attribute){
            case TEXT -> TextItems.getTextItems(content);
            case GRADIENT -> GradientItems.getGradientItems(content, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings());
            case BACKGROUND -> BackgroundItems.getBackgroundItems(content);
            case ALIGNMENT -> AlignmentItems.getAlignmentItems(content);
            case BILLBOARD -> BillboardItems.getBillboardItems(content, main.getClipboardManager().getWrapper(player.getUniqueId()).display().getBillboard());
        }

        FrequentItems.addGeneralItems(content);

        return content;
    }

}
