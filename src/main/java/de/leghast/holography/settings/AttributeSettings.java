package de.leghast.holography.settings;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Message;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.ui.AnvilInputHelper;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class AttributeSettings {

    private Attribute attribute = Attribute.TEXT;

    private TextColor gradientFirst = TextColor.fromHexString("#ffffff");
    private TextColor gradientSecond = TextColor.fromHexString("#000000");

    public Attribute getAttribute(){
        return attribute;
    }

    public void setAttribute(Attribute attribute){
        this.attribute = attribute;
    }

    public TextColor getGradientFirst(){
        return gradientFirst;
    }

    public void setFirst(Holography main, Player player){
        AnvilInputHelper.getTextColor(gradientFirst, main, player).thenAccept(optionalColor -> optionalColor.ifPresentOrElse(
                color -> this.gradientFirst = color,
                () -> player.sendMessage(Message.INVALID_COLOR)
        ));
    }

    public TextColor getGradientSecond(){
        return gradientSecond;
    }

    public void setSecond(Holography main, Player player){
        AnvilInputHelper.getTextColor(gradientSecond, main, player).thenAccept(optionalColor -> optionalColor.ifPresentOrElse(
                color -> this.gradientSecond = color,
                () -> player.sendMessage(Message.INVALID_COLOR)
        ));
    }

}

