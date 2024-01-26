package de.leghast.holography.instance;

import de.leghast.holography.Holography;
import de.leghast.holography.instance.settings.AttributeSettings;
import de.leghast.holography.ui.page.PageUtil;
import de.leghast.holography.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Transformation;

import java.awt.*;
import java.util.Arrays;

public class DisplayWrapper {

    private TextDisplay display;

    public DisplayWrapper(TextDisplay display){
        this.display = display;
    }

    public TextDisplay getDisplay(){
        return display;
    }

    public void move(Axis axis, double factor){
        switch(axis){
            case X -> display.teleport(display.getLocation().add(factor, 0, 0));
            case Y -> display.teleport(display.getLocation().add(0, factor, 0));
            case Z -> display.teleport(display.getLocation().add(0, 0, factor));
        }
    }

    public void rotate(Axis axis, double factor){
        float radiant = (float) Math.toRadians(factor);
        Transformation transformation = display.getTransformation();
        switch (axis){
            case X -> transformation.getLeftRotation().rotateX(radiant);
            case Y -> transformation.getLeftRotation().rotateY(radiant);
            case Z -> transformation.getLeftRotation().rotateZ(radiant);
        }
        display.setTransformation(transformation);
    }

    public void scaleUp(double factor){
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x * factor);
        display.setTransformation(transformation);
    }

    public void scaleDown(double factor){
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x / factor);
        display.setTransformation(transformation);
    }

    public void setNewText(Holography main, Player player){
        ItemStack output = new ItemStack(Material.PAPER);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName(display.getText());
        output.setItemMeta(meta);
        PageUtil.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter new text")
                .text(display.getText())
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        String text = stateSnapshot.getText();
                        text = text.replace("&n", "\n");
                        getDisplay().setText(ChatColor.translateAlternateColorCodes('&', text));
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eSet new text", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

    public void setTextColor(Holography main, Player player){
        Util.getChatColor(ChatColor.WHITE, main, player).thenAccept(color -> {
            String text = display.getText();
            text = ChatColor.stripColor(text);
            display.setText(color + text);
        });

    }

    public void setTextGradient(Holography main, Player player){
        AttributeSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings();
        MiniMessage mm = MiniMessage.miniMessage();
        String firstColor = Util.colorToHex(settings.getFirst());
        String secondColor = Util.colorToHex(settings.getSecond());
        String text = ChatColor.stripColor(display.getText());
        Component parsed = mm.deserialize("<gradient:" + firstColor + ":" + secondColor + ">" + text + "</gradient>");
        display.text(parsed);
    }

    public void setTextOpacity(Holography main, Player player){
        ItemStack output = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet text opacity");
        output.setItemMeta(meta);
        PageUtil.addGlint(output);
        String oldOpacity = String.valueOf(display.getTextOpacity() < 0 ? display.getTextOpacity() + 256 : display.getTextOpacity());

        new AnvilGUI.Builder()
                .title("§eEnter new opacity")
                .text(oldOpacity)
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        try{
                            int newOpacity = Integer.parseInt(stateSnapshot.getText());
                            if(newOpacity < 25 || newOpacity > 255){
                                throw new NumberFormatException();
                            }else if(newOpacity > 127){
                                newOpacity -= 256;
                            }
                            display.setTextOpacity((byte) newOpacity);
                            player.sendMessage(Util.PREFIX + "§aSet text opacity to §e" + stateSnapshot.getText() + "§a");
                        }catch (NumberFormatException e){
                            player.sendMessage(Util.PREFIX + "§cPlease enter a valid opacity (25 - 255)");
                        }
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eSet new opacity", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

    public void toggleTextShadow(){
        display.setShadowed(!display.isShadowed());
    }

    public void setLineWidth(Holography main, Player player){
        ItemStack output = new ItemStack(Material.PAPER);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet line width");
        output.setItemMeta(meta);
        PageUtil.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter new line width")
                .text(String.valueOf(display.getLineWidth()))
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        try{
                            int newLineWidth = Integer.parseInt(stateSnapshot.getText());
                            display.setLineWidth(newLineWidth);
                            player.sendMessage(Util.PREFIX + "§aSet line width to §e" + stateSnapshot.getText() + "§a");
                        }catch (NumberFormatException e){
                            player.sendMessage(Util.PREFIX + "§cPlease enter a valid number");
                        }
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eSet new line width", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);
    }

    public void setBackgroundColor(Holography main, Player player){
        display.setDefaultBackground(false);
        Util.getChatColor(ChatColor.of(new Color(display.getBackgroundColor().asRGB())), main, player).thenAccept(color -> {
            display.setBackgroundColor(org.bukkit.Color.fromARGB(color.getColor().getRGB()));
        });
    }

    public void remove(){
        display.remove();
    }

}
