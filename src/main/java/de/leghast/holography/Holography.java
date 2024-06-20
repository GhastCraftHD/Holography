package de.leghast.holography;

import com.destroystokyo.paper.Metrics;
import de.leghast.holography.command.HologramCommand;
import de.leghast.holography.listener.InventoryClickListener;
import de.leghast.holography.listener.PlayerChatListener;
import de.leghast.holography.listener.PlayerInteractListener;
import de.leghast.holography.listener.PlayerJoinListener;
import de.leghast.holography.manager.ChatInputManager;
import de.leghast.holography.manager.ClipboardManager;
import de.leghast.holography.manager.ConfigManager;
import de.leghast.holography.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;

public final class Holography extends JavaPlugin {

    public static String PERMISSION = "holography.use";

    private ClipboardManager clipboardManager;
    private SettingsManager settingsManager;
    private ChatInputManager chatInputManager;
    private Metrics metrics;

    private boolean updateAvailable = false;
    private String latestVersion = this.getPluginMeta().getVersion();

    @Override
    public void onLoad(){
        ConfigManager.setupConfig(this);
    }

    @Override
    public void onEnable() {
        clipboardManager = new ClipboardManager();
        settingsManager = new SettingsManager();
        chatInputManager = new ChatInputManager();
        getCommand("hologram").setExecutor(new HologramCommand(this));
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this), this);
        checkForUpdate();
        this.metrics = new Metrics("PaperMC", "22336", true, getLogger());
    }

    public ClipboardManager getClipboardManager(){
        return clipboardManager;
    }

    public SettingsManager getSettingsManager(){
        return settingsManager;
    }

    public ChatInputManager getChatInputManager() {
        return chatInputManager;
    }

    private void checkForUpdate() {
        if (!ConfigManager.CHECK_FOR_UPDATE) return;

        String apiUrl = "https://api.github.com/repos/GhastCraftHD/Holography/releases/latest";

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                try {
                    connection.setRequestProperty("Content-Type", "application/json");

                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) return;

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String input;
                    StringBuilder response = new StringBuilder();

                    while ((input = in.readLine()) != null) {
                        response.append(input);
                    }

                    in.close();
                    connection.disconnect();

                    if (!response.toString().contains("tag_name")) return;

                    latestVersion = response.toString().split("\"tag_name\":\"v")[1].split("\",")[0];

                } finally {
                    connection.disconnect();
                }

            } catch (Exception ignore) {}

            updateAvailable = !Objects.equals(latestVersion, this.getPluginMeta().getVersion());
        });

    }

    public boolean isUpdateAvailable(){
        return updateAvailable;
    }

    public String getLatestReleaseVersion(){
        return latestVersion;
    }
}
