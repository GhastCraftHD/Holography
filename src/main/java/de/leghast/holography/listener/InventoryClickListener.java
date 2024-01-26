package de.leghast.holography.listener;

import de.leghast.holography.Holography;
import de.leghast.holography.instance.settings.AdjusterSettings;
import de.leghast.holography.instance.settings.AttributeSettings;
import de.leghast.holography.instance.settings.DimensionSettings;
import de.leghast.holography.instance.settings.FactorSettings;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.UserInterface;
import de.leghast.holography.util.Util;
import org.bukkit.Axis;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private Holography main;

    public InventoryClickListener(Holography main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        int slot = e.getRawSlot();
        if (title.contains(Page.POSITION.getTitle())){
            handlePositionInteractions(slot, player);
            e.setCancelled(true);
        }else if(title.contains(Page.SIZE.getTitle())){
            handleSizeInteractions(slot, player);
            e.setCancelled(true);
        }else if(title.contains(Page.ROTATION.getTitle())){
            handleRotationInteractions(slot, player);
            e.setCancelled(true);
        }else if(title.contains(Page.ATTRIBUTES.getTitle())){
            handleAttributesInteraction(slot, player);
            e.setCancelled(true);
        }
    }

    private void handlePositionInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings positionSettings = settings.getPositionSettings();

        switch(slot){
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.ATTRIBUTES);
            case 15 -> Util.getCustomNumberInput(main, player, settings.getPage());
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(10);
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
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

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleSizeInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        FactorSettings sizeSettings = settings.getSizeSettings();

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.ATTRIBUTES);
            case 20 -> sizeSettings.setFactor(0.25);
            case 21 -> sizeSettings.setFactor(0.5);
            case 22 -> sizeSettings.setFactor(1);
            case 23 -> sizeSettings.setFactor(5);
            case 24 -> Util.getCustomNumberInput(main, player, settings.getPage());
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

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleRotationInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings rotationSettings = settings.getRotationSettings();

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
            case 9 -> settings.setPage(Page.SIZE);
            case 27 -> settings.setPage(Page.ATTRIBUTES);
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> Util.getCustomNumberInput(main, player, settings.getPage());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
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

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }

    }

    private void handleAttributesInteraction(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        AttributeSettings attributeSettings = settings.getAttributeSettings();
        Attribute attribute = attributeSettings.getAttribute();
        TextDisplay display = main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay();

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
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
                    case 29 -> main.getClipboardManager().getWrapper(player.getUniqueId()).setNewText(main, player);
                    case 30 -> main.getClipboardManager().getWrapper(player.getUniqueId()).setLineWidth(main, player);
                    case 31 -> main.getClipboardManager().getWrapper(player.getUniqueId()).toggleTextShadow();
                    case 32 -> main.getClipboardManager().getWrapper(player.getUniqueId()).setTextColor(main, player);
                    case 33 -> main.getClipboardManager().getWrapper(player.getUniqueId()).setTextOpacity(main, player);
                }
            }
            case GRADIENT -> {
                switch(slot){
                    case 30 -> main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings().setFirst(main, player);
                    case 31 -> main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings().setSecond(main, player);
                    case 32 -> main.getClipboardManager().getWrapper(player.getUniqueId()).setTextGradient(main, player);
                }
            }
            case BACKGROUND -> {
                switch(slot){
                    case 30 -> main.getClipboardManager().getWrapper(player.getUniqueId()).setBackgroundColor(main, player);
                    case 32 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setDefaultBackground(true);
                }
            }
            case ALIGNMENT -> {
                switch(slot){
                    case 30 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setAlignment(TextDisplay.TextAlignment.LEFT);
                    case 31 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setAlignment(TextDisplay.TextAlignment.CENTER);
                    case 32 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setAlignment(TextDisplay.TextAlignment.RIGHT);
                }
            }
            case BILLBOARD -> {
                switch(slot) {
                    case 29 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setBillboard(Display.Billboard.VERTICAL);
                    case 30 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setBillboard(Display.Billboard.HORIZONTAL);
                    case 32 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setBillboard(Display.Billboard.CENTER);
                    case 33 -> main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setBillboard(Display.Billboard.FIXED);
                }
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }
}
