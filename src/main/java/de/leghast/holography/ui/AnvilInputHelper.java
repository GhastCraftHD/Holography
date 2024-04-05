package de.leghast.holography.ui;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Colors;
import de.leghast.holography.settings.AdjusterSettings;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AnvilInputHelper {

    public static void getCustomNumberInput(Holography main, Player player, Page page, double factor){

        ItemStack output= new InterfaceItem(Material.PAPER)
                .name(Component.text("Set custom factor", Colors.ACCENT))
                .glow();

        new AnvilGUI.Builder()
                .title("§eEnter custom factor")
                .text(String.valueOf(factor))
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                        switch (page){
                            case POSITION -> settings.getPositionSettings().setFactor(stateSnapshot.getText());
                            case SIZE -> settings.getSizeSettings().setFactor(stateSnapshot.getText());
                            case ROTATION -> settings.getRotationSettings().setFactor(stateSnapshot.getText());
                        }
                        return List.of(AnvilGUI.ResponseAction.close());
                    }
                    return List.of(AnvilGUI.ResponseAction.updateTitle("§eEnter custom factor", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

    public static CompletableFuture<Optional<TextColor>> getTextColor(TextColor oldColor, Holography main, Player player){
        ItemStack output = new InterfaceItem(Material.WHITE_STAINED_GLASS)
                .name(Component.text("Set new color", Colors.ACCENT))
                .glow();

        CompletableFuture<Optional<TextColor>> future = new CompletableFuture<>();

        new AnvilGUI.Builder()
                .title("§eEnter new hex color")
                .text(oldColor.asHexString())
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        try{
                            String hex = stateSnapshot.getText();
                            if(hex.isEmpty()) throw new IllegalArgumentException();
                            if(hex.length() != 7) throw new NumberFormatException();

                            future.complete(Optional.ofNullable(TextColor.fromHexString(hex)));
                            return List.of(AnvilGUI.ResponseAction.close());
                        }catch (Exception e){
                            future.complete(Optional.empty());
                            return List.of(AnvilGUI.ResponseAction.close());
                        }
                    }
                    return List.of(AnvilGUI.ResponseAction.updateTitle("§eEnter new hex color", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);

        return future;
    }

    public static CompletableFuture<Optional<Byte>> getOpacityInput(Holography main, Player player, byte oldOpacity) {

        ItemStack output = new InterfaceItem(Material.WHITE_STAINED_GLASS_PANE)
                .name(Component.text("Set text opacity", Colors.ACCENT))
                .glow();

        String oldOpacityString = String.valueOf(oldOpacity < 0 ? oldOpacity + 256 : oldOpacity);
        CompletableFuture<Optional<Byte>> future = new CompletableFuture<>();

        new AnvilGUI.Builder()
                .title("§eEnter new opacity")
                .text(oldOpacityString)
                .onClick((slot, stateSnapshot) -> {
                    if (slot == AnvilGUI.Slot.OUTPUT) {
                        try {
                            int newOpacity = Integer.parseInt(stateSnapshot.getText());
                            if (newOpacity < 25 || newOpacity > 255) {
                                throw new NumberFormatException();
                            } else if (newOpacity > 127) {
                                newOpacity -= 256;
                            }
                            future.complete(Optional.of((byte) newOpacity));
                        } catch (NumberFormatException e) {
                            future.complete(Optional.empty());
                        }
                        return List.of(AnvilGUI.ResponseAction.close());
                    }
                    return List.of(AnvilGUI.ResponseAction.updateTitle("§eSet new opacity", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);

        return future;
    }

    public static CompletableFuture<Optional<Integer>> getLineWidth(Holography main, Player player, int oldLineWidth){

        ItemStack output = new InterfaceItem(Material.PAPER)
                .name(Component.text("Set line width"))
                .glow();

        CompletableFuture<Optional<Integer>> future = new CompletableFuture<>();

        new AnvilGUI.Builder()
                .title("§eEnter new line width")
                .text(String.valueOf(oldLineWidth))
                .onClick((slot, stateSnapshot) -> {
                    if (slot == AnvilGUI.Slot.OUTPUT) {
                        try {
                            int newLineWidth = Integer.parseInt(stateSnapshot.getText());
                            future.complete(Optional.of(newLineWidth));
                        } catch (NumberFormatException e) {
                            future.complete(Optional.empty());
                        }
                        return List.of(AnvilGUI.ResponseAction.close());
                    }
                    return List.of(AnvilGUI.ResponseAction.updateTitle("§eSet new line width", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);

        return future;
    }

}
