package de.leghast.holography.display;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Message;
import de.leghast.holography.settings.AttributeSettings;
import de.leghast.holography.ui.AnvilInputHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Axis;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

import org.bukkit.util.Transformation;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


public record DisplayWrapper(TextDisplay display) {

    public void move(Axis axis, double factor) {
        switch (axis) {
            case X -> display.teleport(display.getLocation().add(factor, 0, 0));
            case Y -> display.teleport(display.getLocation().add(0, factor, 0));
            case Z -> display.teleport(display.getLocation().add(0, 0, factor));
        }
    }

    public void rotate(Axis axis, double factor) {
        float radiant = (float) Math.toRadians(factor);
        Transformation transformation = display.getTransformation();
        switch (axis) {
            case X -> transformation.getLeftRotation().rotateX(radiant);
            case Y -> transformation.getLeftRotation().rotateY(radiant);
            case Z -> transformation.getLeftRotation().rotateZ(radiant);
        }
        display.setTransformation(transformation);
    }

    public void scaleUp(double factor) {
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x * factor);
        display.setTransformation(transformation);
    }

    public void scaleDown(double factor) {
        Transformation transformation = display.getTransformation();
        transformation.getScale().set(transformation.getScale().x / factor);
        display.setTransformation(transformation);
    }

    public void registerChatInput(Holography main, Player player) {

        player.showTitle(Title.title(
                Message.NEW_TEXT_TITLE,
                Message.NEW_TEXT_SUBTITLE,
                Title.Times.times(
                        Duration.of(200, ChronoUnit.MILLIS),
                        Duration.of(6, ChronoUnit.SECONDS),
                        Duration.of(200, ChronoUnit.MILLIS)
                )
        ));

        player.sendMessage(Message.suggestOldText(display.text()));
        player.sendMessage(Message.USE_MINIMESSAGE);

        main.getChatInputManager().register(player.getUniqueId());

    }

    public void displayText(String text){
        display.text(MiniMessage.miniMessage().deserialize(text));
    }

    public void setTextColor(Holography main, Player player) {
        AnvilInputHelper.getTextColor((display.text().color() != null) ? display.text().color() : NamedTextColor.WHITE, main, player).thenAccept(optionalColor -> optionalColor.ifPresentOrElse(
                color -> display.text(display.text().color(color)),
                () -> player.sendMessage(Message.INVALID_COLOR)
        ));

    }

    public void setTextGradient(Holography main, Player player) {
        AttributeSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getAttributeSettings();
        MiniMessage mm = MiniMessage.miniMessage();
        String firstColor = settings.getGradientFirst().asHexString();
        String secondColor = settings.getGradientSecond().asHexString();
        String text = ((TextComponent) display.text()).content();
        Component parsed = mm.deserialize("<gradient:" + firstColor + ":" + secondColor + ">" + text + "</gradient>");
        display.text(parsed);
    }

    public void setTextOpacity(Holography main, Player player) {
        AnvilInputHelper.getOpacityInput(main, player, display.getTextOpacity()).thenAccept(optionalOpacity -> optionalOpacity.ifPresentOrElse(
                opacity -> {
                    display.setTextOpacity(opacity);
                    player.sendMessage(Message.setTextOpacity(opacity));
                },
                () -> player.sendMessage(Message.INVALID_OPACITY)
        ));
    }

    public void toggleTextShadow() {
        display.setShadowed(!display.isShadowed());
    }

    public void setLineWidth(Holography main, Player player) {
        AnvilInputHelper.getLineWidth(main, player, display.getLineWidth()).thenAccept(optionalWidth -> optionalWidth.ifPresentOrElse(
                width -> {
                    display.setLineWidth(width);
                    player.sendMessage(Message.setLineWidth(width));
                },
                () -> player.sendMessage(Message.INVALID_LINE_WIDTH)
        ));
    }

    public void setBackgroundColor(Holography main, Player player) {
        display.setDefaultBackground(false);

        AnvilInputHelper.getTextColor(TextColor.color(display.getBackgroundColor().asRGB()), main, player).thenAccept(
                optionalColor -> optionalColor.ifPresentOrElse(
                        color -> display.setBackgroundColor(Color.fromRGB(color.value())),
                        () -> player.sendMessage(Message.INVALID_COLOR)
                )
        );

    }

    public void remove() {
        display.remove();
    }

}
