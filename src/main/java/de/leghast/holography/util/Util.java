package de.leghast.holography.util;

import de.leghast.holography.Holography;
import de.leghast.holography.instance.settings.AdjusterSettings;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.Page;
import de.leghast.holography.ui.page.PageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.md_5.bungee.api.ChatColor;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

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
        TextReplacementConfig config = TextReplacementConfig.builder()
                .match("\n")
                .replacement(Component.text("  \n"))
                .build();
        return Component.text("§7- §r").append(display.text().replaceText(config))
                .hoverEvent(HoverEvent.showText(Component.text("§eSelect §r").append(display.text().replaceText(config))))
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

    public static CompletableFuture<ChatColor> getChatColor(ChatColor oldColor, Holography main, Player player){
        ItemStack output = new ItemStack(Material.WHITE_STAINED_GLASS);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet new color");
        output.setItemMeta(meta);
        AtomicReference<ChatColor> newColor = new AtomicReference<>();
        String oldHex = "#" + Integer.toHexString(oldColor.getColor().getRGB()).substring(2);
        CompletableFuture<ChatColor> future = new CompletableFuture<>();

        new AnvilGUI.Builder()
                .title("§eEnter new hex color")
                .text(oldHex)
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        try{
                            String newHex = stateSnapshot.getText();
                            if(newHex.length() != 7){
                                throw new NumberFormatException();
                            }
                            newColor.set(ChatColor.of(newHex));
                            future.complete(newColor.get());
                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                        }catch (NumberFormatException e){
                            player.sendMessage(PREFIX + "§cPlease enter a valid hex color");
                            future.completeExceptionally(e);
                            return Arrays.asList(AnvilGUI.ResponseAction.close());
                        }
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eEnter new hex color", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);

        return future;
    }

    public static String colorToHex(ChatColor color){
        return "#" + Integer.toHexString(color.getColor().getRGB()).substring(2);
    }



}
