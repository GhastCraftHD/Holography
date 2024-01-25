package de.leghast.holography.command;

import de.leghast.holography.Holography;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.ui.UserInterface;
import de.leghast.holography.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HologramCommand implements TabExecutor {

    private Holography main;

    public HologramCommand(Holography main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (player.hasPermission("holography.use")) {
                if (args.length >= 1) {
                    args[0] = args[0].toLowerCase();
                    switch (args[0]) {
                        case "create" -> {
                            TextDisplay display = Util.spawnTextDisplay(player.getLocation().add(0, 2, 0));
                            if (args.length > 1) {
                                List<String> textSegments = new ArrayList<>();
                                for (int i = 1; i <= args.length - 1; i++) {
                                    textSegments.add(ChatColor.translateAlternateColorCodes('&', args[i]));
                                }
                                display.setText(String.join(" ", textSegments));
                            }
                            main.getClipboardManager().update(player.getUniqueId(), display);
                        }
                        case "list" -> {
                            double r = ConfigManager.getRadius();
                            int amount = 0;
                            List<TextDisplay> displays = new ArrayList<>();
                            for (Entity entity : player.getNearbyEntities(r, r, r)) {
                                if (entity instanceof TextDisplay display) {
                                    amount++;
                                    displays.add(display);
                                }
                            }
                            if (amount != 0) {
                                player.sendMessage(Util.PREFIX + "§aAll text display in a radius of §e" + (int) r + " §ablock" + (r == 1 ? "" : "s"));
                                for (TextDisplay display : displays) {
                                    player.sendMessage(Util.getDisplayNameComponent(display));
                                }
                            } else {
                                player.sendMessage(Util.PREFIX + "§cThere are no text display in a radius of §e" + (int) r + " §cblock" + (r == 1 ? "" : "s"));
                            }
                        }
                        case "remove" -> {
                            if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
                                main.getClipboardManager().getWrapper(player.getUniqueId()).getDisplay().remove();
                                main.getClipboardManager().remove(player.getUniqueId());
                            } else {
                                player.sendMessage(Util.PREFIX + "§cYour clipboard is empty");
                            }
                        }
                        case "select" -> {
                            Entity entity = player.getWorld().getEntity(UUID.fromString(args[1]));
                            if (entity != null) {
                                if (entity instanceof TextDisplay display) {
                                    main.getClipboardManager().update(player.getUniqueId(), display);
                                    player.sendMessage(Util.PREFIX + "§aYou selected §r" + display.getText());
                                }
                            } else {
                                player.sendMessage(Util.PREFIX + "§cThis entity could not be found");
                            }
                        }
                        case "edit" -> {
                            if (!main.getSettingsManager().hasAdjusterSettings(player.getUniqueId())) {
                                main.getSettingsManager().addAdjusterSettings(player.getUniqueId());
                            }
                            if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
                                new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
                            } else {
                                player.sendMessage(Util.PREFIX + "§cYou dont have a text display selected");
                            }
                        }
                        case "clear" -> {
                            if (main.getClipboardManager().hasClipboard(player.getUniqueId())) {
                                main.getClipboardManager().remove(player.getUniqueId());
                                player.sendMessage(Util.PREFIX + "§aYour clipboard was cleared");
                            } else {
                                player.sendMessage(Util.PREFIX + "§cYour clipboard is already empty");
                            }
                        }
                    }

                } else {
                    player.sendMessage(Util.PREFIX + "§cUsage: /hologram <create|list|remove|select|edit|clear>");
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("holography.use")){
                if(args.length == 1){
                    List<String> results = new ArrayList<>();
                    results.add("create");
                    results.add("list");
                    results.add("remove");
                    results.add("select");
                    results.add("edit");
                    results.add("clear");
                    return StringUtil.copyPartialMatches(args[0], results, new ArrayList<>());
                }
            }
        }
        return new ArrayList<>();
    }
}
