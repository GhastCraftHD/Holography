package de.leghast.holography.util;

import de.leghast.holography.Holography;
import de.leghast.holography.instance.settings.AdjusterSettings;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.page.PageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class Util {

    public static final String PREFIX = "§7[§9Holography§7] ";

    public static TextDisplay spawnTextDisplay(Location location){
        Vector position = location.toVector();
        TextDisplay display;
        display = (TextDisplay) location.getWorld().spawnEntity(
                new Location(
                        location.getWorld(),
                        position.getX(),
                        position.getY(),
                        position.getZ()),
                EntityType.TEXT_DISPLAY);

        display.setText("§eTextDisplay");
        display.setBillboard(ConfigManager.getDefaultBillboard());
        return display;
    }

    public static TextComponent getDisplayNameComponent(TextDisplay display){
        return Component.text("§7- §r" + display.getText())
                .hoverEvent(HoverEvent.showText(Component.text("§eSelect §r" + display.getText())))
                .clickEvent(ClickEvent.runCommand("/hologram select " + display.getUniqueId()));
    }

    public static void getCustomNumberInput(Holography main, Player player, Page page){
        ItemStack output = new ItemStack(Material.PAPER);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet custom factor");
        output.setItemMeta(meta);
        PageUtil.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter custom factor")
                .text("1")
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                        switch (page){
                            case POSITION -> settings.getPositionSettings().setFactor(stateSnapshot.getText());
                            case SIZE -> settings.getSizeSettings().setFactor(stateSnapshot.getText());
                            case ROTATION -> settings.getRotationSettings().setFactor(stateSnapshot.getText());
                        }
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eEnter custom factor", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

    public static void getNewText(String oldText, Holography main, Player player){
        ItemStack output = new ItemStack(Material.PAPER);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName(main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().getText());
        output.setItemMeta(meta);
        PageUtil.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter new text")
                .text(oldText)
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().setText(ChatColor.translateAlternateColorCodes('&', stateSnapshot.getText()));
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eSet new text", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

    public static void getTextOpacity(int oldOpacity, Holography main, Player player){
        ItemStack output = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet text opacity");
        output.setItemMeta(meta);
        PageUtil.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter new opacity")
                .text(String.valueOf(oldOpacity))
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        main.getClipboardManager().getWrapper(player.getUniqueId()).setTextOpacity(stateSnapshot.getText(), player);
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eSet new opacity", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

}
