package de.leghast.holography.instance.settings;

import de.leghast.holography.Holography;
import de.leghast.holography.ui.Attribute;
import de.leghast.holography.util.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class AttributeSettings {

    private AdjusterSettings parent;

    private Attribute attribute = Attribute.TEXT;

    private ChatColor first = ChatColor.of("#ffffff");
    private ChatColor second = ChatColor.of("#000000");

    public AttributeSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public Attribute getAttribute(){
        return attribute;
    }

    public void setAttribute(Attribute attribute){
        this.attribute = attribute;
    }

    public ChatColor getFirst(){
        return first;
    }

    public void setFirst(Holography main, Player player){
        Util.getChatColor(first, main, player).thenAccept(color ->{
            this.first = color;
        });
    }

    public ChatColor getSecond(){
        return second;
    }

    public void setSecond(Holography main, Player player){
        Util.getChatColor(second, main, player).thenAccept(color ->{
            this.second = color;
        });
    }

}

