package de.leghast.holography.ui.page;

import de.leghast.holography.Holography;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.page.attribute.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AttributesPage {

    public static ItemStack[] getAttributesPage(Holography main, Player player){
        ItemStack[] content = new ItemStack[45];

        Attribute attribute = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings().getAttribute();

        PageUtil.addPageSwitchItems(content, Page.ATTRIBUTES);

        ItemStack text = new ItemStack(Material.FILLED_MAP);
        ItemMeta textMeta = text.getItemMeta();
        textMeta.setDisplayName("§eText");
        text.setItemMeta(textMeta);
        content[11] = text;
        if(attribute == Attribute.TEXT){
            PageUtil.addGlint(text);
        }

        ItemStack gradient = new ItemStack(Material.TORCHFLOWER);
        ItemMeta gradientMeta = gradient.getItemMeta();
        gradientMeta.setDisplayName("§eGradient");
        gradient.setItemMeta(gradientMeta);
        content[12] = gradient;
        if(attribute == Attribute.GRADIENT){
            PageUtil.addGlint(gradient);
        }

        ItemStack background = new ItemStack(Material.PAINTING);
        ItemMeta backgroundMeta = background.getItemMeta();
        backgroundMeta.setDisplayName("§eBackground");
        background.setItemMeta(backgroundMeta);
        content[13] = background;
        if(attribute == Attribute.BACKGROUND){
            PageUtil.addGlint(background);
        }

        ItemStack alignment = new ItemStack(Material.ANVIL);
        ItemMeta alignmentMeta = alignment.getItemMeta();
        alignmentMeta.setDisplayName("§eAlignment");
        alignment.setItemMeta(alignmentMeta);
        content[14] = alignment;
        if(attribute == Attribute.ALIGNMENT){
            PageUtil.addGlint(alignment);
        }

        ItemStack billboard = new ItemStack(Material.OAK_SIGN);
        ItemMeta billboardMeta = billboard.getItemMeta();
        billboardMeta.setDisplayName("§eBillboard");
        billboard.setItemMeta(billboardMeta);
        content[15] = billboard;
        if(attribute == Attribute.BILLBOARD){
            PageUtil.addGlint(billboard);
        }

        switch(attribute){
            case TEXT -> TextItems.getTextItems(content);
            case GRADIENT -> GradientItems.getGradientItems(content, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings());
            case BACKGROUND -> BackgroundItems.getBackgroundItems(content);
            case ALIGNMENT -> AlignmentItems.getAlignmentItems(content);
            case BILLBOARD -> BillboardItems.getBillboardItems(content, main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().getBillboard());
        }


        PageUtil.addGeneralItems(content);

        return content;
    }

}
