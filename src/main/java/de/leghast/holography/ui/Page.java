package de.leghast.holography.ui;

import de.leghast.holography.constant.Colors;
import net.kyori.adventure.text.Component;

public enum Page {

    POSITION(Component.text("Adjust the holograms position", Colors.ACCENT)),
    SIZE(Component.text("Adjust the holograms size", Colors.ACCENT)),
    ROTATION(Component.text("Adjust the holograms rotation", Colors.ACCENT)),
    ATTRIBUTES(Component.text("Adjust the holograms attributes", Colors.ACCENT));

    private final Component title;

    Page(Component title){
        this.title = title;
    }

    public Component getTitle(){
        return this.title;
    }

}
