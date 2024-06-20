package de.leghast.holography.constant;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.TextDisplay;

public class Message {

    public static final Component HOLO_COMMAND_USAGE = Prefix.HOLOGRAPHY.append(
            Component.text("Usage: /hologram <create | list | remove | select | edit | clear>", Colors.ERROR)
    );

    public static final Component CLEARED_CLIPBOARD = Prefix.HOLOGRAPHY.append(
            Component.text("Your clipboard was cleared", Colors.SUCCESS)
    );

    public static final Component CLIPBOARD_EMPTY = Prefix.HOLOGRAPHY.append(
            Component.text("Your clipboard is already empty", Colors.ERROR)
    );

    public static final Component REMOVED_HOLO = Prefix.HOLOGRAPHY.append(
            Component.text("The Text Display was removed", Colors.SUCCESS)
    );

    public static final Component NO_HOLO_SELECTED = Prefix.HOLOGRAPHY.append(
            Component.text("You have no Text Display selected", Colors.ERROR)
    );

    public static final Component COULD_NOT_FIND_ENTITY = Prefix.HOLOGRAPHY.append(
            Component.text("This entity could not be found", Colors.ERROR)
    );

    public static final Component NEW_TEXT_TITLE = Component.text("Enter new text in Chat", Colors.SUCCESS);

    public static final Component INVALID_COLOR = Prefix.HOLOGRAPHY.append(
            Component.text("Please enter a valid color in the hexadecimal format", Colors.ERROR)
    );

    public static final Component INVALID_OPACITY = Prefix.HOLOGRAPHY.append(
            Component.text("Please enter a valid opacity (25 - 255)", Colors.ERROR)
    );

    public static final Component INVALID_LINE_WIDTH = Prefix.HOLOGRAPHY.append(
            Component.text("Please enter a valid line width (whole numbers)", Colors.ERROR)
    );

    public static final Component INVALID_FACTOR = Prefix.HOLOGRAPHY.append(
            Component.text("Please enter a valid factor", Colors.ERROR)
    );

    public static final Component CANCELLED_INPUT = Prefix.HOLOGRAPHY.append(
            Component.text("The input was cancelled", Colors.ERROR)
    );

    public static final Component USE_MINIMESSAGE = Prefix.HOLOGRAPHY.append(
            Component.text("If possible. please use the ", Colors.SUCCESS)
                    .append(Component.text("MiniMessage", Colors.ACCENT)
                            .decorate(TextDecoration.UNDERLINED)
                            .hoverEvent(HoverEvent.showText(Component.text("Open MiniMessage Viewer in browser", Colors.ACCENT)))
                            .clickEvent(ClickEvent.openUrl("https://webui.advntr.dev/")))
                    .append(Component.text(" format", Colors.SUCCESS))
    );

    public static final Component NEW_TEXT_SUBTITLE =
            Component.text("Type ", Colors.SUCCESS)
                    .append(Component.text("cancel", Colors.ACCENT))
                    .append(Component.text(" to cancel chat input", Colors.SUCCESS)
    );


    public static Component listCommandHeader(int radius){
        return Prefix.HOLOGRAPHY.append(
                Component.text("All text display in a radius of ", Colors.SUCCESS)
                        .append(Component.text(radius, Colors.ACCENT))
                        .append(Component.text(" block" + (radius == 1 ? "" : "s"), Colors.SUCCESS))
        );
    }

    public static Component setTextOpacity(byte opacity){
        return Prefix.HOLOGRAPHY.append(
                Component.text("Set the text opacity to ", Colors.SUCCESS)
                        .append(Component.text(opacity, Colors.ACCENT))
        );
    }

    public static Component setLineWidth(int lineWidth){
        return Prefix.HOLOGRAPHY.append(
                Component.text("Set the line width to ", Colors.SUCCESS)
                        .append(Component.text(lineWidth, Colors.ACCENT))
        );
    }

    public static Component createdHolo(Component text){
        return Prefix.HOLOGRAPHY.append(
                Component.text("Created ", Colors.SUCCESS)
                        .append(text.color((text.color() == null) ? NamedTextColor.WHITE : text.color()))
        );
    }

    public static Component setText(Component text){
        return Prefix.HOLOGRAPHY.append(
                Component.text("Changed the text to ", Colors.SUCCESS)
                        .append(text.color((text.color() == null) ? NamedTextColor.WHITE : text.color()))
        );
    }

    public static Component noHoloInRange(int radius){
        return Prefix.HOLOGRAPHY.append(
                Component.text("There are no Text Displays in a radius of ", Colors.ERROR)
                        .append(Component.text(radius, Colors.ACCENT))
                        .append(Component.text(" block" + (radius == 1 ? "" : "s"), Colors.ERROR))
        );
    }

    public static Component selectedHolo(Component displayComponent){
        return Prefix.HOLOGRAPHY.append(
                Component.text("You selected ", Colors.ACCENT)
                        .append(displayComponent.color((displayComponent.color() == null) ? NamedTextColor.WHITE : displayComponent.color()))
        );
    }

    public static Component newVersionAvailable(String newVersion){
        return Prefix.HOLOGRAPHY.append(
                Component.text("A new version of Holography is available: ", Colors.SUCCESS)
                        .append(Component.text("Version " + newVersion, Colors.ACCENT))
                        .append(Component.newline())
                        .append(Prefix.HOLOGRAPHY)
                        .append(Component.text("Get it from ", Colors.SUCCESS))
                        .append(Component.text("Hangar", Colors.ACCENT)
                                .clickEvent(ClickEvent.openUrl("https://hangar.papermc.io/GhastCraftHD/Holography/versions/" + newVersion))
                                .hoverEvent(HoverEvent.showText(Component.text("Click to open web page", Colors.ACCENT)))
                                .decorate(TextDecoration.UNDERLINED))
        );
    }

    public static Component getDisplayNameComponent(TextDisplay display){
        return Prefix.HOLOGRAPHY.append(
                Component.text(" - ", NamedTextColor.GRAY)
                        .append(display.text()
                                .color((display.text().color() == null) ? NamedTextColor.WHITE : display.text().color()))
                .hoverEvent(HoverEvent.showText(Component.text("Select §r", Colors.ACCENT)
                        .append(display.text()
                                .color((display.text().color() == null) ? NamedTextColor.WHITE : display.text().color())))))
                .clickEvent(ClickEvent.runCommand("/hologram select " + display.getUniqueId()));
    }

    public static Component suggestOldText(Component oldText){
        return Prefix.HOLOGRAPHY.append(
                Component.text("Click to paste the ", Colors.SUCCESS)
                        .append(Component.text("old text", Colors.ACCENT).decorate(TextDecoration.UNDERLINED))
                        .append(Component.text(" in your chat window", Colors.SUCCESS))
                        .hoverEvent(HoverEvent.showText(
                                Component.text("Paste ", Colors.ACCENT)
                                        .append(oldText.color((oldText.color() == null) ? NamedTextColor.WHITE : oldText.color()))
                                        .append(Component.text(" in chat", Colors.ACCENT))
                        ))
                        .clickEvent(ClickEvent.suggestCommand(MiniMessage.miniMessage().serialize(oldText)))
        );
    }

    public static Component changedFactor(double factor){
        return Prefix.HOLOGRAPHY.append(
                Component.text("The factor was set to ", Colors.SUCCESS)
                        .append(Component.text(factor + " block" + (factor == 1 ? "" : "s") + "/degree" + (factor == 1 ? "" : "s"), Colors.ACCENT))
        );
    }

}
