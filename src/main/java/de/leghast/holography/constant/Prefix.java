package de.leghast.holography.constant;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Prefix {

    public static final Component HOLOGRAPHY = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("Holography", Colors.HOLOGRAPHY))
            .append(Component.text("] ", NamedTextColor.GRAY));

}
