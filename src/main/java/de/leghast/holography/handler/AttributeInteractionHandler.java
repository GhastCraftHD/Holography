package de.leghast.holography.handler;

import de.leghast.holography.Holography;
import de.leghast.holography.display.DisplayWrapper;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.settings.AdjusterSettings;
import de.leghast.holography.settings.AttributeSettings;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.UserInterface;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;

public class AttributeInteractionHandler {

    public AttributeInteractionHandler(Holography main, int slot, Player player) {

        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        AttributeSettings attributeSettings = settings.getAttributeSettings();
        Attribute attribute = attributeSettings.getAttribute();
        DisplayWrapper wrapper = main.getClipboardManager().getWrapper(player.getUniqueId());
        TextDisplay display = wrapper.display();

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 11 -> attributeSettings.setAttribute(Attribute.TEXT);
            case 12 -> attributeSettings.setAttribute(Attribute.GRADIENT);
            case 13 -> attributeSettings.setAttribute(Attribute.BACKGROUND);
            case 14 -> attributeSettings.setAttribute(Attribute.ALIGNMENT);
            case 15 -> attributeSettings.setAttribute(Attribute.BILLBOARD);
            case 26 -> {
                main.getClipboardManager().remove(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getClipboardManager().getWrapper(player.getUniqueId()).remove();
                main.getClipboardManager().remove(player.getUniqueId());
                player.closeInventory();
            }
        }

        switch(attribute){
            case TEXT -> {
                switch(slot){
                    case 29 -> {
                        wrapper.registerChatInput(main, player);
                        player.closeInventory();
                    }
                    case 30 -> wrapper.setLineWidth(main, player);
                    case 31 -> wrapper.toggleTextShadow();
                    case 32 -> wrapper.setTextColor(main, player);
                    case 33 -> wrapper.setTextOpacity(main, player);
                }
            }
            case GRADIENT -> {
                switch(slot){
                    case 30 -> main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings().setFirst(main, player);
                    case 31 -> main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings().setSecond(main, player);
                    case 32 -> wrapper.setTextGradient(main, player);
                }
            }
            case BACKGROUND -> {
                switch(slot){
                    case 30 -> wrapper.setBackgroundColor(main, player);
                    case 31 -> display.setSeeThrough(!display.isSeeThrough());
                    case 32 -> display.setDefaultBackground(true);
                }
            }
            case ALIGNMENT -> {
                switch(slot){
                    case 30 -> display.setAlignment(TextDisplay.TextAlignment.LEFT);
                    case 31 -> display.setAlignment(TextDisplay.TextAlignment.CENTER);
                    case 32 -> display.setAlignment(TextDisplay.TextAlignment.RIGHT);
                }
            }
            case BILLBOARD -> {
                switch(slot) {
                    case 29 -> display.setBillboard(Display.Billboard.VERTICAL);
                    case 30 -> display.setBillboard(Display.Billboard.HORIZONTAL);
                    case 32 -> display.setBillboard(Display.Billboard.CENTER);
                    case 33 -> display.setBillboard(Display.Billboard.FIXED);
                }
            }
        }

        if(attribute != Attribute.TEXT && slot != 29 && slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }

    }
}
