package de.leghast.holography.command;

import de.leghast.holography.Holography;
import de.leghast.holography.constant.Colors;
import de.leghast.holography.constant.Message;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.UserInterface;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.StringUtil;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class HologramCommand implements TabExecutor {

    private final Holography main;

    public HologramCommand(Holography main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Holography.PERMISSION)) return false;

        if(args.length < 1){
            player.sendMessage(Message.HOLO_COMMAND_USAGE);
            return true;
        }

        args[0] = args[0].toLowerCase();

        switch (args[0]) {
            case "create" -> createHolo(args, player);

            case "list" -> listHolos(player);

            case "remove" -> removeHolo(player);

            case "select" -> selectHolo(args, player);

            case "edit" -> editHolo(player);

            case "clear" -> clearClipboard(player);
        }
        return true;
    }

    private void clearClipboard(Player player) {
        if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
            main.getClipboardManager().remove(player.getUniqueId());
            player.sendMessage(Message.CLEARED_CLIPBOARD);
        } else {
            player.sendMessage(Message.CLIPBOARD_EMPTY);
        }
    }

    private void editHolo(Player player) {

        if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        } else {
            player.sendMessage(Message.NO_HOLO_SELECTED);
        }
    }

    private void selectHolo(String[] args, Player player) {
        Entity entity = player.getWorld().getEntity(UUID.fromString(args[1]));
        if(entity == null) player.sendMessage(Message.COULD_NOT_FIND_ENTITY);

        if (entity instanceof TextDisplay display) {
            main.getClipboardManager().update(player.getUniqueId(), display);
            player.sendMessage(Message.selectedHolo(display.text()));
        }
    }

    private void removeHolo(Player player) {
        if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
            main.getClipboardManager().getWrapper(player.getUniqueId()).display().remove();
            main.getClipboardManager().remove(player.getUniqueId());
            player.sendMessage(Message.REMOVED_HOLO);
        } else {
            player.sendMessage(Message.NO_HOLO_SELECTED);
        }
    }

    private static void listHolos(Player player) {
        final double radius = ConfigManager.RADIUS;

        List<TextDisplay> displays = player.getNearbyEntities(radius, radius, radius)
                .stream()
                .filter(e -> e instanceof TextDisplay)
                .map(e -> (TextDisplay) e)
                .toList();


        if (!displays.isEmpty()){
            player.sendMessage(Message.listCommandHeader((int) radius));
            for (TextDisplay display : displays) {
                player.sendMessage(Message.getDisplayNameComponent(display));
            }
        } else {
            player.sendMessage(Message.noHoloInRange((int) radius));
        }
    }

    private void createHolo(String[] args, Player player) {
        TextDisplay display = spawnTextDisplay(player.getLocation().add(0, 2, 0));
        if (args.length >= 2) {
            String text = String.join(" ", Arrays.stream(args).toList().subList(1, args.length));

            display.text(MiniMessage.miniMessage().deserialize(text));

        }
        main.getClipboardManager().update(player.getUniqueId(), display);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return new ArrayList<>();
        if(!player.hasPermission(Holography.PERMISSION)) return new ArrayList<>();

        if(args.length == 1){
            List<String> results = List.of(
                    "create",
                    "list",
                    "remove",
                    "select",
                    "edit",
                    "clear"
            );
            return StringUtil.copyPartialMatches(args[0], results, new ArrayList<>());
        }

        return new ArrayList<>();
    }

    private TextDisplay spawnTextDisplay(Location location){
        Vector position = location.toVector();
        TextDisplay display;
        display = (TextDisplay) location.getWorld().spawnEntity(
                new Location(
                        location.getWorld(),
                        position.getX(),
                        position.getY(),
                        position.getZ()),
                EntityType.TEXT_DISPLAY);

        display.text(Component.text("TextDisplay", Colors.ACCENT));
        display.setBillboard(ConfigManager.DEFAULT_BILLBOARD);
        return display;
    }

}
