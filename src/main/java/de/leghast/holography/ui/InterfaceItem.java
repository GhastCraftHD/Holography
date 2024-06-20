package de.leghast.holography.ui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;


public class InterfaceItem extends ItemStack {

    public InterfaceItem(Material material){
        super(material);
    }

    public InterfaceItem name(Component displayName){
        ItemMeta meta = this.getItemMeta();
        meta.displayName(displayName.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
        super.setItemMeta(meta);
        return this;
    }

    public InterfaceItem description(Component... lore){
        return description(Arrays.stream(lore).toList());
    }

    public InterfaceItem description(List<Component> lore){
        ItemMeta meta = this.getItemMeta();
        meta.lore(
                lore.stream().map(val -> val.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)).toList()
        );
        super.setItemMeta(meta);
        return this;
    }

    public InterfaceItem glow(){
        addGlint();
        return this;
    }

    public InterfaceItem glow(GlowPredicate predicate){
        if(predicate.test()) addGlint();
        return this;
    }

    private void addGlint(){
        ItemMeta meta = super.getItemMeta();
        meta.addEnchant(Enchantment.UNBREAKING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        super.setItemMeta(meta);
    }

}
